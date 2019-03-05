package com.zpi.searcher.model;

import com.zpi.searcher.model.WeddingHall;

import java.util.ArrayList;

public class Data
{
    private static final ArrayList<WeddingHall> weddingHalls = new ArrayList<WeddingHall>()
    {{
        add(new WeddingHall("lala"));
        add(new WeddingHall("lala"));
        add(new WeddingHall("lala"));
        add(new WeddingHall("lala"));
        add(new WeddingHall("lala"));
    }};


    public static ArrayList<WeddingHall> getWeddingHalls()
    {
        return weddingHalls;
    }
}
