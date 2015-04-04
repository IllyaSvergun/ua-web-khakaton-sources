package com.communicationhelper.Conversation;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.communicationhelper.Entities.Line;
import com.communicationhelper.Helpers.PreferencesHelper;
import com.communicationhelper.Interfaces.LineTypes;
import com.communicationhelper.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversationFragment extends Fragment {


    public ConversationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_conversation, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.conversation);
        ConversationListAdapter adapter = new ConversationListAdapter(getActivity());
        listView.setAdapter(adapter);

        try {
            JSONArray jsonArr = new JSONArray(PreferencesHelper.getConversation(getActivity()));
            for(int i = 0; i < jsonArr.length(); i++)
            {
                JSONObject jsonObj = jsonArr.getJSONObject(i);
                Line line = new Line(jsonObj);
                adapter.addItem(line);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter.addItem(new Line("request", LineTypes.REQUEST));
        adapter.addItem(new Line("response", LineTypes.RESPONSE));

        return rootView;
    }


}
