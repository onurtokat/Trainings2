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

import org.apache.hadoop.fs.shell.Count;

import java.math.BigDecimal;
import java.util.*;

public class NewYearChaos {

    private static BigDecimal total = new BigDecimal(0);

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {

        //this solution (bubble sort) provides O(n^2), means quadratic problem
//        int temp;
//        int count = 0;
//        for (int i = 0; i < q.length - 1; i++) {
//            for (int j = 0; j < q.length - 1; j++) {
//                if ((q[j] - 1 - j) > 2) {
//                    System.out.println("Too chaotic");
//                    return;
//                }
//                if (q[j] > q[j + 1]) {
//                    temp = q[j];
//                    q[j] = q[j + 1];
//                    q[j + 1] = temp;
//                    count++;
//                }
//            }
//        }
//        System.out.println(count);

        for (int a = 0; a < q.length; a++) {
            if (((q[a] - 1) - a) > 2) {
                System.out.println("Too chaotic");
                return;
            }
        }

        //Mergesort
        //Count inversions counts in worst case in O(nlogn) time.
        int[] sortedArray = divideAndConquer(q);
        //Number of inversions
        System.out.println(total);
    }

    public static int[] divideAndConquer(int[] inputArray) {
        int n = inputArray.length;
        if (n == 1) {
            return inputArray;
        }
        int mid = n / 2;
        int[] leftArray = new int[mid];
        int[] rightArray = new int[n - mid];
        System.arraycopy(inputArray, 0, leftArray, 0, leftArray.length);
        System.arraycopy(inputArray, leftArray.length, rightArray, 0, rightArray.length);
        divideAndConquer(leftArray);
        divideAndConquer(rightArray);
        merge(leftArray, rightArray, inputArray);
        return inputArray;
    }

    public static void merge(int[] leftArray,
                             int[] rightArray,
                             int[] sortedArray) {
        int leftArrayLength = leftArray.length;
        int rightArrayLength = rightArray.length;
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < leftArrayLength && j < rightArrayLength) {
            if (leftArray[i] < rightArray[j]) {
                sortedArray[k] = leftArray[i];
                i++;
            } else {
                sortedArray[k] = rightArray[j];
                j++;
                total = total.add(new BigDecimal(leftArray.length - i));
            }
            k++;
        }
        while (i < leftArrayLength) {
            sortedArray[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < rightArrayLength) {
            sortedArray[k] = rightArray[j];
            j++;
            k++;
        }
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
