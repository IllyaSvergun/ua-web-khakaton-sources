package com.communicationhelper.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.communicationhelper.R;


public class BigTextActivity extends ActionBarActivity {
    public static final String EXTRA_BIG_TEXT = "com.communicationhelper.EXTRA_BIG_TEXT";

    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_text);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("");

        mText = (TextView) findViewById(R.id.text);
        mText.setText(getIntent().getStringExtra(EXTRA_BIG_TEXT));
    }
}
