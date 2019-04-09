package com.zpi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class WeddingHallDetails implements Parcelable
{

    private int id;
    private Service service;
    private boolean canSleep;
    private int maxNumberOfGuests;

  //  private Date createdAt;
   // private Date updatedAt;


    public WeddingHallDetails(boolean canSleep, int maxNumberOfGuests)
    {
        this.canSleep = canSleep;
        this.maxNumberOfGuests = maxNumberOfGuests;
    }

    protected WeddingHallDetails(Parcel in)
    {
        id = in.readInt();
        service = in.readParcelable(Service.class.getClassLoader());
        canSleep = in.readByte() != 0;
        maxNumberOfGuests = in.readInt();
    }

    public static final Creator<WeddingHallDetails> CREATOR = new Creator<WeddingHallDetails>()
    {
        @Override
        public WeddingHallDetails createFromParcel(Parcel in)
        {
            return new WeddingHallDetails(in);
        }

        @Override
        public WeddingHallDetails[] newArray(int size)
        {
            return new WeddingHallDetails[size];
        }
    };

    public boolean isCanSleep()
    {
        return canSleep;
    }

    public int getMaxNumberOfGuests()
    {
        return maxNumberOfGuests;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(id);
        dest.writeParcelable(service, flags);
        dest.writeByte((byte) (canSleep ? 1 : 0));
        dest.writeInt(maxNumberOfGuests);
    }
}
