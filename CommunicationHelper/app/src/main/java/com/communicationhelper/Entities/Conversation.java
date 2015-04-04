package com.communicationhelper.Entities;

import android.content.Context;

import com.communicationhelper.Helpers.PreferencesHelper;

import org.json.JSONArray;
import org.json.JSONException;

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

    public static void addLineAndSave(Context context, Line line) {
        try {
            if (PreferencesHelper.getConversation(context).equals("")) {
                JSONArray jsonArr = new JSONArray();
                jsonArr.put(line.toJSONObject());
                PreferencesHelper.saveConversation(context, jsonArr);
            } else {
                JSONArray jsonArr = new JSONArray(PreferencesHelper.getConversation(context));
                jsonArr.put(line.toJSONObject());
                PreferencesHelper.saveConversation(context, jsonArr);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
