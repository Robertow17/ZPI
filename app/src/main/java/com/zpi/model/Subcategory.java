package com.zpi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Subcategory implements Parcelable
{

    private String name;


    public Subcategory(String name)
    {
        this.name = name;
    }

    protected Subcategory(Parcel in)
    {
        name = in.readString();

    }

    public static final Creator<Subcategory> CREATOR = new Creator<Subcategory>()
    {
        @Override
        public Subcategory createFromParcel(Parcel in)
        {
            return new Subcategory(in);
        }

        @Override
        public Subcategory[] newArray(int size)
        {
            return new Subcategory[size];
        }
    };

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(name);
    }


}
