package com.zpi.searcher.model;

import android.os.Parcelable;

import java.util.ArrayList;

public abstract class Service implements Parcelable
{
    public abstract String getLocalization();
    public abstract boolean isFavourite();
    public abstract String  getName();
    public abstract void setFavourite(boolean favourite);
    public abstract ArrayList<Integer> getPhotos();
    public abstract String getSubcategory();

}
