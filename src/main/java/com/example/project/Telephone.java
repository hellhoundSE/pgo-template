package com.example.project;

public class Telephone {
    String model;
    double resolution;
    String number;

    public Telephone(String model, double resolution, String number) {
        this.model = model;
        this.resolution = resolution;
        this.number = number;
    }

    public void sendSMS(String text, String number){
        System.out.println("wysylam wiadomosc " + text + " na numer "+number);
    }
}