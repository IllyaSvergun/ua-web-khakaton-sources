package com.communicationhelper.Conversation;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.communicationhelper.R;

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

        adapter.addItem(new Speech("request",Speech.TYPE_REQUEST));
        adapter.addItem(new Speech("response",Speech.TYPE_RESPONSE));

        return rootView;
    }


}
