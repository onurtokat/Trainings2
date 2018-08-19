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


1 2 5 3 7 8 6 4
*/

package com.onurtokat;

import java.util.*;

public class NewYearChaos {

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {

//        int[] regularArr = Arrays.copyOf(q, q.length);
//        Arrays.sort(regularArr);
//        for (int i = 0; i < regularArr.length; i++) {
//            for (int j = 0; j < q.length; j++) {
//                if (regularArr[i] == q[j] && (i != j)) {
//                    if (i > j) {
//                        if ((i - j) > 2) {
//                            System.out.println("Too chaotic");
//                            return;
//                        }
//                    }
//                }
//            }
//        }
        int temp;
        int count = 0;
        for (int i = 0; i < q.length - 1; i++) {
            for (int j = 0; j < q.length - 1; j++) {
                if (q[j]>=q[j+2]) {
                    System.out.println("Too chaotic");
                    return;
                }
                if (q[j] > q[j + 1]) {//inversion of array
                    temp = q[j];
                    q[j] = q[j + 1];
                    q[j + 1] = temp;
                    count++;
                }
            }
        }
        System.out.println(count);
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
