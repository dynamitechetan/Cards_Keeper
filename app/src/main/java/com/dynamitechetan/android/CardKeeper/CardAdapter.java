package com.dynamitechetan.android.CardKeeper;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
        final Card card = getItem(position);

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
        work.setText(card.address);
        ImageView phone, wesbite, email;
        phone = (ImageView) convertView.findViewById(R.id.phone);
        wesbite = (ImageView) convertView.findViewById(R.id.website);
        email = (ImageView) convertView.findViewById(R.id.email);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + card.number));
                getContext().startActivity(intent);
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", card.email, null));

                getContext().startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
        wesbite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = card.website;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                getContext().startActivity(i);
            }
        });
        return convertView;
    }

}

