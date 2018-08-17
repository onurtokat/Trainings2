/*
Sample Input

2
5
2 1 5 3 4
5
2 5 1 3 4

Sample Output

3
Too chaotic



1
5
2 1 5 3 4
*/

package com.onurtokat;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class NewYearChaos {

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {

        int[] regularArr = Arrays.copyOf(q, q.length);
        Arrays.sort(regularArr);
        int diff = 0;
        for (int i = 0; i < regularArr.length; i++) {
            for (int j = 0; j < q.length; j++) {
                if (regularArr[i] == q[j] && (i != j)) {
                    if (i > j) {
                        if ((i - j) > 2) {
                            System.out.println("Too chaotic");
                            return;
                        } else {
                            diff = diff + (i - j);
                        }
                    } else if (i < j) {
                        System.out.println("q[j]: "+q[j]+"q[j-1]: "+q[j-1]+" "+(q[j]-q[j-1]));

                    }
                }
            }
        }
        //System.out.println(diff);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String[] qItems = scanner.nextLine().split("\\s+");

        int[] q = new int[qItems.length];

        for (int i = 0; i < qItems.length; i++) {
            int qItem = Integer.parseInt(qItems[i]);
            q[i] = qItem;
        }

        minimumBribes(q);
        scanner.close();
    }
}
