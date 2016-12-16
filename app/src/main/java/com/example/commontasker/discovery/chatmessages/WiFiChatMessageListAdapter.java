package com.example.commontasker.discovery.chatmessages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.commontasker.R;


class WiFiChatMessageListAdapter extends ArrayAdapter<String> {

    private final WiFiChatFragment chatFragment;


    public WiFiChatMessageListAdapter(Context context, int textViewResourceId,
                                      WiFiChatFragment chatFragment) {
        super(context,textViewResourceId,chatFragment.getItems());
        this.chatFragment = chatFragment;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(v==null) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatmessage_row, parent, false);
        }

        String message = chatFragment.getItems().get(position);
        if (message != null && !message.isEmpty()) {
            TextView nameText = (TextView) v
                    .findViewById(R.id.message);
            if (nameText != null) {
                nameText.setText(message);
                nameText.setTextAppearance(chatFragment.getActivity(),R.style.normalText);
                if(chatFragment.isGrayScale()) {
                    nameText.setTextColor(chatFragment.getResources().getColor(R.color.gray));
                } else {
                    if (message.startsWith("Me: ")) {
                        nameText.setTextAppearance(chatFragment.getActivity(),
                                R.style.normalText);
                    } else {
                        nameText.setTextAppearance(chatFragment.getActivity(),
                                R.style.boldText);
                    }
                }
            }
        }
        return v;
    }
}