package com.example.project;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestClassTemplate {

    @Test
    public void testHelloWorld() {
            Main main = GenericTestFactory.getInstance(Main.class);
            GenericTestFactory.testSystemOutputFromMethod(main,"printHelloWorld","Hello World",null,null);
    }

    @Test
    public void testMultiplyNumbers() {
        int first = 5;
        int second = 10;
        Main main = GenericTestFactory.getInstance(Main.class);
        GenericTestFactory.testMethod(main,"multiplyNumbers",50,new Class[]{int.class,int.class},new Object[]{first,second}, true);
    }

}



