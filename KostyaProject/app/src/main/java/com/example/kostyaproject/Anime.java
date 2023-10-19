package com.example.kostyaproject;

public class Anime {
    String type,episodecount,startdate,enddate,id;
    Ratings ratings;
    Titles titles;

    public Anime() {
        this.type = "Test";
        this.episodecount = "work";
        this.startdate = "data";
        this.enddate = "fа";
    }

    @Override
    public String toString() {
        return "Anime{" +
                "type='" + type + '\'' +
                ", episodecount='" + episodecount + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", id='" + id + '\'' +
                ", ratings=" + ratings +
                ", titles=" + titles +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Titles getTitles(){
        return titles;
    }

    public void setTitles(Titles titles) {
        this.titles = titles;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEpisodecount() {
        return episodecount;
    }

    public void setEpisodecount(String episodecount) {
        this.episodecount = episodecount;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public Ratings getRatings() {
        return ratings;
    }

    public void setRatings(Ratings ratings) {
        this.ratings = ratings;
    }
}
