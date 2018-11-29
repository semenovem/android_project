package com.example.user.academy.dto;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

public class CategoryDto implements Serializable {
    @NonNull
    private final int id;
    @NonNull
    private final String name;

    CategoryDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}