package com.communicationhelper.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.os.Build;
import android.widget.ImageButton;

import com.communicationhelper.Entities.Conversation;
import com.communicationhelper.Entities.Line;
import com.communicationhelper.Interfaces.LineTypes;
import com.communicationhelper.R;
import ru.yandex.speechkit.*;

import com.communicationhelper.Conversation.ConversationFragment;

import org.json.JSONArray;
import org.json.JSONException;


public class MainActivity extends ActionBarActivity implements RecognizerListener {
    AutoCompleteTextView mAutocomplete;
    private static final String TAG = "SpeechKitSample";
    private static final String TAGRESULT = "RESULT";
    private Recognizer recognizer;
    private boolean mRecognizerStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SpeechKit.getInstance().configure(getBaseContext(), "6afff325-1614-4958-9f6c-700ef26e566a");
        setContentView(R.layout.activity_main);
        recognizer = null;
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ConversationFragment())
                    .commit();
        }
        mAutocomplete = (AutoCompleteTextView) findViewById(R.id.autocomplete);
        mAutocomplete.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
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

    @Override
    public void onRecordingBegin(Recognizer recognizer) {
        Log.v(TAG, "onRecordingBegin");

    }

    @Override
    public void onSpeechDetected(Recognizer recognizer) {
        Log.v(TAG, "onSpeechDetected");

    }

    @Override
    public void onRecordingDone(Recognizer recognizer) {
        Log.v(TAG, "onRecordDone");


    }

    @Override
    public void onSoundDataRecorded(Recognizer recognizer, byte[] bytes) {
        Log.v(TAG, "onSoundDataRecorded");
    }

    @Override
    public void onPowerUpdated(Recognizer recognizer, float power) {
//        Log.v(TAG, "onPoverUpdate");
//        Log.v("onPoverUpdate", String.valueOf(power));
//        if (power<0.1){
//            recognizer.finishRecording();
//            Log.v("onPoverUpdate", "<0.3");
//        }
//        else
//        if(power>=0.1){
//            Log.v("onPoverUpdate", ">0.3");
////            recognizer = Recognizer.create("ru-RU", "general", new MainActivity());
////            recognizer.setVADEnabled(false);
////            recognizer.start();
//        }

    }

    @Override
    public void onPartialResults(Recognizer recognizer, Recognition recognition, boolean b) {
        Log.v(TAGRESULT, recognition.getBestResultText());
        Log.v(TAG, "onPartialResult");

    }

    @Override
    public void onRecognitionDone(Recognizer recognizer, Recognition recognition) {
        Log.v(TAG, "onRecordingDone");
        String result = recognition.getBestResultText();
        Line response = new Line(result, LineTypes.RESPONSE);
        Conversation.addLineAndSave(getApplicationContext(), response);
        Log.v("recognized_text", result);

    }

    @Override
    public void onError(Recognizer recognizer, ru.yandex.speechkit.Error error) {
        Log.v(TAG, "onError");

    }


    public void executeTypeText(View view) {
        mAutocomplete.setVisibility(View.VISIBLE);
        mAutocomplete.requestFocus();
    }

    public void executeMicro(View view) {
        if (mRecognizerStarted) {
            if(recognizer!=null){
                recognizer.finishRecording();}
        } else {
            if(recognizer==null){
            mRecognizerStarted = true;
            recognizer = Recognizer.create("ru-RU", "general", new MainActivity());
            recognizer.setVADEnabled(false);
            recognizer.start();
            }
        }

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
