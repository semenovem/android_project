package com.example.user.academy.utils;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.academy.data.Article;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ViewVisibleOnlyOne {
    @NonNull
    private final ArrayList<View> views;

    public ViewVisibleOnlyOne(View... views) {
        this.views = new ArrayList<>(Arrays.asList(views));
    }

    public ViewVisibleOnlyOne(ViewGroup viewGroup) {
        int countViews = viewGroup.getChildCount();
        this.views = new ArrayList<>(countViews);

        for(int i=0; i < countViews; i++) {
            this.views.add(viewGroup.getChildAt(i));
        }
    }


    public ViewVisibleOnlyOne on(View view) {
        View v;

        // todo do optimisation for views.size() ?
        for(int i=0; i < views.size(); i++) {
            v = views.get(i);
            if (view == v) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
        }


        return this;
    }

    public ViewVisibleOnlyOne off(View v) {


        return this;
    }
}
