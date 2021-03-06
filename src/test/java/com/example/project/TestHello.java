package com.example.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.*;

public class TestHello {

    @Test
    public void testTelephone()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        Telephone t = new Telephone("arra",12,"22222");
        t.sendSMS("kuku","666666");

        assertEquals("Wysyłam wiadomość kuku na numer 666666\n", bos.toString());

        t.sendSMS("haha","112233");

        assertEquals("Wysyłam wiadomość haha na numer 112233\n", bos.toString());

        // undo the binding in System
        System.setOut(originalOut);
    }
}
