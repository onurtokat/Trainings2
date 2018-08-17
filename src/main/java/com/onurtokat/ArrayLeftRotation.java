/*
Sample Input

5 4
1 2 3 4 5

Sample Output

5 1 2 3 4

[1,2,3,4,5] --> [2,3,4,5,1] --> [3,4,5,1,2] --> [4,5,1,2,3] --> [5,1,2,3,4]

#########################################################
Sample Input2
5 2
1 2 3 4 5

3 4 5 1 2
*/

package com.onurtokat;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayLeftRotation {

    private static final Scanner scanner = new Scanner(System.in);

    private static int arrayLength;
    private static int leftRotation;
    private static int[] array;


    static int[] rotLeft(int[] a, int d) {
        int[] tmpArr = new int[arrayLength];

        for (int i = d; i < arrayLength; i++) {
            tmpArr[i - d] = a[i];
        }
        for (int j = 0; j < d; j++) {
            tmpArr[j + (arrayLength - d)] = a[j];
        }
        return tmpArr;
    }

    public static void main(String[] args) throws IOException {

        String firsRow = scanner.nextLine();
        arrayLength = Integer.valueOf(firsRow.split("\\s+")[0]);
        leftRotation = Integer.valueOf(firsRow.split("\\s+")[1]);

        String secondRow = scanner.nextLine();
        array = new int[arrayLength];
        String[] split = secondRow.split("\\s+");
        for (int i = 0; i < arrayLength; i++) {
            array[i] = Integer.valueOf(split[i]);
        }
        System.out.println(Arrays.toString(rotLeft(array, leftRotation)));

    }

}
