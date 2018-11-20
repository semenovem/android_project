package com.example.user.academy.utils;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;


public class ViewVisibleOnlyOne {
    private final ArrayList<View> views;

    public ViewVisibleOnlyOne(View... views) {
        this.views = new ArrayList<>(Arrays.asList(views));
    }

    public ViewVisibleOnlyOne(ViewGroup viewGroup) {
        int countViews = viewGroup.getChildCount();
        this.views = new ArrayList<>(countViews);

        for(int i = 0; i < countViews; i++) {
            this.views.add(viewGroup.getChildAt(i));
        }
    }

    public ViewVisibleOnlyOne on(@Nullable View view) {
        if (view != null) {
            View v;

            for(int i = 0; i < views.size(); i++) {
                v = views.get(i);
                if (view == v) {
                    v.setVisibility(View.VISIBLE);
                } else {
                    v.setVisibility(View.GONE);
                }
            }
        }
        return this;
    }

    public void destroy() {
        views.clear();
    }
}
