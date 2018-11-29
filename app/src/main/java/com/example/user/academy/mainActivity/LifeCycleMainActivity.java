package com.example.user.academy.mainActivity;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

public class LifeCycleMainActivity implements LifecycleObserver {
//    private int[] nums;
//
//    LifeCycleMainActivity() {
//        nums = new int[10000];
//
//        for (int i = 0; i < nums.length; i ++) {
//            nums[i] = (int) (Math.random() * 10000);
//        }
//    }


//        LifeCycleMainActivity engine = new LifeCycleMainActivity();
//        getLifecycle().addObserver(engine);


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
