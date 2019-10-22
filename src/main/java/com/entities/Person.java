package com.entities;

import java.util.Objects;

public class Person extends Nameble {
    protected String name;
    protected String lastName;
    protected Integer id;

    public Person(Integer id) {
        this.id = id;
    }

    public Person(String name, String lastName, Integer id) {
        this.name = name;
        this.lastName = lastName;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "first_name='" + name + '\'' +
                ", last_name='" + lastName + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return getId() == person.getId() &&
                getName().equals(person.getName()) &&
                getLastName().equals(person.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLastName(), getId());
    }

    @Override
    public String toCSVWithFormatString() {
        return null;
    }

    @Override
    public String toCSVFileString() {
        return null;
    }
}
