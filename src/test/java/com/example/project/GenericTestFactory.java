package com.example.project;

import sun.reflect.ReflectionFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public final class GenericTestFactory {

    public static <T> T getInstance(Class clazz, Class[] requiredParameters, Object[] parameters){

        T instance = null;
        try {
            Constructor<T> ctor = clazz.getDeclaredConstructor(requiredParameters);
            instance = ctor.newInstance(parameters);

        }catch (Exception e) {
            try {
                ReflectionFactory rf = ReflectionFactory.getReflectionFactory();
                Constructor objDef = Object.class.getDeclaredConstructor();
                Constructor intConstr = rf.newConstructorForSerialization(clazz, objDef);
                instance = (T) intConstr.newInstance();
            } catch (Exception ee) {
                return null;
            }
        }
        return instance;
    }

    public static <T> T getInstance(Class clazz){

        T instance = null;

        try {
            ReflectionFactory rf = ReflectionFactory.getReflectionFactory();
            Constructor objDef = Object.class.getDeclaredConstructor();
            Constructor intConstr = rf.newConstructorForSerialization(clazz, objDef);
            instance = (T) intConstr.newInstance();
        } catch (Exception ee) {
            return null;
        }

        return instance;
    }

    public static void testFieldExists(Class clazz, String field){

        try {
            assertNotNull(clazz.getDeclaredField(field));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail();
        }
    }

    public static <T,F> void testGetter(F instance, String fieldName, T expectedValue){

        Field field = null;

        try {
            field = instance.getClass().getDeclaredField(fieldName);
            if(field.isAccessible())
                assertEquals(expectedValue,field.get(instance));
            else {
                String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                if (field.getGenericType().toString().equals(Boolean.class.toString()) || "boolean".equals(field.getGenericType().toString())) {
                    getterName = "is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                }
                    Method method = instance.getClass().getDeclaredMethod(getterName);
                    Object realValue = method.invoke(instance) ;
                    assertEquals(expectedValue,field.get(instance));
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
    public static <T,F> void testSetter(F instance, String fieldName, T expectedValue){

        Field field = null;

        try {
            field = instance.getClass().getDeclaredField(fieldName);
            if(field.isAccessible())
                assertTrue(field.isAccessible());
            else {
                String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                Class expectedClass = expectedValue.getClass();

                Class c = expectedValue.getClass();
                Method method = instance.getClass().getDeclaredMethod(setterName, expectedClass);
                method.invoke(instance,expectedValue);

                field.setAccessible(true);
                boolean result = field.get(instance).equals(expectedValue);
                field.setAccessible(false);

                assertTrue(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
    public static <T,F> void testSetter(F instance, String fieldName, T expectedValue, boolean isPrimitiveType){

        Field field = null;

        try {
            field = instance.getClass().getDeclaredField(fieldName);
            if(field.isAccessible())
                assertTrue(field.isAccessible());

            else {
                String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                Class expectedClass = expectedValue.getClass();

                if( isPrimitiveType)
                    expectedClass = convertToPrimitiveType(expectedValue.getClass());

                Class c = expectedValue.getClass();
                Method method = instance.getClass().getDeclaredMethod(setterName, expectedClass);
                method.invoke(instance,expectedValue);

                field.setAccessible(true);
                boolean result = field.get(instance).equals(expectedValue);
                field.setAccessible(false);

                assertTrue(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    public static <T> void testSystemOutputFromMethod(T instance, String methodName, String expectedOutput, Class[] requiredParameters, Object[] parameters){

        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        if(requiredParameters == null)
            requiredParameters = new Class[0];

        if(parameters == null)
            parameters = new Object[0];

        try {
            if(requiredParameters.length != parameters.length)
                throw new Exception("Parameters length are different");

            Method method = instance.getClass().getDeclaredMethod(methodName,requiredParameters);

            method.invoke(instance,parameters);

            String realOutput = bos.toString().replaceAll("\\s+", "").toLowerCase();
            expectedOutput = expectedOutput.replaceAll("\\s+", "").toLowerCase();
            assertEquals(expectedOutput,realOutput);

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }finally {
            System.setOut(originalOut);
        }
    }
    public static <T> void testSystemOutputFromMethod(T instance, String methodName, String expectedOutput, Class[] requiredParameters, Object[] parameters, boolean isPrimitiveTypes){

        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        if(requiredParameters == null)
            requiredParameters = new Class[0];

        if(parameters == null)
            parameters = new Object[0];

        try {
            if(requiredParameters.length != parameters.length)
                throw new Exception("Parameters length are different");

            if(isPrimitiveTypes)
                for (int i = 0; i < requiredParameters.length;i++)
                    requiredParameters[i] = convertToPrimitiveType(requiredParameters[i]);

            Method method = instance.getClass().getDeclaredMethod(methodName,requiredParameters);

            method.invoke(instance,parameters);

            String realOutput = bos.toString().replaceAll("\\s+", "").toLowerCase();
            expectedOutput = expectedOutput.replaceAll("\\s+", "").toLowerCase();
            assertEquals(expectedOutput,realOutput);

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }finally {
            System.setOut(originalOut);
        }
    }
    public static <T> void testSystemOutputFromMethod(T instance, String methodName, String expectedOutput, Class[] requiredParameters, Object[] parameters, boolean[] isPrimitiveTypes){

        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        if(requiredParameters == null)
            requiredParameters = new Class[0];

        if(parameters == null)
            parameters = new Object[0];

        try {
            if(requiredParameters.length != parameters.length && requiredParameters.length != isPrimitiveTypes.length)
                throw new Exception("Parameters length are different");

            for (int i = 0; i < requiredParameters.length;i++)
                if(isPrimitiveTypes[i])
                    requiredParameters[i] = convertToPrimitiveType(requiredParameters[i]);

            Method method = instance.getClass().getDeclaredMethod(methodName,requiredParameters);

            method.invoke(instance,parameters);

            String realOutput = bos.toString().replaceAll("\\s+", "").toLowerCase();
            expectedOutput = expectedOutput.replaceAll("\\s+", "").toLowerCase();
            assertEquals(expectedOutput,realOutput);

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }finally {
            System.setOut(originalOut);
        }
    }

    public static <T> void testMethod(T instance, String methodName, Object expectedOutput, Class[] requiredParameters, Object[] parameters){

        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        if(requiredParameters == null)
            requiredParameters = new Class[0];

        if(parameters == null)
            parameters = new Object[0];

        try {
            if(requiredParameters.length != parameters.length)
                throw new Exception("Parameters length are different");

            Method method = instance.getClass().getDeclaredMethod(methodName,requiredParameters);

            Object realOutput = method.invoke(instance,parameters);
            assertEquals(expectedOutput,realOutput);

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }finally {
            System.setOut(originalOut);
        }
    }
    public static <T> void testMethod(T instance, String methodName, Object expectedOutput, Class[] requiredParameters, Object[] parameters, boolean isPrimitiveTypes){

        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        if(requiredParameters == null)
            requiredParameters = new Class[0];

        if(parameters == null)
            parameters = new Object[0];

        try {
            if(requiredParameters.length != parameters.length)
                throw new Exception("Parameters length are different");

            if(isPrimitiveTypes)
                for (int i = 0; i < requiredParameters.length;i++)
                    requiredParameters[i] = convertToPrimitiveType(requiredParameters[i]);

            Method method = instance.getClass().getDeclaredMethod(methodName,requiredParameters);

            Object realOutput = method.invoke(instance,parameters);
            assertEquals(expectedOutput,realOutput);

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }finally {
            System.setOut(originalOut);
        }
    }
    public static <T> void testMethod(T instance, String methodName, Object expectedOutput, Class[] requiredParameters, Object[] parameters, boolean[] isPrimitiveTypes){

        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        if(requiredParameters == null)
            requiredParameters = new Class[0];

        if(parameters == null)
            parameters = new Object[0];

        try {
            if(requiredParameters.length != parameters.length && requiredParameters.length != isPrimitiveTypes.length)
                throw new Exception("Parameters length are different");

            for (int i = 0; i < requiredParameters.length;i++)
                if(isPrimitiveTypes[i])
                    requiredParameters[i] = convertToPrimitiveType(requiredParameters[i]);

            Method method = instance.getClass().getDeclaredMethod(methodName,requiredParameters);

            Object realOutput = method.invoke(instance,parameters);
            assertEquals(expectedOutput,realOutput);

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }finally {
            System.setOut(originalOut);
        }
    }

    private static Class convertToPrimitiveType(Class clazz){
        if(clazz.equals(Boolean.class))     return boolean.class;
        if(clazz.equals(Integer.class))     return int.class;
        if(clazz.equals(Double.class))      return double.class;
        if(clazz.equals(Float.class))       return float.class;
        if(clazz.equals(Short.class))       return short.class;
        if(clazz.equals(Long.class))        return long.class;
        if(clazz.equals(Byte.class))        return byte.class;
        if(clazz.equals(Character.class))   return char.class;
        if(clazz.equals(Character.class))   return char.class;
        return clazz;
    }



}
