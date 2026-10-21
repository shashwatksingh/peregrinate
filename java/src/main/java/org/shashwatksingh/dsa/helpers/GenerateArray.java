package org.shashwatksingh.dsa.helpers;

import java.util.Random;

public class GenerateArray {
    
    public int[] generate(int l) {
        return generate(l, 0, 1000000000);
    }

    public int[] generate(int l, int min, int max) {
        int[] arr = new int[l];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(0, 100000000);
        }
        System.out.println(arr);
        return arr;
    }
}
