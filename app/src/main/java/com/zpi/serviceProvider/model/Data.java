package com.zpi.serviceProvider.model;

import com.zpi.R;
import com.zpi.ServerConnector.ServerConnector;
import com.zpi.ServerConnector.ServerConnectorUtils;
import com.zpi.ServerConnector.ServiceName;
import com.zpi.model.Service;


import java.util.ArrayList;
import java.util.List;

public class Data {
    private ServiceProvider serviceProvider;

    public Data() {
        //if(serviceProvider==null){
            createServiceProvider();
        //}
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    private void createServiceProvider(){
        ArrayList<Integer> photos = new ArrayList<Integer>()
        {
            {
                add(R.drawable.img1);
                add(R.drawable.img2);
                add(R.drawable.img3);

            }

        };
        serviceProvider = new ServiceProvider("zielu@o2.pl", "P@ssw0rd");
        ServerConnector utils = new ServerConnector(ServiceName.services);
        List<Service> services = utils.getAll();
        serviceProvider.setServices(services);

  /*      serviceProvider.addService(new WeddingHall("Zacisze", "Wrocław", 120, true, false, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis nec odio. Vivamus justo erat, accumsan ut pharetra eu, feugiat mollis ex. Aenean lectus libero", photos,
                "609781153", "psliwinska@onet.eu"));

        serviceProvider.addService(new WeddingHall("Zajazd u Bożenki", "Warszawa", 300, false, false, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis nec odio. Vivamus justo erat, accumsan ut pharetra eu, feugiat mollis ex. Aenean lectus libero", photos
                , "609781153", "psliwinska@onet.eu"));*/
    }
}
