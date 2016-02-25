package com.example;

/**
 * Created by luksyvyg on 25/02/2016.
 */
public class Player {
    private String name;
    private int lives;
    private int level;
    private int score;

    public Player(String name) {
        this.name = name;
        this.lives = 3;
        this.level = 1;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getLives() {
        return lives;
    }

    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        if (name.length() < 3)
            return;
        this.name = name;
    }
}
