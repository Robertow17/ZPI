package com.zpi.searcher.model;

import java.util.ArrayList;

public interface Service {

     String getLocalization();
     boolean isFavourite();
     String  getName();
     void setFavourite(boolean favourite);
     ArrayList<Integer> getPhotos();

}
