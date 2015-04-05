package com.communicationhelper;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.communicationhelper.Activities.MainActivity;

/**
 * Created by elijah on 05.04.15.
 *
 */
public class CommunicationHelperApplication extends Application {
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public static void startMain() {
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION|Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}