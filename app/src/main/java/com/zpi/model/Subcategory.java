package com.zpi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Subcategory implements Parcelable
{

    private String name;
    private List<Service> services;
    private Category category;

  //  private Date createdAt;
   // private Date updatedAt;


    public Subcategory(String name, Category category)
    {
        this.name = name;
        this.category = category;
    }

    protected Subcategory(Parcel in)
    {
        name = in.readString();
        services = in.createTypedArrayList(Service.CREATOR);
        category =  in.readParcelable(Category.class.getClassLoader());
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
        dest.writeTypedList(services);
        dest.writeParcelable(this.category, flags);
    }

    public Category getCategory()
    {
        return category;
    }
}
