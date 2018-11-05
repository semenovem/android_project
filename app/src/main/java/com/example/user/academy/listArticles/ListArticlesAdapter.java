package com.example.user.academy.listArticles;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.academy.R;
import com.example.user.academy.data.Article;
import java.util.List;


public class ListArticlesAdapter extends RecyclerView.Adapter<ListArticlesAdapter.ViewHolder> {
    @NonNull
    private final List<Article> articles;
    @NonNull
    private final LayoutInflater inflater;
    @Nullable
    private final OnItemClickListener clickListener;
    @NonNull
    private final RequestManager imageLoader;

    public interface OnItemClickListener {
        void onItemClick(Article article);
    }

    ListArticlesAdapter(@NonNull Context context,
                        @NonNull List<Article> articles,
                        @Nullable OnItemClickListener clickListener) {
        this.articles = articles;
        this.inflater = LayoutInflater.from(context);
        this.clickListener = clickListener;

        RequestOptions imageOption = new RequestOptions()
                .placeholder(R.drawable.ic_preloader)
                .fallback(R.drawable.ic_preloader)
                .centerCrop();
        this.imageLoader = Glide.with(context).applyDefaultRequestOptions(imageOption);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_article, parent, false), clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(articles.get(position));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView categoryView;
        private final ImageView imageView;
        private final TextView titleView;
        private final TextView previewView;
        private final TextView publishDateView;

        ViewHolder(@NonNull View itemView, @Nullable OnItemClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(articles.get(position));
                }
            });
            categoryView = itemView.findViewById(R.id.category);
            titleView = itemView.findViewById(R.id.title);
            previewView = itemView.findViewById(R.id.preview);
            publishDateView = itemView.findViewById(R.id.publish_date);
            imageView = itemView.findViewById(R.id.image);
        }

        void bind(Article article) {
            imageLoader.load(article.getImageUrl()).into(imageView);
            categoryView.setText(article.getCategory().getName());
            titleView.setText(article.getTitle());
            previewView.setText(article.getPreviewText());
            publishDateView.setText(article.getPublishDate().toString());
        }
    }
}