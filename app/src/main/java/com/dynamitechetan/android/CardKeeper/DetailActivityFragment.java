package com.dynamitechetan.android.CardKeeper;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class DetailActivityFragment extends Fragment {
    Card myCard;
    private static final String CARD_KEY = "card" ;
    Bitmap bitmap;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=  inflater.inflate(R.layout.fragment_detail, container, false);
        Bundle arguments = getArguments();
        if(arguments != null){
            myCard = arguments.getParcelable(CARD_KEY);
        }

        ImageView photo = (ImageView) rootView.findViewById(R.id.ivProfilePic);
        Glide.with(getContext()).load(myCard.photo).into(photo);

        TextView cardName = (TextView)rootView.findViewById(R.id.card_name) ;
        cardName.setText(myCard.name);

        TextView cardNumber = (TextView)rootView.findViewById(R.id.card_number);
        cardNumber.setText(myCard.number);


        TextView cardWebsite = (TextView)rootView.findViewById(R.id.card_website) ;
        cardWebsite.setText(myCard.website);


        TextView cardEmail = (TextView)rootView.findViewById(R.id.card_email) ;
        cardEmail.setText(myCard.email);


        TextView cardAddress = (TextView)rootView.findViewById(R.id.card_addr) ;
        cardAddress.setText(myCard.address);


        return rootView;
    }
}
