package com.example.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestSendSMS {

    @Test
    public void testSendSMS() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        Telephone t = new Telephone("arra",12,"22222");
        t.sendSMS("kuku","111111");

        assertEquals(("wysylam wiadomosc kuku na numer 111111\n").trim().toLowerCase(), (bos.toString().trim()).toLowerCase());
    }
}
