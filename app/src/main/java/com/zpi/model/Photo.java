package com.zpi.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Photo implements Parcelable
{
    private int serviceId;
    private int photoId;


    public Photo(int id)
    {
        this.serviceId = id;
    }

    protected Photo(Parcel in)
    {
        serviceId = in.readInt();
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

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {dest.writeInt(serviceId);}

    public int getServiceId()
    {
        return serviceId;
    }

    public int getPhotoId() {
        return photoId;
    }
}
