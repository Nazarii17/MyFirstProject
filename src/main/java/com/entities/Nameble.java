package com.entities;

public abstract class Nameble extends EntityID implements CSVSerializable{

    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
