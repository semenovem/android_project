package com.example.user.academy.utils;

import android.support.annotation.Nullable;

import com.example.user.academy.BuildConfig;

import io.reactivex.disposables.Disposable;

public class Utils {
    private Utils() {
        throw new AssertionError("No instances");
    }

    public static void disposeSafe(@Nullable Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public static boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
