package com.zpi.searcher.model;

import com.zpi.R;
import com.zpi.searcher.utils.Service;

import java.util.ArrayList;
import java.util.List;

public class Data
{

    private static final ArrayList<Integer> photosWeddingHall = new ArrayList<Integer>()
    {
        {
            add(R.drawable.img1);
            add(R.drawable.img2);
            add(R.drawable.img3);

        }
    };


    private static final ArrayList<Integer> photosTransport = new ArrayList<Integer>()
    {
        {
            add(R.drawable.auto);
            add(R.drawable.auto2);
        }
    };


    private static final ArrayList<Integer> photosFashion = new ArrayList<Integer>()
    {
        {
            add(R.drawable.kosmetyka);
            add(R.drawable.fryzjer);
        }
    };

    private static final ArrayList<Integer> photosDecorations = new ArrayList<Integer>()
    {
        {
            add(R.drawable.dekoracje);
            add(R.drawable.kwiaty);
        }
    };

    private static final ArrayList<Integer> photosOthers = new ArrayList<Integer>()
    {
        {
            add(R.drawable.cukiernia);
            add(R.drawable.cukiernia2);
        }
    };

    private static final ArrayList<Integer> photosPhotography = new ArrayList<Integer>()
    {
        {
            add(R.drawable.fotograf);
            add(R.drawable.fotograf2);
        }
    };

    private static final ArrayList<Integer> photosMusic = new ArrayList<Integer>()
    {
        {
            add(R.drawable.dj);
            add(R.drawable.zespol);
        }
    };


