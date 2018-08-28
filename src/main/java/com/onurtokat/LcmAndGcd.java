package com.onurtokat;

public class LcmAndGcd {

    //Greatest common divisor with two long parameter
    private static long gcd(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }

    //Greatest common divisor with a long array
    private static long gcd(long[] input) {
        long result = input[0];
        for (int i = 1; i < input.length; i++) result = gcd(result, input[i]);
        return result;
    }

    //least common multiplier with two long parameters
    private static long lcm(long a, long b) {
        return a * (b / gcd(a, b));
    }

    //least common multiplier with a long array
    private static long lcm(long[] input) {
        long result = input[0];
        for (int i = 1; i < input.length; i++) result = lcm(result, input[i]);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(gcd(30, 12));
        System.out.println(lcm(20, 15));
    }
}
