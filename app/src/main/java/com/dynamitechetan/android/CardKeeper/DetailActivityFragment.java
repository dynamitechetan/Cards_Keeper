package com.dynamitechetan.android.CardKeeper;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


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


        TextView cardName = (TextView)rootView.findViewById(R.id.card_name) ;
        cardName.setText(myCard.name);

        TextView cardNumber = (TextView)rootView.findViewById(R.id.card_number);
        cardNumber.setText(myCard.number);

        return rootView;
    }
}
