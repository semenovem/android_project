package com.example.user.academy.listArticles;

import android.accounts.NetworkErrorException;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.example.user.academy.utils.Utils;
import com.example.user.academy.utils.ViewVisibleOnlyOne;
import com.example.user.academy.utils.AnimViewOpacity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class ListArticlesActivity extends AppCompatActivity {
    private final static String DEBUG_TAG = ListArticlesActivity.class.getSimpleName();

    private ListArticlesAdapter adapter;
    private AnimViewOpacity animPendind;

    @Nullable
    private Disposable disposable;
    @NonNull
    private ViewVisibleOnlyOne manageView;
    @NonNull
    private View viewError;
    @NonNull
    private View viewProgress;
    @NonNull
    private RecyclerView viewRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_articles);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(false);
        }

        viewError = findViewById(R.id.error_layout);
        viewProgress = findViewById(R.id.progress);
        viewRecycler = findViewById(R.id.list_articles);

        manageView = new ViewVisibleOnlyOne(findViewById(R.id.frame_list_articles));
        animPendind = new AnimViewOpacity(this, viewProgress);

        adapter = new ListArticlesAdapter(this, clickListener);
        viewRecycler.setAdapter(adapter);

        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            final int columnsCount = getResources().getInteger(R.integer.landscape_articles_columns_count);
            viewRecycler.setLayoutManager(new GridLayoutManager(this, columnsCount));
        } else {
            viewRecycler.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    private void openDetailsArticle(String articlePackToJson) {
        FullArticleActivity.start(this, articlePackToJson);
    }

    private final ListArticlesAdapter.OnItemClickListener clickListener = article -> openDetailsArticle(article.toJson());

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadItems();
        animPendind.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        animPendind.stop();
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
        Utils.disposeSafe(disposable);
        disposable = null;
        adapter = null;
        manageView.destroy();
    }

    private void loadItems() {
        manageView.on(viewProgress);
        disposable = DataUtils.observeNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateItems,
                        this::handleError);
    }

    private void handleError(Throwable th) {
        Log.e(DEBUG_TAG, th.getMessage(), th);
        manageView.on(viewError);
    }

    private void updateItems(@NonNull List<Article> news) {
        if (adapter != null) {
            adapter.replaceItems(news);
            manageView.on(viewRecycler);
        }
    }
}
