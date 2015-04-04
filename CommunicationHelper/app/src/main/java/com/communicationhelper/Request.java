package com.communicationhelper;

/**
 * Created by Illya Svergun on 04.04.15.
 *
 */
public class Request {
   int id;
    String text;
    String prevEntityType;
    int prevEntityId;

    public Request(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public Request(int id, String text, String prevEntityType, int prevEntityId) {
        this.id = id;
        this.text = text;
        this.prevEntityType = prevEntityType;
        this.prevEntityId = prevEntityId;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getPrevEntityType() {
        return prevEntityType;
    }

    public int getPrevEntityId() {
        return prevEntityId;
    }
}
