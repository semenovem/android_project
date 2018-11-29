package com.example.user.academy.dto;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class ArticleDto implements Serializable {
    @NonNull
    private final String title;
    @NonNull
    private final String imageUrl;
    @NonNull
    private final CategoryDto category;
    @NonNull
    private final Date publishDate;
    @NonNull
    private final String previewText;
    @NonNull
    private final String fullText;

    // todo not sure that's right - about methods: fromJson and toJson
    public static ArticleDto fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, ArticleDto.class);
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    ArticleDto(@NonNull String title, @NonNull String imageUrl, @NonNull CategoryDto category, @NonNull Date publishDate, @NonNull String previewText, @NonNull String fullText) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.category = category;
        this.publishDate = publishDate;
        this.previewText = previewText;
        this.fullText = fullText;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CategoryDto getCategory() {
        return category;
    }

//    public CategoryDto[id] getCategoryId() {
//        return category.getId();
//    }

    public Date getPublishDate() {
        return publishDate;
    }

    public String getPreviewText() {
        return previewText;
    }

    public String getFullText() {
        return fullText;
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, imageUrl, category, publishDate, previewText, fullText);
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", category=" + category +
                ", publishDate=" + publishDate +
                ", previewText='" + previewText + '\'' +
                ", fullText='" + fullText + '\'' +
                '}';
    }
}

