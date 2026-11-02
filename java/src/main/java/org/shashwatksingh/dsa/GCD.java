package org.shashwatksingh.dsa;

import java.util.Map;

public class GCD {
    public int bruteForce(int x, int y){
        int ans = 1;
        int max = Math.min(x,y);

        for (int i = ans+1; i <= max; i++) {
            if(x%i==0 && y%i==0) ans = i;
        }

        return ans;
    }

    public int primeFactorisation(int x, int y) {
        int ans = 1;
        PrimeFactorOfNumber pf = new PrimeFactorOfNumber();

        Map<Integer, Integer> xPrimeFactors = pf.primeFactor(x);
        Map<Integer, Integer> yPrimeFactors = pf.primeFactor(y);

        for (Map.Entry<Integer, Integer> entry : xPrimeFactors.entrySet()) {
            int factor = entry.getKey();
            int common = Math.min(entry.getValue(), yPrimeFactors.getOrDefault(factor, 0));
            for (int k = 0; k < common; k++) ans *= factor;
        }

        return ans;
    }

    public int euclideanMethod(int x, int y) {
        while (y!=0) {
            int remainder = x%y;
            x=y;
            y=remainder;
        }
        return x;
    }

    public int recursiveEuclidean(int x, int y) {
        if(y == 0) return x;
        return recursiveEuclidean(y, x%y);
    }

    public static void main(String[] args) {
        GCD gcd = new GCD();
        // System.out.println(gcd.bruteForce(15,40));
        // System.out.println(gcd.bruteForce(15,30));

        // System.out.println(gcd.euclideanMethod(15,40));
        // System.out.println(gcd.euclideanMethod(15,30));

        System.out.println(gcd.recursiveEuclidean(15,40));
        System.out.println(gcd.recursiveEuclidean(15,30));

        System.out.println(gcd.primeFactorisation(15,40));
        System.out.println(gcd.primeFactorisation(15,30));
    }

}
