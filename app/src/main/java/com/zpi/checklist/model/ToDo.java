package com.zpi.checklist.model;

import java.io.Serializable;

public class ToDo implements Serializable {

    private String name;
    private boolean done;

    public ToDo(String name) {
        this.name = name;
        this.done = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}