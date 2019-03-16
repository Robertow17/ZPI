package com.zpi.searcher.model;

import com.zpi.R;
import com.zpi.searcher.model.WeddingHall;

import java.util.ArrayList;

public class Data
{


    private static final ArrayList<Integer> photos = new ArrayList<Integer>()
    {{
      add(R.drawable.ic_launcher_background);
      add(R.drawable.ic_launcher_background);
      add(R.drawable.ic_launcher_background);

    }

    };

    private static final ArrayList<WeddingHall> weddingHalls = new ArrayList<WeddingHall>()
    {{
        add(new WeddingHall("name", "localization", 120, true, "rgrgrgrg", photos));
        add(new WeddingHall("name", "localization", 120, true, "rgrgrgrg", photos));
        add(new WeddingHall("name", "localization", 120, true, "rgrgrgrg", photos));
        add(new WeddingHall("name", "localization", 120, true, "rgrgrgrg", photos));
        add(new WeddingHall("name", "localization", 120, true, "rgrgrgrg", photos));
        add(new WeddingHall("name", "localization", 120, true, "rgrgrgrg", photos));

    }};


    public static ArrayList<WeddingHall> getWeddingHalls()
    {
        return weddingHalls;
    }
}
