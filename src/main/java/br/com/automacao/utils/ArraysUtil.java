package br.com.automacao.utils;

import java.util.Arrays;

public class ArraysUtil {
    public static final Boolean compare(String[] array1, String[] array2) {
        String[] copy1 = Arrays.copyOf(array1, array1.length);
        String[] copy2 = Arrays.copyOf(array2, array2.length);

        Arrays.sort(copy1);
        Arrays.sort(copy2);

        return Arrays.equals(copy1, copy2);
    }
}
