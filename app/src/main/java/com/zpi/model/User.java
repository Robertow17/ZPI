package com.zpi.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.zpi.model.enums.Gender;
import com.zpi.model.enums.UserType;

import java.util.ArrayList;

import java.util.List;

public class User implements Parcelable
{

    private String login;
    private String password;
    private Gender gender;
    private UserType type;
    private List<Favourite> favourites;

  //  private Date createdAt;
  //  private Date updatedAt;

    public User(String login, String password, Gender gender, UserType type)
    {
        this.login = login;
        this.password = password;
        this.gender = gender;
        this.type = type;
    }

    protected User(Parcel in)
    {
        login = in.readString();
        password = in.readString();
        gender = Gender.values()[in.readInt()];
        type = UserType.values()[in.readInt()];
        favourites = in.createTypedArrayList(Favourite.CREATOR);
    }

    public static final Creator<User> CREATOR = new Creator<User>()
    {
        @Override
        public User createFromParcel(Parcel in)
        {
            return new User(in);
        }

        @Override
        public User[] newArray(int size)
        {
            return new User[size];
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
        dest.writeString(login);
        dest.writeString(password);
        dest.writeInt(gender.ordinal());
        dest.writeInt(type.ordinal());
        dest.writeTypedList(favourites);
    }
}
