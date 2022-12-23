/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.sort;

import java.util.stream.Stream;

public class BubbleSort {

    public static String[] sortArray(String[] array) {
        for (int i = array.length - 1; i > 1; i--) {
            for (int j = 0; j < i; j++)
                if (array[j].equals(Stream.of(array[i], array[j]).max(String::compareToIgnoreCase).get())) {
                    String temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
        }
        return array;
    }
}
