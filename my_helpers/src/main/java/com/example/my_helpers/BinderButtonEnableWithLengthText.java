package com.example.my_helpers;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

public class BinderButtonEnableWithLengthText {
    private EditText instanceEditText;
    private Button instanceBottom;
    private int minLength;
    private Boolean isSubscribe = false;

    public BinderButtonEnableWithLengthText(EditText editText, Button button, int length) {
        instanceEditText = editText;
        instanceBottom = button;
        minLength = length;

        changeStatusEnable();
    }

    public void destroy() {
        unsubscribe();
        instanceEditText = null;
        instanceBottom = null;
    }

    public void resume() {
        if (!isSubscribe && instanceEditText != null && instanceBottom != null) {
            isSubscribe = true;
            subscribe();
        }
    }

    public void pause() {
        if (isSubscribe) {
            isSubscribe = false;
            unsubscribe();
        }
    }

    private void subscribe() {
        instanceEditText.addTextChangedListener(textWatcher);
    }

    private void unsubscribe() {
        instanceEditText.removeTextChangedListener(textWatcher);
    }

    private void changeStatusEnable() {
        int len = instanceEditText.getText().toString().length();

        instanceBottom.setEnabled(len >= minLength);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {}

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            changeStatusEnable();
        }
    };
}
