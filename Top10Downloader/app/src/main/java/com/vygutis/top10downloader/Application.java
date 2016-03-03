package com.vygutis.top10downloader;

/**
 * Created by luksyvyg on 03/03/2016.
 */
public class Application {

    private String name;
    private String artist;
    private String releaseDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + "\n" +
                "Artist: " + this.getArtist() + "\n" +
                "Release Date: " + this.getReleaseDate() + "\n";
    }
}
