package com.example.user.academy.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.academy.listArticles.ListArticlesActivity;
import com.example.user.academy.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListArticlesActivity.start(this);
    }
}
