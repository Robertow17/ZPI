package com.zpi.searcher.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.zpi.searcher.utils.Service;

import java.util.ArrayList;

public class WeddingHall implements Service, Parcelable
{

    private String name;
    private String localization;
    private int maxNumberOfGuests;
    private boolean canSleep, isFavourite;
    private String description;
    private ArrayList<Integer> photos;
    private String phoneNumber;
    private String email;
    private String subcategory;



    public WeddingHall(String name, String localization, int maxNumberOfGuests, boolean canSleep,
                       boolean isFavourite, String description, ArrayList<Integer> photos,
                       String phoneNumber, String email)
    {
        this.name = name;
        this.localization = localization;
        this.maxNumberOfGuests = maxNumberOfGuests;
        this.canSleep = canSleep;
        this.isFavourite = isFavourite;
        this.description = description;
        this.photos = photos;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.subcategory = null;
    }

    protected WeddingHall(Parcel in)
    {
        name = in.readString();
        localization = in.readString();
        description = in.readString();
        phoneNumber = in.readString();
        subcategory = in.readString();
        email = in.readString();
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
        dest.writeString(phoneNumber);
        dest.writeString(subcategory);
        dest.writeString(email);
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

    public boolean isCanSleep()
    {
        return canSleep;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getEmail()
    {
        return email;
    }

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

    public boolean canSleep()
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


    public String getSubcategory()
    {
        return subcategory;
    }

    public void setSubcategory(String subcategory)
    {
        this.subcategory = subcategory;
    }
}
