package com.dynamitechetan.android.CardKeeper;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


public class DetailActivityFragment extends Fragment implements OnMapReadyCallback {
    private static final String CARD_KEY = "card" ;
    Card myCard;
    GoogleMap mMap;
    MapView mMapView;
    LatLng adres;

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


        MapsInitializer.initialize(this.getActivity());
        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);

        return rootView;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Geocoder coder = new Geocoder(getContext());
        List<Address> addresses;
        try {
            addresses = coder.getFromLocationName(myCard.address, 5);
            if (addresses == null || addresses.size() == 0) {
                Toast.makeText(getContext(), "Failed to Load Map/ Ivalid Address", Toast.LENGTH_SHORT).show();
            } else {
                Address location = addresses.get(0);
                double lat = location.getLatitude();
                double lng = location.getLongitude();
                Log.i("Lat", "" + lat);
                Log.i("Lng", "" + lng);
                adres = new LatLng(lat, lng);
                mMap.addMarker(new
                        MarkerOptions().position(adres).title(myCard.name + ""));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(adres));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        adres = null;

        super.onPause();
    }
}
