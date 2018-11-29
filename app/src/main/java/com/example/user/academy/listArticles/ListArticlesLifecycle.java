package com.example.user.academy.listArticles;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

public class ListArticlesLifecycle implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(LifecycleOwner owner) {
//        btnOpenSelectSection = findViewById(R.id.btn_open_select_section);
//        btnOpenSelectSection.setText("HOME");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        System.out.println("onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        System.out.println("onPause");
    }

//    public void destroy() {
//        System.out.println(nums[500]);
//        nums = new int[1];
//    }
}
