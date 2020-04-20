package com.kaiakz.apchat;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MessageAdapter extends BaseAdapter {
    private ArrayList<Message> messages = new ArrayList<Message>();
    private Context context;

    public MessageAdapter(Context context) {
        this.context = context;
    }

    public void add(Message message) {
        this.messages.add(message);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MessageViewHolder holder = new MessageViewHolder();
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        Message message = messages.get(position);

        convertView = messageInflater.inflate(R.layout.msg_txt_item, null);
        convertView.setTag(holder);
        holder.messageBody = (TextView)convertView.findViewById(R.id.msg_txt_ctx);
        holder.id = (TextView)convertView.findViewById(R.id.msg_txt_name);
        holder.date = (TextView)convertView.findViewById(R.id.msg_txt_timestamp);
        convertView.setTag(holder);
        holder.id.setText(message.getId());
        holder.messageBody.setText(message.getText());
        holder.date.setText(message.getDate());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

class MessageViewHolder {
    public TextView id;
    public TextView date;
    public TextView messageBody;
}