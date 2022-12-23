/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SortTests {
    @Test
    public void sortComparativeTest() {

        System.out.println("Now quicksort:");
        double[] inArray = new double[]{34565.65d, 5.0d, 2.6d, 7.9d, Double.POSITIVE_INFINITY, 267.8d};
        double[] outArray = QuickSort.quicksort(inArray);
        for (int i = 0; i < outArray.length; i++) System.out.println("->" + outArray[i]);

        //TODO why sorting is happening in different order?
        System.out.println("Now choice sort:");
        inArray = new double[]{34565.65d, 5.0d, 2.6d, 7.9d, Double.POSITIVE_INFINITY, 267.8d};
        outArray = ChoiceSort.sort(inArray);
        for (int i = 0; i < outArray.length; i++) System.out.println("->" + outArray[i]);

    }

    @Test
    public void sortBubble() {

        String[] names = new String[]{"Dmitri","Vladimir","Oleg","Evgen","Nikolay","Alex","Robert","Igor","Konstantine","Leonide","Timofey","Mikhael","Boris","Peter","Xenomorph"};
        Assertions.assertNotEquals("Alex", names[0]);
        Assertions.assertNotEquals("Igor", names[4]);
        Assertions.assertNotEquals("Peter", names[10]);
        System.out.println(Arrays.stream(BubbleSort.sortArray(names)).reduce((s1, s2) -> s1 + " " + s2).get());
        Assertions.assertEquals("Alex", names[0]);
        Assertions.assertEquals("Igor", names[4]);
        Assertions.assertEquals("Peter", names[10]);

    }

    @Test
    public void sortInsertion() {

        String[] names = new String[]{"Dmitri","Vladimir","Oleg","Evgen","Nikolay","Alex","Robert","Igor","Konstantine","Leonide","Timofey","Mikhael","Boris","Peter","Xenomorph"};
        Assertions.assertNotEquals("Alex", names[0]);
        Assertions.assertNotEquals("Igor", names[4]);
        Assertions.assertNotEquals("Peter", names[10]);
        System.out.println(Arrays.stream(InsertionSort.sortArray(names)).reduce((s1, s2) -> s1 + " " + s2));
        Assertions.assertEquals("Alex", names[0]);
        Assertions.assertEquals("Igor", names[4]);
        Assertions.assertEquals("Peter", names[10]);

    }

    @Test
    public void sortChoice() {

        String[] names = new String[]{"Dmitri","Vladimir","Oleg","Evgen","Nikolay","Alex","Robert","Igor","Konstantine","Leonide","Timofey","Mikhael","Boris","Peter","Xenomorph"};
        Assertions.assertNotEquals("Alex", names[0]);
        Assertions.assertNotEquals("Igor", names[4]);
        Assertions.assertNotEquals("Peter", names[10]);
        System.out.println(Arrays.stream(ChoiceSort.sortArray(names, false)).reduce((s1, s2) -> s1 + " " + s2).get());
        Assertions.assertEquals("Alex", names[0]);
        Assertions.assertEquals("Igor", names[4]);
        Assertions.assertEquals("Peter", names[10]);
        System.out.println(Arrays.stream(ChoiceSort.sortArray(names, true)).reduce((s1, s2) -> s1 + " " + s2).get());
        Assertions.assertEquals("Xenomorph", names[0]);
        Assertions.assertEquals("Alex", names[14]);

    }
}