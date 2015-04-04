package com.communicationhelper;

/**
 * Created by Illya Svergun on 04.04.15.
 *
 */
public class Dialog {
    int id;
    String title;

    public Dialog(int id, String title) {
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
