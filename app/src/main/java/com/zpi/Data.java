package com.zpi;

import android.util.Log;

import com.zpi.model.Category;
import com.zpi.model.Photo;
import com.zpi.model.Service;
import com.zpi.model.Subcategory;
import com.zpi.model.TransportDetails;
import com.zpi.model.WeddingHallDetails;

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

////////////////////////////////
    private static List<Category> categories = new ArrayList<Category>(){{
       add(new Category("SALE"));
       add(new Category("TRANSPORT"));
       add(new Category("FOTOGRAFIA"));
       add(new Category("MODA"));
       add(new Category("INNE"));
       add(new Category("MUZYKA"));
       add(new Category("DEKORACJE"));

    }};


    private static List<Subcategory> subcategories = new ArrayList<Subcategory>(){{
        add(new Subcategory("Limuzyna", categories.get(1)));
        add(new Subcategory("Samochody zabytkowe", categories.get(1)));
        add(new Subcategory("Inne pojazdy", categories.get(1)));

    }};

    static List<WeddingHallDetails> weddingHallDetails = new ArrayList<WeddingHallDetails>(){{
        add(new WeddingHallDetails(true, 100));
    }};


    static List<Photo> photos = new ArrayList<Photo>(){{
        add(new Photo(String.valueOf(R.drawable.kosmetyka)));
        add(new Photo(String.valueOf(R.drawable.dj)));
        add(new Photo(String.valueOf(R.drawable.zespol)));
    }};


    private static List<Service> services = new ArrayList<Service>(){{
       add(new Service("Zacisze", "Wrocław", "Opis", "609781153", "psliwinska@onet.eu",  subcategories.get(0), categories.get(0),null, null,  photos));
       add(new Service("Zacisze", "Wrocław", "Opis", "609781153", "psliwinska@onet.eu", subcategories.get(1), categories.get(1),weddingHallDetails.get(0), null, photos));
       add(new Service("Zacisze", "Wrocław", "Opis", "609781153", "psliwinska@onet.eu", subcategories.get(1),categories.get(0),null, null, photos));
       add(new Service("Zacisze", "Wrocław", "Opis", "609781153", "psliwinska@onet.eu", subcategories.get(1), categories.get(2),null, null, photos));

    }};

    public static List<Service> getTransports()
    {
        List<Service> transports = new ArrayList<>();
        for(Service service : services)
        {
            if(service.getCategory().getName().equals("TRANSPORT"))
            {
                transports.add(service);
            }
        }
        Log.d("TRANSPORT", String.valueOf(transports.size()));
        return transports;
    }

    public static List<Service> getWeddingHalls()
    {
        List<Service> weddingHalls = new ArrayList<>();
        for(Service service : services)
        {
            if(service.getCategory().getName().equals("SALE"))
            {
                weddingHalls.add(service);
            }
        }

        return weddingHalls;
    }


    public static List<Service> getServices()
    {
        return services;
    }


  public static List<String> getLocalizations(List<Service> services)
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

    public static List<String> getSubcategories(List<Service> services)
    {
        List<String> localizations = new ArrayList<>();
        for(Service service : services)
        {
            if(!localizations.contains(service.getSubcategory()))
            {
                localizations.add(service.getSubcategory().getName());
            }
        }

        return localizations;
    }



}
