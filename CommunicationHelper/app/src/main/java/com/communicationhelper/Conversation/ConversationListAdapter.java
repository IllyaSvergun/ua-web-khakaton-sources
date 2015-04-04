package com.communicationhelper.Conversation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.communicationhelper.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by ilia on 04.04.15.
 */
public class ConversationListAdapter extends BaseAdapter{

    private LayoutInflater mInflater;
    private final int TYPE_REQUEST = 0;
    private final int TYPE_RESPONSE = 1;
    private List<Speech> data;

    public ConversationListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        data = new ArrayList<>();
    }

    public void addItem(Speech speech){
        data.add(speech);
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
    public Speech getItem(int position){
        return data.get(position);
    }

    @Override
    public int getItemViewType(int position){
        return data.get(position).getType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        int type = getItemViewType(position);
        if (convertView == null) {
            holder = new ViewHolder();
            switch (type) {
                case TYPE_REQUEST:
                    convertView = mInflater.inflate(R.layout.list_item_request, null);
                    break;
                case TYPE_RESPONSE:
                    convertView = mInflater.inflate(R.layout.list_item_response, null);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.textView = (TextView)convertView.findViewById(R.id.text);
        holder.textView.setText(data.get(position).getText());
        return convertView;
    }

    public static class ViewHolder{
        public TextView textView;
    }
}
