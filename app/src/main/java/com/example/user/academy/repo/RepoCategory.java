package com.example.user.academy.repo;

import com.example.user.academy.dto.CategoryDto;

import java.util.ArrayList;

public class RepoCategory {
    private static RepoCategory instance = new RepoCategory();

    private RepoCategory() {}
    public static RepoCategory getInstance() {
        return instance;
    }

    private static ArrayList<CategoryDto> list = new ArrayList<>();
    private static int selectedCategoryId;

    public ArrayList<CategoryDto> getList() {
        return list;
    }

    public int getSelectedCategoryId() {
        return selectedCategoryId;
    }

    public RepoCategory setSelectedCategoryId(int id) {
        selectedCategoryId = id;
        return this;
    }
}
