package com.zpi.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Photo implements Parcelable
{

    private String value;


    public Photo(String value)
    {
        this.value = value;
    }


    protected Photo(Parcel in)
    {

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

        dest.writeString(value);
    }
}
