package com.dynamitechetan.android.CardKeeper;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class CardAdapter extends ArrayAdapter<Card> {

    public CardAdapter(Activity context, List<Card> cards) {

        super(context,0, cards);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Card card = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.card_item, parent, false);
        }


        TextView cardName = (TextView) convertView.findViewById(R.id.card_name);
        cardName.setText(card.name);

        TextView cardNumber = (TextView) convertView.findViewById(R.id.card_number);
        TextView work = (TextView) convertView.findViewById(R.id.work);
        ImageView photo = (ImageView) convertView.findViewById(R.id.ivProfilePic);
        Glide.with(getContext()).load(card.photo).into(photo);

        cardNumber.setText(card.number);
        work.setText(card.photo);
        return convertView;
    }

}

