/*
Sample Input
5
2 3 4 1 5

Expected Output
3

Sample Input
1 3 5 2 4 6

Expected Output
3

Sample Input
7 1 3 2 4 5 6

Exprected
5

*/

package com.onurtokat;

import java.util.Scanner;

public class MinimumSwapForInteger {

    private static final Scanner scanner = new Scanner(System.in);

    static int minimumSwaps(int[] arr) {
        int swap=0;
        for(int i=0;i<arr.length;i++){
            if(i+1!=arr[i]){
                int t=i;
                while(arr[t]!=i+1){
                    t++;
                }
                int temp=arr[t];
                arr[t]=arr[i];
                arr[i]=temp;
                swap++;
            }
        }
        return swap;
    }

    public static void main(String[] args) {
        String[] qItems = scanner.nextLine().split("\\s+");

        int[] arr = new int[qItems.length];


        for (int i = 0; i < qItems.length; i++) {
            int qItem = Integer.parseInt(qItems[i]);
            arr[i] = qItem;
        }

        int res = minimumSwaps(arr);
        System.out.println(res);
        scanner.close();
    }
}
