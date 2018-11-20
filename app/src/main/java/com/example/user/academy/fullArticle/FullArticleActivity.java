package com.example.user.academy.fullArticle;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.user.academy.R;
import com.example.user.academy.data.Article;

public class FullArticleActivity extends AppCompatActivity {
    public static final String ARTICLE_PACK_TO_JSON = "ARTICLE_PACK_TO_JSON";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            final String articlePackToJson = getIntent().getStringExtra(ARTICLE_PACK_TO_JSON);
            final Article article = Article.fromJson(articlePackToJson);

            setContentView(R.layout.activity_full_article);

            final ActionBar ab = getSupportActionBar();
            if (ab != null) {
                ab.setDisplayHomeAsUpEnabled(true);
                ab.setTitle(article.getCategory().getName());
            }

            ImageView imageView = findViewById(R.id.image);

            Glide.with(this)
                    .load(article.getImageUrl())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);

            TextView titleView = findViewById(R.id.title);
            titleView.setText(article.getTitle());

            TextView publishDateView = findViewById(R.id.publish_date);
            publishDateView.setText(article.getPublishDate().toString());

            TextView fullTextView = findViewById(R.id.full_text);
            fullTextView.setText(article.getFullText());

            TextView fullTextView1 = findViewById(R.id.full_text1);
            fullTextView1.setText(article.getFullText());
        }
        catch (Exception e) {
            setContentView(R.layout.layout_error);

            ((Button) findViewById(R.id.repeat_button)).setOnClickListener(view -> {

                // todo why do recreate activity ?
                //
            });
        }
    }

    public static void start(Activity activity, String articlePackToJson) {
        Intent intent = new Intent(activity, FullArticleActivity.class);
        intent.putExtra(ARTICLE_PACK_TO_JSON, articlePackToJson);
        activity.startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
