package com.binaryAA.CarShow.entity;

import jakarta.persistence.*;

// Hibernate: Due to the boilerplate and too much code of JDBC,
// Hibernate standard for representing a table -> 3 requirements: Entity keyword, Id keyword, and a non-parameterized constructor
// ORM (Object Relational Mapping)
// JPA (Java Persistence API)
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // AUTO: tell hibernate to choose id for us, IDENTITY: auto increment, SEQUENCE: you have to type what you're going to use
    private Long id;

    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="my_sequence")
    //@SequenceGenerator
    private String make, model, color, registerNumber;

    private int year;

    private double price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Owner owner;


    public Car() {

    }

    public Car(String make, String model, String color, String registerNumber, int year, double price, Owner owner) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.registerNumber = registerNumber;
        this.year = year;
        this.price = price;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
