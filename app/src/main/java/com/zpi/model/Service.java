package com.zpi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Service implements Parcelable
{
    private int id;
    private String name;
    private String localization;
    private String description;
    private String phoneNumber;
    private String email;
    private Subcategory subcategory;
    private Category category;
    private WeddingHallDetails weddingHallDetails;
    private TransportDetails transportDetails;
    private List<Photo> photos;



    public Service(String name, String localization, String description,
                   String phoneNumber, String email, Category category, Subcategory subcategory,
                   WeddingHallDetails weddingHallDetails, TransportDetails transportDetails,
                   List<Photo> photos)
    {
        this.name = name;
        this.localization = localization;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.subcategory = subcategory;
        this.category = category;
        this.weddingHallDetails = weddingHallDetails;
        this.transportDetails = transportDetails;
        this.photos = photos;
    }

    public void editService(String name, String localization, String description,
                            String phoneNumber, String email, Subcategory subcategory, Category category,
                            WeddingHallDetails weddingHallDetails, TransportDetails transportDetails,
                            List<Photo> photos){
        this.name = name;
        this.localization = localization;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.subcategory = subcategory;
        this.category = category;
        this.weddingHallDetails = weddingHallDetails;
        this.transportDetails = transportDetails;
        this.photos = photos;
    }


    protected Service(Parcel in)
    {
        id = in.readInt();
        name = in.readString();
        localization = in.readString();
        description = in.readString();
        phoneNumber = in.readString();
        email = in.readString();
        subcategory =  in.readParcelable(Subcategory.class.getClassLoader());
        category =  in.readParcelable(Category.class.getClassLoader());
        weddingHallDetails = in.readParcelable(WeddingHallDetails.class.getClassLoader());
        transportDetails = in.readParcelable(TransportDetails.class.getClassLoader());
        photos = in.createTypedArrayList(Photo.CREATOR);

    }


    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(localization);
        dest.writeString(description);
        dest.writeString(phoneNumber);
        dest.writeString(email);
        dest.writeParcelable(this.subcategory, flags);
        dest.writeParcelable(this.category, flags);
        dest.writeParcelable(this.weddingHallDetails, flags);
        dest.writeParcelable(this.transportDetails, flags);
        dest.writeTypedList(this.photos);
    }

    public static final Creator<Service> CREATOR = new Creator<Service>()
    {
        @Override
        public Service createFromParcel(Parcel in)
        {
            return new Service(in);
        }

        @Override
        public Service[] newArray(int size)
        {
            return new Service[size];
        }
    };

    @Override
    public int describeContents()
    {
        return 0;
    }

    public String getName()
    {
        return name;
    }

    public String getLocalization()
    {
        return localization;
    }

    public String getDescription()
    {
        return description;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public WeddingHallDetails getWeddingHallDetails()
    {
        return weddingHallDetails;
    }

    public TransportDetails getTransportDetails()
    {
        return transportDetails;
    }

    public List<Photo> getPhotos()
    {
        return photos;
    }


    public Category getCategory()
    {
        return category;
    }

    public Subcategory getSubcategory()
    {
        return subcategory;
    }

    public int getId()
    {
        return id;
    }

}
