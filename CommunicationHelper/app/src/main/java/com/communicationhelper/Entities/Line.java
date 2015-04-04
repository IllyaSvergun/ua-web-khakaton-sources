package com.communicationhelper.Entities;

import com.communicationhelper.Interfaces.LineTypes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Illya Svergun on 04.04.15.
 *
 */
public class Line implements LineTypes {
    String id;
    String text;
    String type;
    int prevLineId;
    int dialogId;

    public Line(JSONObject json) {
        try {
            this.id = json.getString("id");
            this.text = json.getString("text");
            this.type = json.getString("type");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Line(String text, String type) {
        this.id = UUID.randomUUID().toString();
        this.text = text;
        this.type = type;
    }

    public Line(String id, String text, String type) {
        this.id = id;
        this.text = text;
        this.type = type;
    }

    public String getId() {
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

    public JSONObject toJSONObject() {
        Map<String, String> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("text", this.getText());
        map.put("type", this.getType());
        return new JSONObject(map);
    }

}
