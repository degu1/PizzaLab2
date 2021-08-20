package com.example.pizzalab2.utils;

import com.example.pizzalab2.entities.Pizza;

import java.lang.reflect.Field;

import java.util.Arrays;


public class Utils {

    public Utils() {
    }

    public static String[] ignoreProperties(Pizza pizza) {
        Field[] fields = Pizza.class.getFields();
        String[] ans = new String[fields.length];
        int index = 0;
        for (Field field : fields) {
            try {
                if (field.get(pizza) == null) {
                    ans[index++] = field.getName();
                }
            } catch (Exception e) {
                System.out.println("Error from ignoreProperties");
            }
        }
        return trimArray(ans);
    }

    private static String[] trimArray(String[] arr) {
        String[] ans = Arrays.stream(arr)
                .filter(s -> s != null && !s.isEmpty())
                .toArray(String[]::new);
        return ans;
    }
}
