package org.shashwatksingh.dsa;

import java.util.HashMap;
import java.util.Map;

public class PrimeFactorOfNumber {

    public static void main(String[] args) {
        PrimeFactorOfNumber pf = new PrimeFactorOfNumber();
        System.out.println(pf.primeFactor(15));
    }

    public Map<Integer, Integer> primeFactor(int x) {
        // factor:frequency
        Map<Integer, Integer> map = new HashMap<>();
        if(x<=0) return map;

        while (x%2==0) {
            map.merge(2, 1, Integer::sum);
            x/=2;
        }   
        

        for (int i = 3; i<=x/i; i+=2) {
            while(x%i==0) {
                map.merge(i, 1, Integer::sum);
                x/=i;
            }
        }

        if(x>1) map.merge(x, 1, Integer::sum);

        return map;
    }

}