    private static final List<WeddingHall> weddingHalls = new ArrayList<WeddingHall>()
   {{
        add(new WeddingHall("Zacisze", "Wrocław", 120, true, false, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis nec odio. Vivamus justo erat, accumsan ut pharetra eu, feugiat mollis ex. Aenean lectus libero", photosWeddingHall,
                "609781153", "psliwinska@onet.eu"));
        add(new WeddingHall("Zajazd u Bożenki", "Warszawa", 300, false, false, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis nec odio. Vivamus justo erat, accumsan ut pharetra eu, feugiat mollis ex. Aenean lectus libero", photosWeddingHall
                , "609781153", "psliwinska@onet.eu"));
        add(new WeddingHall("Dom weselny u Beatki", "Zakopane", 250, false, false, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis nec odio. Vivamus justo erat, accumsan ut pharetra eu, feugiat mollis ex. Aenean lectus libero", photosWeddingHall, "609781153", "psliwinska@onet.eu"));
        add(new WeddingHall("Laguna", "Kraków", 120, true, false, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis nec odio. Vivamus justo erat, accumsan ut pharetra eu, feugiat mollis ex. Aenean lectus libero", photosWeddingHall, "609781153"
                , "psliwinska@onet.eu"));

    }};


    private static final List<Transport> transports = new ArrayList<Transport>()
    {{
       add(new Transport("Warszawa", "Zielona Góra", true, "Lorem ipsum dolor sit amet, " +
               "consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis" +
               " nec odio. Vivamus justo erat", photosTransport, "609781153", "psliwinska@onet.eu", "Samochody zabytkowe", 4));
        add(new Transport("Syrenka", "Lublin", false, "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis" +
                " nec odio. Vivamus justo erat", photosTransport, "609781153", "psliwinska@onet.eu", "Samochody zabytkowe", 4));
        add(new Transport("Limuzyna", "Szczecin", true, "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis" +
                " nec odio. Vivamus justo erat", photosTransport, "609781153", "psliwinska@onet.eu", "Limuzyna", 4));
        add(new Transport("Traktor Heniek", "Głogów", true, "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis" +
                " nec odio. Vivamus justo erat", photosTransport, "609781153", "psliwinska@onet.eu", "Inne pojazdy", 4));
    }};


    private static  final List<Photography> photographs = new ArrayList<Photography>()
    {{
        add(new Photography("Fotografia ślubna", "Zielona Góra", true, "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis" +
                " nec odio. Vivamus justo erat", photosPhotography, "609781153", "psliwinska@onet.eu"));
        add(new Photography("Zdjęcia i video", "Lublin", false, "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis" +
                " nec odio. Vivamus justo erat", photosPhotography, "609781153", "psliwinska@onet.eu"));
        add(new Photography("Fotograf okolicznościowy", "Szczecin", true, "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis" +
                " nec odio. Vivamus justo erat", photosPhotography, "609781153", "psliwinska@onet.eu"));

    }};

    private static final List<Others> others = new ArrayList<Others>()
    {{
        add(new Others("Catering ślubny", "Zielona Góra", true, "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis" +
                " nec odio. Vivamus justo erat", photosOthers, "609781153", "psliwinska@onet.eu", "Catering"));
        add(new Others("Cukiernia pod Dębem", "Lublin", false, "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis" +
                " nec odio. Vivamus justo erat", photosOthers, "609781153", "psliwinska@onet.eu", "Cukiernia"));
        add(new Others("Gołębie", "Szczecin", true, "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis" +
                " nec odio. Vivamus justo erat", photosOthers, "609781153", "psliwinska@onet.eu", "Inne"));

    }};


    private static final List<Music> musicians = new ArrayList<Music>()
    {{
        add(new Music("Catering ślubny", "Zielona Góra", true, "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis" +
                " nec odio. Vivamus justo erat", photosMusic, "609781153", "psliwinska@onet.eu", "DJ"));
        add(new Music("Cukiernia pod Dębem", "Lublin", false, "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis" +
                " nec odio. Vivamus justo erat", photosMusic, "609781153", "psliwinska@onet.eu", "Zespół weselny"));
        add(new Music("Gołębie", "Szczecin", true, "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis" +
                " nec odio. Vivamus justo erat", photosMusic, "609781153", "psliwinska@onet.eu", "DJ"));
    }};


    private static final List<Fashion> fashion = new ArrayList<Fashion>()
    {{
        add(new Fashion("Fryzjerka Beata", "Zielona Góra", true, "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis" +
                " nec odio. Vivamus justo erat", photosFashion, "609781153", "psliwinska@onet.eu", "Fryzjer"));
        add(new Fashion("Centrum urody", "Lublin", false, "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis" +
                " nec odio. Vivamus justo erat", photosFashion, "609781153", "psliwinska@onet.eu", "Kosmetyczka"));
        add(new Fashion("Akcesoria ślubne", "Szczecin", true, "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis" +
                " nec odio. Vivamus justo erat", photosFashion, "609781153", "psliwinska@onet.eu", "Dodatki"));

    }};


    private static final List<Decorations> decorations = new ArrayList<Decorations>()
    {{
        add(new Decorations("Ozdoby ślubne", "Zielona Góra", true, "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis" +
                " nec odio. Vivamus justo erat", photosDecorations, "609781153", "psliwinska@onet.eu", "Dekoracje weselne"));
        add(new Decorations("Florysta okolicznościowy", "Lublin", false, "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis" +
                " nec odio. Vivamus justo erat", photosDecorations, "609781153", "psliwinska@onet.eu", "Florystyka"));
        add(new Decorations("Kwiaty na zamówienie", "Szczecin", true, "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis" +
                " nec odio. Vivamus justo erat", photosDecorations, "609781153", "psliwinska@onet.eu", "Florystyka"));
    }};



    public static List<String> getLocalizations(List<? extends Service> services)
    {
        List<String> localizations = new ArrayList<>();
        for(Service service : services)
        {
            if(!localizations.contains(service.getLocalization()))
            {
                localizations.add(service.getLocalization());
            }
        }

        return localizations;
    }

    public static List<String> getSubcategories(List<? extends Service> services)
    {
        List<String> localizations = new ArrayList<>();
        for(Service service : services)
        {
            if(!localizations.contains(service.getSubcategory()))
            {
                localizations.add(service.getSubcategory());
            }
        }

        return localizations;
    }




    public static List<Transport> getTransports()
    {
        return transports;
    }

    public static List<Photography> getPhotographs()
    {
        return photographs;
    }

    public static List<Others> getOthers()
    {
        return others;
    }

    public static List<Music> getMusicians()
    {
        return musicians;
    }

    public static List<Fashion> getFashion()
    {
        return fashion;
    }

    public static List<Decorations> getDecorations()
    {
        return decorations;
    }

    public static List<WeddingHall> getWeddingHalls()
    {
        return weddingHalls;
    }
}
