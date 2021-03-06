package com.communicationhelper.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

import com.communicationhelper.Conversation.ConversationFragment;
import com.communicationhelper.ConversationListener;
import com.communicationhelper.Entities.Conversation;
import com.communicationhelper.Entities.Line;
import com.communicationhelper.Helpers.PreferencesHelper;
import com.communicationhelper.Interfaces.LineTypes;
import com.communicationhelper.R;

import ru.yandex.speechkit.Recognizer;
import ru.yandex.speechkit.SpeechKit;

public class MainActivity extends ActionBarActivity {
    AutoCompleteTextView mAutocomplete;
    private static final String TAG = "SpeechKitSample";
    private static final String TAGRESULT = "RESULT";
    private ImageButton btn_micro;
    private ImageButton btn_edit;
    private Recognizer recognizer;
    ConversationFragment mFragment;
    private boolean mRecognizerStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SpeechKit.getInstance().configure(getBaseContext(), "6afff325-1614-4958-9f6c-700ef26e566a");
        setContentView(R.layout.activity_main);
        btn_micro = (ImageButton)findViewById(R.id.btn_micro);
        btn_edit = (ImageButton) findViewById(R.id.text_button);
        recognizer = null;
        mFragment = new ConversationFragment();
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, mFragment)
                    .commit();
        }
        mAutocomplete = (AutoCompleteTextView) findViewById(R.id.autocomplete);
        mAutocomplete.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.autocomplete_strings)));

        mAutocomplete.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mAutocomplete.showDropDown();
                return false;
            }
        });
        mAutocomplete.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            String text = mAutocomplete.getText().toString();
                            Line request = new Line(text, LineTypes.REQUEST);
                            Conversation.addLineAndSave(getApplicationContext(), request);
                            executeBigTextScreen(text);
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
    }

    protected void executeBigTextScreen(String text) {
        Intent bigTextIntent = new Intent(getApplicationContext(), BigTextActivity.class);
        bigTextIntent.putExtra(BigTextActivity.EXTRA_BIG_TEXT, text);
        startActivity(bigTextIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void executeTypeText(View view) {
        mAutocomplete.setVisibility(View.VISIBLE);
        mAutocomplete.requestFocus();
        if (mRecognizerStarted) {
            if (recognizer != null) {
                recognizer.finishRecording();
                mRecognizerStarted = false;
                mFragment.getAdapter().setOnRecording(false);
                btn_micro.setBackgroundDrawable(getResources().getDrawable(R.drawable.selector_btn_micro));
            }
        }
    }

    public void executeMicro(View view) {
        if (mRecognizerStarted) {
            if(recognizer!=null){
                recognizer.finishRecording();
//                recognizer = null;
                mRecognizerStarted = false;
                mFragment.getAdapter().setOnRecording(false);
                btn_micro.setBackgroundDrawable(getResources().getDrawable(R.drawable.selector_btn_micro));}
        } else {
//            if(recognizer==null){
            mRecognizerStarted = true;
            mFragment.getAdapter().setOnRecording(true);
            recognizer = Recognizer.create("ru-RU", "general", new ConversationListener(getApplicationContext(),mFragment.getAdapter(),mFragment.getListView()));
            recognizer.setVADEnabled(false);
            recognizer.start();
            btn_micro.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_btn_micro_active));
//            }
        }

    }

    public void clearCurrentConversation(MenuItem item) {
        PreferencesHelper.clearConversation(getApplicationContext());
        mFragment.getAdapter().getDataSet().clear();
        mFragment.getAdapter().notifyDataSetChanged();
    }
}
