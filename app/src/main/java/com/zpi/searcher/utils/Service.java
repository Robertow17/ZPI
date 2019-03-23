package com.zpi.searcher.utils;

import java.util.ArrayList;

public interface Service
{

     String getLocalization();
     boolean isFavourite();
     String  getName();
     void setFavourite(boolean favourite);
     ArrayList<Integer> getPhotos();
     String getSubcategory();

}
