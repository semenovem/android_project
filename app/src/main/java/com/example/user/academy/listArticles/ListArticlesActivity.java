package com.example.user.academy.listArticles;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.academy.fullArticle.FullArticleActivity;
import com.example.user.academy.R;
import com.example.user.academy.data.DataUtils;

public class ListArticlesActivity extends AppCompatActivity {

    // todo can be do easier ?
    private final ListArticlesAdapter.OnItemClickListener clickListener = article -> {
        String articlePackToJson = article.toJson();
        openDetailsArticle(articlePackToJson);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_articles);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView recyclerView = findViewById(R.id.list_articles);
        recyclerView.setAdapter(new ListArticlesAdapter(this, DataUtils.generateArticles(), clickListener));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void openDetailsArticle(String articlePackToJson) {
        FullArticleActivity.start(this, articlePackToJson);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, ListArticlesActivity.class);
        activity.startActivity(intent);
    }
}