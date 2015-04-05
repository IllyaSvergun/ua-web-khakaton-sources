package com.communicationhelper;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.communicationhelper.Conversation.ConversationListAdapter;
import com.communicationhelper.Entities.Conversation;
import com.communicationhelper.Entities.Line;
import com.communicationhelper.Interfaces.LineTypes;

import ru.yandex.speechkit.Recognition;
import ru.yandex.speechkit.Recognizer;
import ru.yandex.speechkit.RecognizerListener;

/**
 * Created by ilia on 05.04.15.
 */
public class ConversationListener  implements RecognizerListener {
    private final String TAG = "ConversationListener";
    private Context mContext;
    private ConversationListAdapter mAdapter;
    private ListView listView;

    public ConversationListener(Context context, ConversationListAdapter adapter, ListView listView){
        mContext = context;
        mAdapter = adapter;
        this.listView = listView;
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
        Log.v(TAG, recognition.getBestResultText());
        Log.v(TAG, "onPartialResult");

    }

    @Override
    public void onRecognitionDone(Recognizer recognizer, Recognition recognition) {
        Log.v(TAG, "onRecordingDone");
        String result = recognition.getBestResultText();
        if(!result.equals("")) {
            Line response = new Line(result, LineTypes.RESPONSE);
            Conversation.addLineAndSave(mContext, response);
            mAdapter.addItem(response);
            listView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onError(Recognizer recognizer, ru.yandex.speechkit.Error error) {
        Log.v(TAG, "onError");

    }
}
