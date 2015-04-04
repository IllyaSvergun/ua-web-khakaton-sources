package com.communicationhelper.Conversation;

/**
 * Created by ilia on 04.04.15.
 */
public class Speech {
    private String text;
    private int type;

    public static final int TYPE_REQUEST = 0;
    public static final int TYPE_RESPONSE = 1;

    public Speech(String text, int type){
        this.text = text;
        this.type = type;
    }

    public int getType(){
        return type;
    }

    public String getText(){
        return text;
    }

}
