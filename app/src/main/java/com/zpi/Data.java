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


    private static List<Subcategory> subcategoriesTansport = new ArrayList<Subcategory>()
    {{
        add(new Subcategory("Limuzyna"));
        add(new Subcategory("Samochody zabytkowe"));
        add(new Subcategory("Inne pojazdy"));

    }};

    private static List<Subcategory> subcategoriesMusic = new ArrayList<Subcategory>()
    {{
        add(new Subcategory("Zespół weselny"));
        add(new Subcategory("DJ"));


    }};

    private static List<Subcategory> subcategoriesDecorations = new ArrayList<Subcategory>()
    {{
        add(new Subcategory("Dekoracje weselne"));
        add(new Subcategory("Florystyka"));

    }};

    private static List<Subcategory> subcategoriesFashion = new ArrayList<Subcategory>()
    {{
        add(new Subcategory("Fryzjer"));
        add(new Subcategory("Kosmetyczka"));
        add(new Subcategory("Suknie ślubne"));
        add(new Subcategory("Garnitury"));
        add(new Subcategory("Dodatki"));
    }};

    private static List<Subcategory> subcategoriesOthers = new ArrayList<Subcategory>()
    {{
        add(new Subcategory("Jubiler"));
        add(new Subcategory("Catering"));
        add(new Subcategory("Cukiernia"));
        add(new Subcategory("Szkoła tańca"));
    }};

    private static List<Category> categories = new ArrayList<Category>()
    {{
        add(new Category("SALE"));
        add(new Category("TRANSPORT", subcategoriesTansport));
        add(new Category("FOTOGRAFIA"));
        add(new Category("MODA", subcategoriesFashion));
        add(new Category("INNE", subcategoriesOthers));
        add(new Category("MUZYKA", subcategoriesMusic));
        add(new Category("DEKORACJE", subcategoriesDecorations));

    }};

    private static final List<WeddingHallDetails> weddingHallDetails =
            new ArrayList<WeddingHallDetails>()
    {{
        add(new WeddingHallDetails(true, 100));
    }};

    private static final List<TransportDetails> transportDetails = new ArrayList<TransportDetails>()
    {{
        add(new TransportDetails(5));
    }};

    //wedding halls
    private static final List<Photo> photos1 = new ArrayList<Photo>()
    {{
        add(new Photo((R.drawable.img1)));
        add(new Photo((R.drawable.img2)));
        add(new Photo((R.drawable.img3)));
    }};

    private static final List<Photo> photos2 = new ArrayList<Photo>()
    {{
        add(new Photo((R.drawable.img3)));
        add(new Photo((R.drawable.img2)));
        add(new Photo((R.drawable.img1)));
    }};
    private static final List<Photo> photos3 = new ArrayList<Photo>()
    {{
        add(new Photo((R.drawable.img2)));
        add(new Photo((R.drawable.img1)));
        add(new Photo((R.drawable.img3)));
    }};

    private static final List<Photo> photos4 = new ArrayList<Photo>()
    {{
        add(new Photo(R.drawable.img1));
        add(new Photo(R.drawable.img2));
        add(new Photo(R.drawable.img3));
    }};

    //music
    private static final List<Photo> photos5 = new ArrayList<Photo>()
    {{
        add(new Photo(R.drawable.zespol));

    }};

    private static final List<Photo> photos6 = new ArrayList<Photo>()
    {{
        add(new Photo(R.drawable.dj));

    }};

    //photography
    private static final List<Photo> photos7 = new ArrayList<Photo>()
    {{
        add(new Photo(R.drawable.fotograf));
        add(new Photo(R.drawable.fotograf2));

    }};
    private static final List<Photo> photos8 = new ArrayList<Photo>()
    {{
        add(new Photo(R.drawable.fotograf2));
        add(new Photo(R.drawable.fotograf));

    }};

    //decorations
    private static final List<Photo> photos9 = new ArrayList<Photo>()
    {{
        add(new Photo(R.drawable.kwiaty));
        add(new Photo(R.drawable.dekoracje));

    }};

    private static final List<Photo> photos10 = new ArrayList<Photo>()
    {{
        add(new Photo(R.drawable.img2));
        add(new Photo(R.drawable.kwiaty));

    }};

    //transport
    private static final List<Photo> photos11 = new ArrayList<Photo>()
    {{
        add(new Photo(R.drawable.auto));
        add(new Photo(R.drawable.auto2));

    }};
    private static final List<Photo> photos12 = new ArrayList<Photo>()
    {{
        add(new Photo(R.drawable.auto2));
        add(new Photo(R.drawable.auto));

    }};

    //others
    private static final List<Photo> photos15 = new ArrayList<Photo>()
    {{
        add(new Photo((R.drawable.cukiernia)));


    }};
    private static final List<Photo> photos16 = new ArrayList<Photo>()
    {{
        add(new Photo((R.drawable.cukiernia2)));
        add(new Photo((R.drawable.cukiernia)));

    }};

    //fashion
    private static final List<Photo> photos13 = new ArrayList<Photo>()
    {{
        add(new Photo((R.drawable.kosmetyka)));


    }};
    private static final List<Photo> photos14 = new ArrayList<Photo>()
    {{
        add(new Photo((R.drawable.fryzjer)));


    }};

    private static List<Service> services = new ArrayList<Service>()
    {{


        //wedding halls
        add(new Service("Zacisze", "Wrocław", "Lorem ipsum dolor sit amet, consectetur " +
                "adipiscing" + " elit. Nullam fermentum eu felis sed fermentum. Duis sapien leo, "
                + "mollis et " + "condimentum dapibus, malesuada sed sapien. Nunc posuere leo " + "sagittis gravida " + "tristique. Curabitur vestibulum libero et risus suscipit " + "auctor. Nullam et urna " + "sed odio mollis aliquam id a odio. Duis consectetur " + "semper ipsum, ut interdum " + "massa ultrices at. Proin molestie ante augue, " + "semper pharetra velit cursus at. ", "609781153", "psliwinska@onet.eu", categories.get(0), null, weddingHallDetails.get(0), null, photos1));
        add(new Service("Pod sosnami", "Lublin", "Lorem ipsum dolor sit amet, consectetur " +
                "adipiscing elit. Nullam fermentum eu felis sed fermentum. Duis sapien leo, " +
                "mollis et condimentum dapibus, malesuada sed sapien. Nunc posuere leo sagittis " + "gravida tristique. Curabitur vestibulum libero et risus suscipit auctor. Nullam " + "et urna sed odio mollis aliquam id a odio. Duis consectetur semper ipsum, ut " + "interdum massa ultrices at. Proin molestie ante augue, semper pharetra velit " + "cursus at. ", "515259863", "psliwinska@onet.eu", categories.get(0), null, weddingHallDetails.get(0), null, photos2));
        add(new Service("U babuni", "Gdynia", "Lorem ipsum dolor sit amet, consectetur " +
                "adipiscing" + " elit. Nullam fermentum eu felis sed fermentum. Duis sapien leo, "
                + "mollis et " + "condimentum dapibus, malesuada sed sapien. Nunc posuere leo " + "sagittis gravida " + "tristique. Curabitur vestibulum libero et risus suscipit " + "auctor. Nullam et urna " + "sed odio mollis aliquam id a odio. Duis consectetur " + "semper ipsum, ut interdum " + "massa ultrices at. Proin molestie ante augue, " + "semper pharetra velit cursus at. ", "678425145", "psliwinska@onet.eu", categories.get(0), null, weddingHallDetails.get(0), null, photos3));
        add(new Service("Dzika Hańcza", "Mikołajów",
                "Lorem ipsum dolor sit amet, consectetur " + "adipiscing elit. Nullam fermentum " + "eu felis sed fermentum. Duis sapien leo, " + "mollis et condimentum " + "dapibus, malesuada sed sapien. Nunc posuere leo sagittis " + "gravida " + "tristique. Curabitur vestibulum libero et risus suscipit auctor. Nullam " + "et urna sed odio mollis aliquam id a odio. Duis consectetur semper " + "ipsum, ut " + "interdum massa ultrices at. Proin molestie ante augue, " + "semper pharetra velit " + "cursus at. ", "691752863", "psliwinska@onet" + ".eu", categories.get(0), null, weddingHallDetails.get(0), null, photos4));
        //music
        add(new Service("DJ Fokus", "Warszawa", "Lorem ipsum dolor sit amet, consectetur " +
                "adipiscing elit. Nullam fermentum eu felis sed fermentum. Duis sapien leo, " +
                "mollis et condimentum dapibus, malesuada sed sapien. Nunc posuere leo sagittis " + "gravida tristique. Curabitur vestibulum libero et risus suscipit auctor. Nullam " + "et urna sed odio mollis aliquam id a odio. Duis consectetur semper ipsum, ut " + "interdum massa ultrices at. Proin molestie ante augue, semper pharetra velit " + "cursus at. ", "609781153", "psliwinska@onet.eu", categories.get(5), categories.get(5).getSubcategories().get(1), null, null, photos6));
        add(new Service("Zaspół Akcent", "Sosnowiec",
                "Lorem ipsum dolor sit amet, consectetur " + "adipiscing elit. Nullam fermentum " + "eu felis sed fermentum. Duis sapien leo, " + "mollis et condimentum " + "dapibus, malesuada sed sapien. Nunc posuere leo sagittis " + "gravida " + "tristique. Curabitur vestibulum libero et risus suscipit auctor. Nullam " + "et urna sed odio mollis aliquam id a odio. Duis consectetur semper " + "ipsum, ut " + "interdum massa ultrices at. Proin molestie ante augue, " + "semper pharetra velit " + "cursus at. ", "515259863", "psliwinska@onet" + ".eu", categories.get(5), categories.get(5).getSubcategories().get(0), null, null, photos5));
        //photography
        add(new Service("Fotografia weselna", "Białystok", "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Nullam fermentum eu felis sed fermentum. Duis " +
                "sapien leo, mollis et condimentum dapibus, malesuada sed sapien. Nunc posuere " + "leo sagittis gravida tristique. Curabitur vestibulum libero et risus suscipit " + "auctor. Nullam et urna sed odio mollis aliquam id a odio. Duis consectetur " + "semper ipsum, ut interdum massa ultrices at. Proin molestie ante augue, semper " + "pharetra velit cursus at. ", "609781153", "psliwinska@onet" + ".eu", categories.get(2), null, null, null, photos7));
        add(new Service("Zdjecia okolicznościowe", "Kraków", "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Nullam fermentum eu felis sed fermentum. Duis " +
                "sapien leo, mollis et condimentum dapibus, malesuada sed sapien. Nunc posuere " + "leo sagittis gravida tristique. Curabitur vestibulum libero et risus suscipit " + "auctor. Nullam et urna sed odio mollis aliquam id a odio. Duis consectetur " + "semper ipsum, ut interdum massa ultrices at. Proin molestie ante augue, semper " + "pharetra velit cursus at. ", "515259863", "psliwinska" + "@onet.eu", categories.get(2), null, null, null, photos8));
        add(new Service("Fotograf od zaraz", "Warszawa", "Lorem ipsum dolor sit amet, " +
                "consectetur" + " adipiscing elit. Nullam fermentum eu felis sed fermentum. Duis "
                + "sapien leo, " + "mollis et condimentum dapibus, malesuada sed sapien. Nunc " + "posuere leo sagittis " + "gravida tristique. Curabitur vestibulum libero et " + "risus suscipit auctor. Nullam " + "et urna sed odio mollis aliquam id a odio. " + "Duis consectetur semper ipsum, ut " + "interdum massa ultrices at. Proin " + "molestie ante augue, semper pharetra velit " + "cursus at. ", "609781153", "psliwinska@onet" + ".eu", categories.get(2), null, null, null, photos7));

        //decorations
        add(new Service("Kwiaty weselne", "Wrocław",
                "Lorem ipsum dolor sit amet, consectetur " + "adipiscing elit. Nullam fermentum " + "eu felis sed fermentum. Duis sapien leo, " + "mollis et condimentum " + "dapibus, malesuada sed sapien. Nunc posuere leo sagittis " + "gravida " + "tristique. Curabitur vestibulum libero et risus suscipit auctor. Nullam " + "et urna sed odio mollis aliquam id a odio. Duis consectetur semper " + "ipsum, ut " + "interdum massa ultrices at. Proin molestie ante augue, " + "semper pharetra velit " + "cursus at. ", "609781153", "psliwinska@onet" + ".eu", categories.get(6), categories.get(6).getSubcategories().get(1), null, null, photos9));
        add(new Service("Dekoracje okolicznościowe", "Sosnowiec", "Opis", "515259863",
                "psliwinska@onet.eu", categories.get(6),
                categories.get(6).getSubcategories().get(0), null, null, photos10));
        add(new Service("Florystyka", "Lubin", "Lorem ipsum dolor sit amet, consectetur " +
                "adipiscing elit. Nullam fermentum eu felis sed fermentum. Duis sapien leo, " +
                "mollis et condimentum dapibus, malesuada sed sapien. Nunc posuere leo sagittis " + "gravida tristique. Curabitur vestibulum libero et risus suscipit auctor. Nullam " + "et urna sed odio mollis aliquam id a odio. Duis consectetur semper ipsum, ut " + "interdum massa ultrices at. Proin molestie ante augue, semper pharetra velit " + "cursus at. ", "609781153", "psliwinska@onet.eu", categories.get(6), categories.get(6).getSubcategories().get(1), null, null, photos9));

        //transport
        add(new Service("Limuzyna", "Smolec", "Lorem ipsum dolor sit amet, consectetur " +
                "adipiscing" + " elit. Nullam fermentum eu felis sed fermentum. Duis sapien leo, "
                + "mollis et " + "condimentum dapibus, malesuada sed sapien. Nunc posuere leo " + "sagittis gravida " + "tristique. Curabitur vestibulum libero et risus suscipit " + "auctor. Nullam et urna " + "sed odio mollis aliquam id a odio. Duis consectetur " + "semper ipsum, ut interdum " + "massa ultrices at. Proin molestie ante augue, " + "semper pharetra velit cursus at. ", "609781153", "psliwinska@onet.eu", categories.get(1), categories.get(1).getSubcategories().get(0), null, transportDetails.get(0), photos12));
        add(new Service("Samochód na wesele", "Oława",
                "Lorem ipsum dolor sit amet, consectetur " + "adipiscing elit. Nullam fermentum " + "eu felis sed fermentum. Duis sapien leo, " + "mollis et condimentum " + "dapibus, malesuada sed sapien. Nunc posuere leo sagittis " + "gravida " + "tristique. Curabitur vestibulum libero et risus suscipit auctor. Nullam " + "et urna sed odio mollis aliquam id a odio. Duis consectetur semper " + "ipsum, ut " + "interdum massa ultrices at. Proin molestie ante augue, " + "semper pharetra velit " + "cursus at. ", "515259863", "psliwinska@onet" + ".eu", categories.get(1), categories.get(1).getSubcategories().get(1), null, transportDetails.get(0), photos11));
        add(new Service("Warszawa", "Gdynia", "Lorem ipsum dolor sit amet, consectetur " +
                "adipiscing" + " elit. Nullam fermentum eu felis sed fermentum. Duis sapien leo, "
                + "mollis et " + "condimentum dapibus, malesuada sed sapien. Nunc posuere leo " + "sagittis gravida " + "tristique. Curabitur vestibulum libero et risus suscipit " + "auctor. Nullam et urna " + "sed odio mollis aliquam id a odio. Duis consectetur " + "semper ipsum, ut interdum " + "massa ultrices at. Proin molestie ante augue, " + "semper pharetra velit cursus at. ", "609781153", "psliwinska@onet.eu", categories.get(1), categories.get(1).getSubcategories().get(1), null, transportDetails.get(0), photos12));

        //fashion
        add(new Service("Fryzjer", "Zakopane", "Lorem ipsum dolor sit amet, consectetur " +
                "adipiscing elit. Nullam fermentum eu felis sed fermentum. Duis sapien leo, " +
                "mollis et condimentum dapibus, malesuada sed sapien. Nunc posuere leo sagittis " + "gravida tristique. Curabitur vestibulum libero et risus suscipit auctor. Nullam " + "et urna sed odio mollis aliquam id a odio. Duis consectetur semper ipsum, ut " + "interdum massa ultrices at. Proin molestie ante augue, semper pharetra velit " + "cursus at. ", "609781153", "psliwinska@onet.eu", categories.get(3), categories.get(3).getSubcategories().get(0), null, null, photos14));
        add(new Service("Kosmetyczka Beatka", "Jarosławiec", "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Nullam fermentum eu felis sed fermentum. Duis " +
                "sapien leo, mollis et condimentum dapibus, malesuada sed sapien. Nunc posuere " + "leo sagittis gravida tristique. Curabitur vestibulum libero et risus suscipit " + "auctor. Nullam et urna sed odio mollis aliquam id a odio. Duis consectetur " + "semper ipsum, ut interdum massa ultrices at. Proin molestie ante augue, semper " + "pharetra velit cursus at. ", "515259863", "psliwinska" + "@onet.eu", categories.get(3), categories.get(3).getSubcategories().get(1), null, null, photos13));

        //others
        add(new Service("Cukiernia okolicznościowa", "Warszawa",
                "Lorem ipsum dolor sit amet, " + "consectetur adipiscing elit. Nullam fermentum " + "eu felis sed fermentum. Duis " + "sapien leo, mollis et condimentum " + "dapibus, malesuada sed sapien. Nunc posuere " + "leo sagittis gravida " + "tristique. Curabitur vestibulum libero et risus suscipit " + "auctor. " + "Nullam et urna sed odio mollis aliquam id a odio. Duis consectetur " + "semper ipsum, ut interdum massa ultrices at. Proin molestie ante augue, " + "semper " + "pharetra velit cursus at. ", "609781153", "psliwinska" + "@onet.eu", categories.get(4), categories.get(4).getSubcategories().get(2), null, null, photos15));
        add(new Service("Catering", "Sosnowiec", "Lorem ipsum dolor sit amet, consectetur " +
                "adipiscing elit. Nullam fermentum eu felis sed fermentum. Duis sapien leo, " +
                "mollis et condimentum dapibus, malesuada sed sapien. Nunc posuere leo sagittis " + "gravida tristique. Curabitur vestibulum libero et risus suscipit auctor. Nullam " + "et urna sed odio mollis aliquam id a odio. Duis consectetur semper ipsum, ut " + "interdum massa ultrices at. Proin molestie ante augue, semper pharetra velit " + "cursus at. ", "515259863", "psliwinska@onet.eu", categories.get(4), categories.get(4).getSubcategories().get(1), null, null, photos16));
        add(new Service("Jubiler", "Kempno", "Lorem ipsum dolor sit amet, consectetur adipiscing " +
                "elit. Nullam fermentum eu felis sed fermentum. Duis sapien leo, mollis et " +
                "condimentum dapibus, malesuada sed sapien. Nunc posuere leo sagittis gravida " +
                "tristique. Curabitur vestibulum libero et risus suscipit auctor. Nullam et urna " +
                "sed odio mollis aliquam id a odio. Duis consectetur semper ipsum, ut interdum " +
                "massa ultrices at. Proin molestie ante augue, semper pharetra velit cursus at. "
                , "609781153", "psliwinska@onet.eu", categories.get(4),
                categories.get(4).getSubcategories().get(0), null, null, photos13));


    }};

    ////////////////////////////////////////////////////////////////////////////////////////////////////////

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


    public static List<Service> getServices(String categoryName)
    {
        List<Service> serviceList = new ArrayList<>();
        for(Service service : services)
        {
            if(service.getCategory().getName().equals(categoryName))
            {
                serviceList.add(service);
            }
        }

        return serviceList;
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

    public static void setSubcategories()
    {
        categories.get(1).setSubcategories(subcategoriesTansport);
    }

    public static List<Category> getCategories()
    {
        return categories;
    }

    public static List<Subcategory> getSubcategoriesList()
    {
        return subcategoriesTansport;
    }


}
