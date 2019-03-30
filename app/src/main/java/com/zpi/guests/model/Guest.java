package com.zpi.guests.model;

import java.io.Serializable;

public class Guest implements Serializable {

    private String name;
    private String phone;
    private boolean confirmed;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Guest(String name, String phone) {
        this.name = name;
        this.confirmed = false;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
