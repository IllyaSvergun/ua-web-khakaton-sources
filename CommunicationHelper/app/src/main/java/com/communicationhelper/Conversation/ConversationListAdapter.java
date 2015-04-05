package com.communicationhelper.Conversation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.communicationhelper.Activities.BigTextActivity;
import com.communicationhelper.Entities.Line;
import com.communicationhelper.Interfaces.LineTypes;
import com.communicationhelper.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilia on 04.04.15.
 *
 */
public class ConversationListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Line> data;
    private boolean isOnRecording = false;
    Context mContext;

    public ConversationListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        data = new ArrayList<>();
    }

    public List<Line> getDataSet() {
        return data;
    }

    public void addItem(Line line){
        data.add(line);
        notifyDataSetChanged();
    }

    @Override
    public int getCount(){
        return data.size();
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public Line getItem(int position){
        return data.get(position);
    }

    public String getItemType(int position) {
        return data.get(position).getType();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        String type = getItemType(position);
        if (convertView == null) {
            holder = new ViewHolder();
            switch (type) {
                case LineTypes.REQUEST:
                    convertView = mInflater.inflate(R.layout.list_item_request, parent, false);
                    break;
                case LineTypes.RESPONSE:
                    convertView = mInflater.inflate(R.layout.list_item_response, parent, false);
                    break;
            }
            if (convertView != null) {
                convertView.setTag(holder);
            }
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        if (convertView != null) {
            holder.textView = (TextView)convertView.findViewById(R.id.text);
        }
        holder.textView.setText(data.get(position).getText());
        assert convertView != null;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOnRecording) {
                    Intent bigTextIntent = new Intent(mContext, BigTextActivity.class);
                    bigTextIntent.putExtra(BigTextActivity.EXTRA_BIG_TEXT, getItem(position).getText());
                    mContext.startActivity(bigTextIntent);
                }
            }
        });
        return convertView;
    }

    public void setOnRecording(boolean isRecording){
        isOnRecording = isRecording;
    }

    public static class ViewHolder{
        public TextView textView;
    }
}
