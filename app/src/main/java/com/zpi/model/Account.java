package com.zpi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Account implements Parcelable
{
    private Service service;
    private User user;

    public Account(Service service, User user)
    {
        this.service = service;
        this.user = user;
    }

    protected Account(Parcel in)
    {
        service = in.readParcelable(Service.class.getClassLoader());
        user = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<Account> CREATOR = new Creator<Account>()
    {
        @Override
        public Account createFromParcel(Parcel in)
        {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size)
        {
            return new Account[size];
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
        dest.writeParcelable(service, flags);
        dest.writeParcelable(user, flags);
    }
}
