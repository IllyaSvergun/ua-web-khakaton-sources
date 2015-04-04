package com.communicationhelper.Entities;

/**
 * Created by Illya Svergun on 04.04.15.
 *
 */
public class Conversation {
    int id;
    String title;

    public Conversation(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
