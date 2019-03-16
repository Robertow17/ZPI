package com.zpi.searcher.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class WeddingHall implements Parcelable
{

    private String name;
    private String localization;
    private int maxNumberOfGuests;
    boolean canSleep, isFavourite;
    private String description;
    private ArrayList<Integer> photos;

    public WeddingHall(String name)
    {
        this.name = name;
    }

    public WeddingHall(String name, String localization, int maxNumberOfGuests, boolean canSleep,
                       String description, ArrayList<Integer> photos)
    {
        this.name = name;
        this.localization = localization;
        this.maxNumberOfGuests = maxNumberOfGuests;
        this.canSleep = canSleep;
        this.description = description;
        this.photos = photos;
    }


    protected WeddingHall(Parcel in)
    {
        name = in.readString();
        localization = in.readString();
        description = in.readString();
        maxNumberOfGuests = in.readInt();
        photos = (ArrayList<Integer>) in.readSerializable();
        canSleep = in.readByte() != 0;
        isFavourite = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(name);
        dest.writeString(localization);
        dest.writeString(description);
        dest.writeInt(maxNumberOfGuests);
        dest.writeSerializable(photos);
        dest.writeByte((byte) (canSleep ? 1 : 0));
        dest.writeByte((byte) (isFavourite ? 1 : 0));
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    public static final Creator<WeddingHall> CREATOR = new Creator<WeddingHall>()
    {
        @Override
        public WeddingHall createFromParcel(Parcel in)
        {
            return new WeddingHall(in);
        }

        @Override
        public WeddingHall[] newArray(int size)
        {
            return new WeddingHall[size];
        }
    };


    public boolean isFavourite()
    {
        return isFavourite;
    }

    public void setFavourite(boolean favourite)
    {
        isFavourite = favourite;
    }

    public ArrayList<Integer> getPhotos()
    {
        return photos;
    }

    public void setPhotos(ArrayList<Integer> photos)
    {
        this.photos = photos;
    }

    public String getLocalization()
    {
        return localization;
    }

    public void setLocalization(String localization)
    {
        this.localization = localization;
    }

    public int getMaxNumberOfGuests()
    {
        return maxNumberOfGuests;
    }

    public void setMaxNumberOfGuests(int maxNumberOfGuests)
    {
        this.maxNumberOfGuests = maxNumberOfGuests;
    }

    public boolean isCanSleep()
    {
        return canSleep;
    }

    public void setCanSleep(boolean canSleep)
    {
        this.canSleep = canSleep;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }


}
