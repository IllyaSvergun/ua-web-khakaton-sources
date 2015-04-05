package com.communicationhelper;

import android.app.Application;
import android.content.Context;

/**
 * Created by elijah on 05.04.15.
 *
 */
public class CommunicationHelperApplication extends Application {
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}