package com.communicationhelper.Entities;

import com.communicationhelper.Interfaces.LineTypes;

/**
 * Created by Illya Svergun on 04.04.15.
 *
 */
public class Line implements LineTypes {
    int id;
    String text;
    String type;
    int prevLineId;
    int dialogId;

    public Line(String text, String type) {
        this.text = text;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public int getPrevLineId() {
        return prevLineId;
    }

    public int getDialogId() {
        return dialogId;
    }
}
