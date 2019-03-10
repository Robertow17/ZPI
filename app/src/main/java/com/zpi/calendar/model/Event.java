package com.zpi.calendar.model;

import java.util.Date;

public class Event {
    Date date;
    String title;
    String description;

    public Event(Date date, String title, String description) {
        this.date = date;
        this.title = title;
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


}
