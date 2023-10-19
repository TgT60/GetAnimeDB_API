package com.example.kostyaproject;


public class Titles {
    String title;

    @Override
    public String toString() {
        return "Titles{" +
                "title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
