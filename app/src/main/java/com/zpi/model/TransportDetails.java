package com.zpi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class TransportDetails implements Parcelable
{

    private int id;
    private Service service;
    private int maxSittingPlaces;

 //   private Date createdAt;
 //   private Date updatedAt;


    public TransportDetails(int maxSittingPlaces)
    {
    //    this.service = service;
        this.maxSittingPlaces = maxSittingPlaces;
    }

    protected TransportDetails(Parcel in)
    {
        id = in.readInt();
        service = in.readParcelable(Service.class.getClassLoader());
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
        dest.writeParcelable(service, flags);
    }
}
