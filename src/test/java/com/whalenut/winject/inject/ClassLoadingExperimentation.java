package com.whalenut.winject.inject;


import java.lang.reflect.Field;
import java.util.List;

public class ClassLoadingExperimentation {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ClassLoader ccl = Thread.currentThread().getContextClassLoader();
        Class clazz = ccl.getClass();
        while (clazz != java.lang.ClassLoader.class) {
            clazz = clazz.getSuperclass();
        }
        Field f = clazz.getDeclaredField("classes");
        f.setAccessible(true);

        List<Class> classes = (List<Class>)f.get(ccl);
        classes.forEach(c -> {
            System.out.println(c.getName());
        });


        Test test = new Test();
        test.getClass().getFields();
        classes = (List<Class>)f.get(ccl);
        classes.forEach(c -> {
            System.out.println(c.getName());
        });

        System.out.println(f.getType().getName());
        System.out.println("end");
    }

    public static class Test implements Real {
        public MyFace mf;
    }

    public interface Real {

    }


}
