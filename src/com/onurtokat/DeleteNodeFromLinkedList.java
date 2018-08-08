package com.onurtokat;

/*
Sample Input

6
1
2
2
3
3
4

Sample Output

1 2 3 4

another training data

6
1
2
2
2
3
3
4
 */

import java.util.Scanner;

class NodeLL {
    int data;
    NodeLL next;

    NodeLL(int d) {
        data = d;
        next = null;
    }

}

public class DeleteNodeFromLinkedList {
    public static NodeLL removeDuplicates(NodeLL head) {
        NodeLL root = head;

        if (root.next != null) {
            //System.out.println("Current root data: " + root.data);

            if (root.data == root.next.data) {
                //System.out.println("Yes, root data equals with next: " + root.data + " == " + root.next.data);
                root.next = root.next.next;
                //System.out.println("Now root is setted with next: " + root.next.data);
            }
            removeDuplicates(root.next);
        }
        return root;
    }

    public static NodeLL insert(NodeLL head, int data) {
        NodeLL p = new NodeLL(data);
        if (head == null)
            head = p;
        else if (head.next == null)
            head.next = p;
        else {
            NodeLL start = head;
            while (start.next != null)
                start = start.next;
            start.next = p;

        }
        return head;
    }

    public static void display(NodeLL head) {
        NodeLL start = head;
        while (start != null) {
            System.out.print(start.data + " ");
            start = start.next;
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        NodeLL head = null;
        int T = sc.nextInt();
        while (T-- > 0) {
            int ele = sc.nextInt();
            head = insert(head, ele);
        }
        head = removeDuplicates(head);
        display(head);
    }
}
