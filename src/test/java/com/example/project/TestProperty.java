package com.example.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.*;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestProperty {

    @Test
    public void propertyExists(){
        String name = "Sony";
        int res = 12;
        String number = "123123";
        Telephone t = new Telephone(name,res,number);
        assertAll(
                () ->
                        assertNotNull(Telephone.class.getDeclaredField("model")),
                ()->
                        assertNotNull(Telephone.class.getDeclaredField("resolution")),
                ()->
                        assertNotNull(Telephone.class.getDeclaredField("number"))
                );
    }

    @Test
    public void testProperty(){
        String name = "Sony";
        int res = 12;
        String number = "123123";
        Telephone t = new Telephone(name,res,number);
        assertAll(
                () ->{
                        Field f = Telephone.class.getDeclaredField("model");
                        f.setAccessible(true);
                        assertEquals(name,(String)f.get(t));
                },
                ()->{
                        Field f = Telephone.class.getDeclaredField("resolution");
                        f.setAccessible(true);
                        assertEquals(res,(double)f.get(t));
                        },
                ()->{
                        Field f = Telephone.class.getDeclaredField("number");
                        f.setAccessible(true);
                        assertEquals(number,(String)f.get(t));
                }
        );
    }
}
