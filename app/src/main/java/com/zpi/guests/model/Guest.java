package com.zpi.guests.model;

public class Guest {

    private String name;
    private boolean confirmed;

    public Guest(String name) {
        this.name = name;
        this.confirmed = false;
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
