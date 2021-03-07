package com.example.project;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestTask1 {

    @Test
    public void testTelephoneProperty() {
        assertAll(
                () ->
                        assertNotNull(Telephone.class.getDeclaredField("model")),
                () ->
                        assertNotNull(Telephone.class.getDeclaredField("resolution")),
                () ->
                        assertNotNull(Telephone.class.getDeclaredField("number"))
        );
    }

    @Test
    public void testSendSMS() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        Telephone t = GenericTestFactory.getInstance(Telephone.class);

        t.sendSMS("kuku", "111111");

        assertEquals(("wysylam wiadomosc kuku na numer 111111").trim().toLowerCase(), (bos.toString().trim()).toLowerCase());
    }
}



