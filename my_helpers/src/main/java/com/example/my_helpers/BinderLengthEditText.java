package com.example.my_helpers;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

/**
 * по
 */
public class BinderLengthEditText {
    private EditText editText;
    private TextView textView;
    private Boolean isSubscribe = false;

    public BinderLengthEditText(EditText input, TextView output) {
        editText = input;
        textView = output;

        subscribe();
    }

    public void destroy() {
        unsubscribe();
        editText = null;
        textView = null;
    }

    public void resume() {
        if (!isSubscribe && editText != null && textView != null) {
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
        editText.addTextChangedListener(textWatcher);
    }

    private void unsubscribe() {
        editText.removeTextChangedListener(textWatcher);
    }

    private void handleOnChangeText() {
        int len = editText.getText().toString().length();
        String str = String.valueOf(len);

        textView.setText(str);
    }

    /**
     * Метод обработки изменения содержимого EditText
     */
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {}

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            handleOnChangeText();
        }
    };
}
