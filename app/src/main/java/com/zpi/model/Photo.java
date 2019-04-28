package com.zpi.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Photo implements Parcelable
{
    private int serviceId;
    private String value;


    public Photo(int serviceId, String value)
    {
        this.serviceId = serviceId;
        this.value = value;
    }

    protected Photo(Parcel in)
    {
        serviceId = in.readInt();
        value = in.readString();
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>()
    {
        @Override
        public Photo createFromParcel(Parcel in)
        {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size)
        {
            return new Photo[size];
        }
    };

    public String getValue()
    {
        return value;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(serviceId);
        dest.writeString(value);
    }
}
