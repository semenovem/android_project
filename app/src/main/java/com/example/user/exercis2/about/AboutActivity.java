package com.example.user.exercis2.about;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.exercis2.PreviewMessageActivity;
import com.example.user.exercis2.R;

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button btn = findViewById(R.id.button_send_message);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSendMessage(getMessage());
            }
        });
    }

    private String getMessage() {
        EditText field = findViewById(R.id.input_message);
        return field.getText().toString();
    }

    private void handleSendMessage(String message) {
        PreviewMessageActivity.start(this, message);
    }
}
