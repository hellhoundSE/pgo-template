package com.example.project;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.*;

public class TestHello {

    @Test
    public void testProperty(){
        String name = "Sony";
        int res = 12;
        String number = "123123";
        Telephone t = new Telephone(name,res,number);
        assertAll(
                () ->
                    assertEquals(name,t.model),
                ()->
                    assertEquals(res,t.resolution),
                ()->
                    assertEquals(number,t.number)
                );
    }
}
