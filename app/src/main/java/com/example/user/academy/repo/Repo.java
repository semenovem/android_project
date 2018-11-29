package com.example.user.academy.repo;

public class Repo {
    private static Repo instance = new Repo();

    private static int selectedArticleId;
    private static int selectedSectionId;

    private Repo() {}

    public static Repo getInstance() {
        return instance;
    }

    static private String Text;

    public int getSelectedArticleId() {
        return selectedArticleId;
    }

    public Repo setSelectedArticleId(int id) {
        selectedArticleId = id;
        return this;
    }

    public int getSelectedSectionId() {
        return selectedSectionId;
    }

    public Repo setSelectedSectionId(int id) {
        selectedSectionId = id;
        return this;
    }
}
