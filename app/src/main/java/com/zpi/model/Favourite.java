package com.zpi.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Favourite implements Parcelable
{

    private int id;
    private String login;
    private Service service;
    private User user;

    private boolean isFavourite;

   // private Date createdAt;
   // private Date updatedAt;


    public Favourite(String login, Service service, User user, boolean isFavourite)
    {
        this.login = login;
        this.service = service;
        this.user = user;
        this.isFavourite = isFavourite;
    }

    protected Favourite(Parcel in)
    {
        id = in.readInt();
        login = in.readString();
        service = in.readParcelable(Service.class.getClassLoader());
        user = in.readParcelable(User.class.getClassLoader());
        isFavourite = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(id);
        dest.writeString(login);
        dest.writeParcelable(service, flags);
        dest.writeParcelable(user, flags);
        dest.writeByte((byte) (isFavourite ? 1 : 0));
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    public static final Creator<Favourite> CREATOR = new Creator<Favourite>()
    {
        @Override
        public Favourite createFromParcel(Parcel in)
        {
            return new Favourite(in);
        }

        @Override
        public Favourite[] newArray(int size)
        {
            return new Favourite[size];
        }
    };
}
