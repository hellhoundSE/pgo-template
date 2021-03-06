package com.example.project;

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

    @Test
    public void testSendSMS() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        Telephone t = new Telephone();
        t.sendSMS("kuku","111111");

        assertEquals("wysylam wiadomosc kuku na numer 111111\n".trim(), bos.toString().trim());
    }
}
