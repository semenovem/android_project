package com.example.user.academy.listArticles;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.user.academy.about.AboutActivity;
import com.example.user.academy.data.Article;
import com.example.user.academy.fullArticle.FullArticleActivity;
import com.example.user.academy.R;
import com.example.user.academy.data.DataUtils;
import com.example.user.academy.utils.ViewVisibleOnlyOne;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class ListArticlesActivity extends AppCompatActivity {
    private final ListArticlesAdapter.OnItemClickListener clickListener = article -> openDetailsArticle(article.toJson());
    private ListArticlesAdapter adapter;
    @Nullable
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_articles);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        ProgressBar progress = findViewById(R.id.progress);
        progress.setVisibility(View.GONE);

        RecyclerView recyclerView = findViewById(R.id.list_articles);

        adapter = new ListArticlesAdapter(this, clickListener);
        recyclerView.setAdapter(adapter);

//        List<Article> articles;

//        try {
//            articles = DataUtils.generateArticles();
//        } catch (NetworkErrorException ex) {
//
//            Log.e("debug error",  ex.toString());
//            return;
//        }
//
//        adapter.replaceItems(articles);

        ViewVisibleOnlyOne manageView = new ViewVisibleOnlyOne("string 0", "string 1 ", "string 2");

        manageView
                .on(recyclerView)
                .off(recyclerView);

        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            final int columnsCount = getResources().getInteger(R.integer.landscape_articles_columns_count);
            recyclerView.setLayoutManager(new GridLayoutManager(this, columnsCount));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    private void openDetailsArticle(String articlePackToJson) {
        FullArticleActivity.start(this, articlePackToJson);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadItems();
    }

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, ListArticlesActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected ( MenuItem item ) {
        switch ( item.getItemId () ) {
            case R.id.about_activity:
                startActivity ( new Intent( this , AboutActivity.class ) );
            default:
                return super.onOptionsItemSelected ( item );
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter = null;
    }


    private void loadItems() {
        disposable = DataUtils.observeNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateItems,
                        this::handleError);
    }

    private void handleError(Throwable th) {
        Log.e("tag_tag", th.getMessage(), th);
    }

    private void updateItems(@Nullable List<Article> news) {
        if (adapter != null) adapter.replaceItems(news);
    }
}
