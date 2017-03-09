package com.marijandroid.android_things.text_to_speech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.marijandroid.android_things.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marijan on 03/03/2017.
 */
public class MessagesAdapter extends BaseAdapter {

    private List<Message> messages = new ArrayList<>();
    private Context context;

    public MessagesAdapter(Context context) {
        this.context = context;
    }

    public void addMessage(Message message) {
        messages.add(0, message);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Message getItem(int position) {
        if (position < messages.size()) {
            return messages.get(position);
        } else {
            return new Message();
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.message_item, parent, false);
        }

        Message item = getItem(position);

        TextView message = (TextView) convertView.findViewById(R.id.message);
        message.setText(item.getMessage());

        TextView time = (TextView) convertView.findViewById(R.id.time);
        time.setText(item.getTime());

        TextView user = (TextView) convertView.findViewById(R.id.user);
        user.setText(item.getUser());


        return convertView;
    }
}
