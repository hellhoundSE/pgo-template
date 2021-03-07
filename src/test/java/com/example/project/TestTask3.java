package com.example.project;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TestTask3 {


    @Test
    public void testCarGetters(){
        String model = "Mazda";
        String color = "Red";
        double engineCapacity = 13.2;
        Car instance = GenericTestFactory.getInstance(Car.class, new Class[]{String.class,String.class, double.class},new Object[]{color,model,engineCapacity});


        assertAll(
                () -> assertTrue(GenericTestFactory.testGetter(instance,"color", "Red")),
                () -> assertTrue(GenericTestFactory.testGetter(instance,"model", "Mazda")),
                () -> assertTrue(GenericTestFactory.testGetter(instance,"engineCapacity", 13.2))
        );
      }

    @Test
    public void testCarSetters(){
        String model = "Mazda";
        String color = "Red";
        double engineCapacity = 13.2;
        double newEngineCapacity = 15.2;
        Car instance = GenericTestFactory.getInstance(Car.class, new Class[]{String.class,String.class,double.class},new Object[]{color,model,engineCapacity});

        assertAll(
                () -> assertTrue(GenericTestFactory.testSetter(instance,"engineCapacity", newEngineCapacity)),
                () -> assertTrue(GenericTestFactory.testSetter(instance,"color", "Red")),
                () -> assertTrue(GenericTestFactory.testSetter(instance,"model", "Mazda"))
        );

    }
}
