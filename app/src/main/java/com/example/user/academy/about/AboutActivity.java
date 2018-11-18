package com.example.user.academy.about;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.academy.R;
import com.example.user.academy.previewAbout.PreviewAboutActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle("Ivan Ivanov");
        }

        Button btn = findViewById(R.id.button_send_message);
        btn.setOnClickListener(view -> openPreviewAboutActivity(
                ((EditText) findViewById(R.id.input_message)).getText().toString()
        ));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void openPreviewAboutActivity(String message) {
        PreviewAboutActivity.start(this, message);
    }
}
