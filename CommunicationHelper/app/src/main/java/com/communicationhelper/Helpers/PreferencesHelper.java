package com.communicationhelper.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.json.JSONArray;

/**
 * Created by Illya Svergun on 04.04.15.
 *
 */
public class PreferencesHelper {
    public static final String CURRENT_CONVERSATION_PREF = "current_conversation_pref";

    private static SharedPreferences getPrefs(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    private static SharedPreferences.Editor getPrefsEditor(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).edit();
    }

    public static void clearConversation(Context context) {
        getPrefsEditor(context).remove(CURRENT_CONVERSATION_PREF).apply();
    }

    public static void saveConversation(Context context, JSONArray jsonArray) {
        getPrefsEditor(context).putString(CURRENT_CONVERSATION_PREF, jsonArray.toString()).apply();
    }

    public static String getConversation(Context context) {
        return getPrefs(context).getString(CURRENT_CONVERSATION_PREF, "");
    }

}
