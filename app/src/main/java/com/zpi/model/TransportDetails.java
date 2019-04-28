package com.zpi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class TransportDetails implements Parcelable
{
    private int id;
    private int maxSittingPlaces;


    public TransportDetails(int maxSittingPlaces)
    {
        this.maxSittingPlaces = maxSittingPlaces;
    }

    protected TransportDetails(Parcel in)
    {
        id = in.readInt();
        maxSittingPlaces = in.readInt();
    }

    public static final Creator<TransportDetails> CREATOR = new Creator<TransportDetails>()
    {
        @Override
        public TransportDetails createFromParcel(Parcel in)
        {
            return new TransportDetails(in);
        }

        @Override
        public TransportDetails[] newArray(int size)
        {
            return new TransportDetails[size];
        }
    };

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(id);
        dest.writeInt(maxSittingPlaces);
    }

    public int getMaxSittingPlaces(){
        return maxSittingPlaces;
    }
}
