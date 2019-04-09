package com.zpi.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Category implements Parcelable
{

    private String name;
    private List<Subcategory> subcategories;
    private List<Service> services;
 //   private Date createdAt;
 //   private Date updatedAt;


    public Category(String name)
    {
        this.name = name;

    }


    protected Category(Parcel in)
    {
        name = in.readString();
        subcategories = in.createTypedArrayList(Subcategory.CREATOR);
        services = in.createTypedArrayList(Service.CREATOR);

    }

    public static final Creator<Category> CREATOR = new Creator<Category>()
    {
        @Override
        public Category createFromParcel(Parcel in)
        {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size)
        {
            return new Category[size];
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
        dest.writeString(name);
        dest.writeTypedList(subcategories);
        dest.writeTypedList(services);
    }

    public String getName()
    {
        return name;
    }

    public Category(List<Subcategory> subcategories)
    {
        this.subcategories = subcategories;
    }
}
