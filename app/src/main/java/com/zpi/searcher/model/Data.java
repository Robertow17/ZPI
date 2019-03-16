package com.zpi.searcher.model;

import com.zpi.R;
import com.zpi.searcher.model.WeddingHall;

import java.util.ArrayList;

public class Data
{


    private static final ArrayList<Integer> photos = new ArrayList<Integer>()
    {
        {
            add(R.drawable.img1);
            add(R.drawable.img2);
            add(R.drawable.img3);

        }

    };


    private static final ArrayList<WeddingHall> weddingHalls = new ArrayList<WeddingHall>()
    {{
        add(new WeddingHall("Zacisze", "Wrocław", 120, true, false, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis nec odio. Vivamus justo erat, accumsan ut pharetra eu, feugiat mollis ex. Aenean lectus libero", photos,
                "609781153", "psliwinska@onet.eu"));
        add(new WeddingHall("Zajazd u Bożenki", "Warszawa", 300, false, false, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis nec odio. Vivamus justo erat, accumsan ut pharetra eu, feugiat mollis ex. Aenean lectus libero", photos
                , "609781153", "psliwinska@onet.eu"));
        add(new WeddingHall("Dom weselny u Beatki", "Zakopane", 250, false, false, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis nec odio. Vivamus justo erat, accumsan ut pharetra eu, feugiat mollis ex. Aenean lectus libero",
                photos, "609781153", "psliwinska@onet.eu"));
        add(new WeddingHall("Laguna", "Kraków", 120, true, false, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis nec odio. Vivamus justo erat, accumsan ut pharetra eu, feugiat mollis ex. Aenean lectus libero", photos, "609781153"
                , "psliwinska@onet.eu"));

    }};

    public static ArrayList<String> getLocalizations(ArrayList<WeddingHall> weddingHalls)
    {
        ArrayList<String> localizations = new ArrayList<>();
        for(WeddingHall hall : weddingHalls)
        {
            if(!localizations.contains(hall.getLocalization()))
            {
                localizations.add(hall.getLocalization());
            }
        }

        return localizations;
    }


    public static ArrayList<WeddingHall> getWeddingHalls()
    {
        return weddingHalls;
    }
}
