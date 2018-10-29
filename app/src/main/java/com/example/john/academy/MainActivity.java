package com.example.john.academy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.my_helpers.*;

public class MainActivity extends AppCompatActivity {
    private BinderLengthEditText binderDrawLength;
    private BinderButtonEnableWithLengthText binderEnableButton;
    private static final int MIN_LENGTH_MESSAGE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.buttonPreview);
        btn.setOnClickListener(this::handleButton);

        binderEnableButton = new BinderButtonEnableWithLengthText(
                findViewById(R.id.input),
                btn,
                MIN_LENGTH_MESSAGE
        );
    }

    public void openPreviewActivity(String msg) {
        PreviewActivity.start(this, msg);
    }

    private void handleButton(View v) {
        openPreviewActivity(getMessage());
    }

    private String getMessage() {
        EditText input = findViewById(R.id.input);
        return input.getText().toString();
    }

    @Override
    protected void onStart() {
        super.onStart();
        binderDrawLength = new BinderLengthEditText(
                findViewById(R.id.input),
                findViewById(R.id.input_length)
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        binderDrawLength.resume();
        binderEnableButton.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        binderDrawLength.pause();
        binderEnableButton.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        binderDrawLength.destroy();
        binderDrawLength = null;
        binderEnableButton.destroy();
        binderEnableButton = null;
    }
}
