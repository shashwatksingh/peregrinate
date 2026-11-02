package org.shashwatksingh.dsa;

/*
Compute the 
*/

public class LCM {
    public int bruteForce(int x, int y) {
        int ans = x > y ? x : y;

        while (true) {
            if(ans%x ==0 && ans%y==0) break;
            ans++;
        }

        return ans;
    }

    /*
    u*v=LCM(u,v)*GCD(u,v)
    LCM(u,v) = (u*v)/GCD(u,v)
    */
    public int usingGCD(int x, int y) {
        if (x == 0 || y == 0) {
            return 0; 
        }

        GCD gcd = new GCD();

        return x/gcd.euclideanMethod(x, y) * y;
    }

    public static void main(String[] args) {
        LCM lcm = new LCM();
        System.out.println(lcm.bruteForce(15, 40));
    }
}
