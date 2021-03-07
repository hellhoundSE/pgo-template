package com.example.project;

import sun.reflect.ReflectionFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    public static <T,F> boolean testGetter(F instance, String fieldName, T expectedValue ){

        Field field = null;

        try {
            field = instance.getClass().getDeclaredField(fieldName);
            if(field.isAccessible())
                return expectedValue.equals(field.get(instance));
            else {
                String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                if (field.getGenericType().toString().equals(Boolean.class.toString()) || "boolean".equals(field.getGenericType().toString())) {
                    getterName = "is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                }
                    Method method = instance.getClass().getDeclaredMethod(getterName);
                    Object realValue = method.invoke(instance) ;
                    return expectedValue.equals(realValue);
                }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static <T,F> boolean testSetter(F instance, String fieldName, T expectedValue){

        Field field = null;

        try {
            field = instance.getClass().getDeclaredField(fieldName);
            if(field.isAccessible())
                return true;
            else {
                String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                Class expectedClass = ConvertToPrimitiveType(expectedValue.getClass());

                Class c = expectedValue.getClass();
                Method method = instance.getClass().getDeclaredMethod(setterName, expectedClass);
                method.invoke(instance,expectedValue);

                field.setAccessible(true);
                boolean result = field.get(instance).equals(expectedValue);
                field.setAccessible(false);

                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean testField(Class clazz,String field){
        try {
            Field f = clazz.getDeclaredField(field);
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    private static Class ConvertToPrimitiveType(Class clazz){
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
