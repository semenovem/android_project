package com.example.user.academy.repo;

import com.example.user.academy.dto.ArticleDto;

import java.util.ArrayList;

public class RepoArticle {
    private static RepoArticle instance = new RepoArticle();

    private RepoArticle() {}
    public static RepoArticle getInstance() {
        return instance;
    }

    private static ArrayList<ArticleDto> list = new ArrayList<>();
    private static int selectedArticleId;

    public ArrayList<ArticleDto> getList() {
        return list;
    }

    public int getSelectedArticleId() {
        return selectedArticleId;
    }

    public RepoArticle setSelectedArticleId(int id) {
        selectedArticleId = id;
        return this;
    }
}
