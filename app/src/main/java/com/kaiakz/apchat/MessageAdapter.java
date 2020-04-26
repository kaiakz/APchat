package com.kaiakz.apchat;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MessageAdapter extends BaseAdapter {
    private ArrayList<Message> messages = new ArrayList<Message>();
    private Context context;
    private  LayoutInflater li;

    class TextViewHolder {
        TextView id;
        TextView date;
        TextView text;
    }

    class ImageViewHolder {
        TextView id;
        TextView date;
        ImageView image;
    }

    public MessageAdapter(Context context) {
        this.context = context;
        li = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }

    public void putMessage(Message message) {
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

        Message message = messages.get(position);

        switch (message.getType()) {
            case TEXT: {
                TextViewHolder holder = new TextViewHolder();
                convertView = this.li.inflate(R.layout.msg_txt_item, parent, false);
                holder.text = (TextView)convertView.findViewById(R.id.msg_txt_ctx);
                holder.id = (TextView)convertView.findViewById(R.id.msg_txt_name);
                holder.date = (TextView)convertView.findViewById(R.id.msg_txt_timestamp);
                holder.id.setText(message.getId());
                holder.text.setText(message.getText());
                holder.date.setText(message.getDate());
                convertView.setTag(holder);
                break;
            }
            case IMAGE: {
                ImageViewHolder holder = new ImageViewHolder();
                convertView = this.li.inflate(R.layout.msg_img_item, parent, false);
                holder.image = (ImageView)convertView.findViewById(R.id.msg_img_ctx);
                holder.id = (TextView)convertView.findViewById(R.id.msg_img_name);
                holder.date = (TextView)convertView.findViewById(R.id.msg_img_timestamp);
                holder.id.setText(message.getId());
                holder.image.setImageResource(R.drawable.ic_launcher_foreground);
                holder.date.setText(message.getDate());
                convertView.setTag(holder);
                break;
            }
        }

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

