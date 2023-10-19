package com.example.kostyaproject;

public class Ratings {
    String permanent,temporary,review;

    @Override
    public String toString() {
        return "Ratings{" +
                "permanent='" + permanent + '\'' +
                ", temporary='" + temporary + '\'' +
                ", review='" + review + '\'' +
                '}';
    }

    public String getPermanent() {
        return permanent;
    }

    public void setPermanent(String permanent) {
        this.permanent = permanent;
    }

    public String getTemporary() {
        return temporary;
    }

    public void setTemporary(String temporary) {
        this.temporary = temporary;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
