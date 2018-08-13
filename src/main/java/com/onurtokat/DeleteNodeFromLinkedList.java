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

7
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

        //non-recursive way
        /*
        if(head == null)
            return null;
        Node s = head;
        while(s.next != null){
            if(s.data == s.next.data)
                s.next = s.next.next;
            else
                s = s.next;
        }
        return head;
        */
        NodeLL root = head;

        //check the next node is null
        if (root.next != null) {

            if (root.data == root.next.data) {

                root.next = root.next.next;
                //after the regulation, restart the control for same node start
                removeDuplicates(root);
            }else{
                removeDuplicates(root.next);
            }
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
