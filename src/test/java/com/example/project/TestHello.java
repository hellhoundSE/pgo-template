package com.example.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.*;

public class TestHello {

    @Test
    public void testTelephone() throws IOException {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        Telephone t = new Telephone("arra",12,"22222");
        t.sendSMS("kuku","666666");

        assertEquals("wysylam wiadomosc kuku na numer 666666\n".trim(), bos.toString().trim());
        //assertEquals("wysylam wiadomosc kuku na numer 666666\n", "wysylam wiadomosc kuku na numer 666666\n");

        // undo the binding in System
    }

    @Test
    public void testTelephone2() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        Telephone t = new Telephone("arra",12,"22222");
        t.sendSMS("kuku","111111");

        assertEquals("wysylam wiadomosc kuku na numer 111111\n".trim(), bos.toString().trim());
        //assertEquals("wysylam wiadomosc kuku na numer 666666\n", "wysylam wiadomosc kuku na numer 666666\n");

        // undo the binding in System
    }
}
