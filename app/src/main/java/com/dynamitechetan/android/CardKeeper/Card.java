package com.dynamitechetan.android.CardKeeper;

import android.os.Parcel;
import android.os.Parcelable;


public class Card implements Parcelable {
    Integer id;
    String name;
    String number;
    String photo;
    String email;
    String website;
    String address;

    public Card (Integer mId, String mName, String mNumber,String email,String photo,String website,String address){
        this.id = mId;
        this.name = mName;
        this.number = mNumber;
        this.photo = photo;
        this.email = email;
        this.website = website;
        this.address = address;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.number);
        dest.writeString(this.email);
        dest.writeString(this.photo);
        dest.writeString(this.website);
        dest.writeString(this.address);
    }

    protected Card(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.number = in.readString();
        this.email = in.readString();
        this.photo = in.readString();
        this.website = in.readString();
        this.address = in.readString();
    }

    public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel source) {
            return new Card(source);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };
}
