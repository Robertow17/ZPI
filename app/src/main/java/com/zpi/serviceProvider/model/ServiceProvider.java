package com.zpi.serviceProvider.model;

import com.zpi.searcher.utils.Service;

import java.util.ArrayList;

public class ServiceProvider
{
    private String email;
    private String password;
    private ArrayList<Service> services;

    public ServiceProvider(String email, String password) {
        this.email = email;
        this.password = password;
        this.services = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    public void addService(Service service){
        services.add(service);
    }
}
