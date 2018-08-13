package com.onurtokat;

/*
Sample Input

3
12
5
7

Sample Output

Not prime
Prime
Prime

Additional Input

6
45
31
22
95
43
11


10
1000000000
1000000001
1000000002
1000000003
1000000004
1000000005
1000000006
1000000007
1000000008
1000000009

Exprected output

Not prime
Not prime
Not prime
Not prime
Not prime
Not prime
Not prime
Prime
Not prime
Prime


3
1000000007
100000003
1000003

Expected

Prime
Not prime
Prime
 */

import java.util.Scanner;

public class PrimaryNumberDetection {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        int[] arr = new int[T];
        int point = 0;

        for (int i = 0; i < T; i++) {//O(N)
            arr[i] = sc.nextInt();
        }

        for (int value : arr) {//O(N)
            if (value == 0 || value == 1) {
                point++;
            }

// worst time complexity algorithm
//           for (int i = 1; i <= value; i++) {//O(N)
//                if (i != 1 && i != value) {
//                    if (value % i == 0) {
//                        System.out.println(i);
//                        point++;
//                    }
//                }
//            }
//

            for (int i = 1; i <= Math.sqrt(value); i++) {
                if (i != 1 && i != value && (value % i == 0)) {
                        point++;
                }
            }

            if (point == 0) {
                System.out.println("Prime");
            } else {
                System.out.println("Not prime");
            }
            point = 0;
        }
    }
}
