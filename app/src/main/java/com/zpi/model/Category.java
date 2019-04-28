package com.zpi.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


public class Category implements Parcelable
{

    private String name;
    private List<Subcategory> subcategories;


    public Category(String name, List<Subcategory> subcategories)
    {
        this.name = name;
        this.subcategories = subcategories;

    }

    public Category(String name)
    {
        this.name = name;
        this.subcategories = null;

    }

    protected Category(Parcel in)
    {
        name = in.readString();
        subcategories = in.createTypedArrayList(Subcategory.CREATOR);

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

    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Subcategory> getSubcategories()
    {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories)
    {
        this.subcategories = subcategories;
    }
}
