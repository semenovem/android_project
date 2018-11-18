package com.example.user.academy.utils;

import android.support.annotation.Nullable;

import io.reactivex.disposables.Disposable;

public class Utils {
    private Utils() {}

    public static void disposeSafe(@Nullable Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
