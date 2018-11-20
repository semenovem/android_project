package com.example.user.academy.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.user.academy.R;

/**
 * reproduced from
 * @link http://developer.alexanderklimov.ru/android/animation/alpha.php
 */
public class AnimViewOpacity {
    private final View view;
    private Animation fadeInAnimation, fadeOutAnimation;

    public AnimViewOpacity(Context context, View view) {
        this.view = view;
        fadeInAnimation = AnimationUtils.loadAnimation(context, R.anim.fadein);
        fadeOutAnimation = AnimationUtils.loadAnimation(context, R.anim.fadeout);

        fadeInAnimation.setAnimationListener(animationFadeInListener);
        fadeOutAnimation.setAnimationListener(animationFadeOutListener);
    }

    public void start() {
        view.startAnimation(fadeOutAnimation);
    }

    public void stop() {
        view.clearAnimation();
    }

    private Animation.AnimationListener animationFadeOutListener = new Animation.AnimationListener() {

        @Override
        public void onAnimationEnd(Animation animation) {
            view.startAnimation(fadeInAnimation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub
        }
    };

    private Animation.AnimationListener animationFadeInListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationEnd(Animation animation) {
            view.startAnimation(fadeOutAnimation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub
        }
    };
}
