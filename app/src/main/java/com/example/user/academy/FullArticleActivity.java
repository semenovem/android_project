package com.example.user.academy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.academy.data.Article;

public class FullArticleActivity extends AppCompatActivity {
    public static final String ARTICLE_PACK_TO_JSON = "ARTICLE_PACK_TO_JSON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Article article;

        try {
            final String articlePackToJson = getIntent().getStringExtra(ARTICLE_PACK_TO_JSON);
            article = Article.fromJson(articlePackToJson);
        }
        catch (Exception e) {
            // show error

            return;
        }

        setContentView(R.layout.activity_full_article);

        // todo Glade need to remove overhead
        ImageView imageView = findViewById(R.id.image);
        RequestOptions imageOption = new RequestOptions()
                .placeholder(R.drawable.ic_preloader)
                .fallback(R.drawable.ic_preloader)
                .centerCrop();
        RequestManager imageLoader = Glide.with(this).applyDefaultRequestOptions(imageOption);
        imageLoader.load(article.getImageUrl()).into(imageView);

        TextView titleView = findViewById(R.id.title);
        titleView.setText(article.getTitle());

        TextView publishDateView = findViewById(R.id.publish_date);
        publishDateView.setText(article.getPublishDate().toString());

        TextView fullTextView = findViewById(R.id.full_text);
        fullTextView.setText(article.getFullText());

        TextView fullTextView1 = findViewById(R.id.full_text1);
        fullTextView1.setText(article.getFullText());
    }

    public static void start(Activity activity, String articlePackToJson) {
        Intent intent = new Intent(activity, FullArticleActivity.class);
        intent.putExtra(ARTICLE_PACK_TO_JSON, articlePackToJson);
        activity.startActivity(intent);
    }
}
