package com.example.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import sun.reflect.ReflectionFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestTask2 {

    @Test
    public void testCarProperty() {



        assertAll(
                () ->
                        assertTrue(GenericTestFactory.testField(Car.class,"color")),
                () ->
                        assertTrue(GenericTestFactory.testField(Car.class,"model")),
                () ->
                        assertTrue(GenericTestFactory.testField(Car.class,"engineCapacity"))

                );
    }

}
