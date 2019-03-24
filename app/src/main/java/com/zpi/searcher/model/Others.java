package com.zpi.searcher.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.zpi.searcher.utils.Service;

import java.util.ArrayList;

public class Others implements Service, Parcelable
{

    private String name;
    private String localization;
    private boolean isFavourite;
    private String description;
    private ArrayList<Integer> photos;
    private String phoneNumber;
    private String email;
    private String subcategory;


    public Others(String name, String localization, boolean isFavourite, String description,
                  ArrayList<Integer> photos, String phoneNumber, String email, String subcategory)
    {
        this.name = name;
        this.localization = localization;
        this.isFavourite = isFavourite;
        this.description = description;
        this.photos = photos;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.subcategory = subcategory;
    }

    protected Others(Parcel in)
    {
        name = in.readString();
        localization = in.readString();
        description = in.readString();
        phoneNumber = in.readString();
        subcategory = in.readString();
        email = in.readString();
        photos = (ArrayList<Integer>) in.readSerializable();
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
        dest.writeSerializable(photos);
        dest.writeByte((byte) (isFavourite ? 1 : 0));
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    public static final Creator<Others> CREATOR = new Creator<Others>()
    {
        @Override
        public Others createFromParcel(Parcel in)
        {
            return new Others(in);
        }

        @Override
        public Others[] newArray(int size)
        {
            return new Others[size];
        }
    };

    @Override
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String getLocalization()
    {
        return localization;
    }

    public void setLocalization(String localization)
    {
        this.localization = localization;
    }

    @Override
    public boolean isFavourite()
    {
        return isFavourite;
    }

    @Override
    public void setFavourite(boolean favourite)
    {
        isFavourite = favourite;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public ArrayList<Integer> getPhotos()
    {
        return photos;
    }

    public void setPhotos(ArrayList<Integer> photos)
    {
        this.photos = photos;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public String getSubcategory()
    {
        return subcategory;
    }

    public void setSubcategory(String subcategory)
    {
        this.subcategory = subcategory;
    }
}
