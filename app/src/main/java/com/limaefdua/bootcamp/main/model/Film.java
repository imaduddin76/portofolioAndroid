package com.limaefdua.bootcamp.main.model;
//
// Created by maftuhin on 10/18/2019.
//

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Film {
    int movie_id;
    String title;
    String synopsis;
    String image;
    String category_name;

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
