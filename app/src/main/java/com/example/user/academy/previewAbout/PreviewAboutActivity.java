package com.example.user.academy.previewAbout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.academy.R;

public class PreviewAboutActivity extends AppCompatActivity {
    public static final String MESSAGE = "MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_about);

        final String messageString = getIntent().getStringExtra(MESSAGE);

        TextView textView = findViewById(R.id.message_preview);
        textView.setText(messageString);

        Button btn = findViewById(R.id.button_send_message);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMail(messageString);
            }
        });

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle("Check before");
        }
    }

    private void openMail(String message) {
        final Intent intent = new Intent(Intent.ACTION_SENDTO)
                .setData(Uri.parse(String.format("mailto:%s", getString(R.string.about_email_address))))
                .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.about_email_subject))
                .putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.error_no_email_app, Toast.LENGTH_LONG).show();
        }
    }

    public static void start(Activity activity, String message) {
        Intent intent = new Intent(activity, PreviewAboutActivity.class);
        intent.putExtra(MESSAGE, message);
        activity.startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}