package com.example.project;

public class Telephone {
    private String model;
    private double resolution;
    String number;

    public Telephone(String number, double resolution, String model ) {
        this.model = model;
        this.resolution = resolution;
        this.number = number;
    }

    public void sendSMS(String text, String number){
        System.out.println("wysylam wiadomosc " + text + " na numer "+number);
    }
}