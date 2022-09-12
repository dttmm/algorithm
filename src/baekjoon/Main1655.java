package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main1655 {

    static Node head;
    static Node tail;
    static Node mid;
    static int size;

    private static class Node {
        int data;
        Node next;
        Node pre;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void insert(int data) {
        Node newNode = new Node(data);

        // 데이터 집어 넣기
        if (head == null) {
            head = newNode;
            tail = newNode;
            mid = newNode;
        } else {
            // head보다 작을 때
            if (newNode.data <= head.data) {
                head.pre = newNode;
                newNode.next = head;
                head = newNode;
            }
            // tail보다 클 때
            else if (newNode.data >= tail.data) {
                tail.next = newNode;
                newNode.pre = tail;
                tail = newNode;
            } else {
                // mid보다 작을 때
                if (newNode.data < mid.data) {
                    Node node = head;
                    while (true) {
                        if (newNode.data < node.next.data) {
                            newNode.next = node.next;
                            newNode.pre = node;
                            node.next.pre = newNode;
                            node.next = newNode;
                            break;
                        }
                        node = node.next;
                    }
                }
                // mid 보다 클 때
                else {
                    Node node = mid;
                    while (true) {
                        if (newNode.data < node.next.data) {
                            newNode.next = node.next;
                            newNode.pre = node;
                            node.next.pre = newNode;
                            node.next = newNode;
                            break;
                        }
                        node = node.next;
                    }
                }
            }
        }

        // mid값 조정하기

        // 짝수일 때
        if (size % 2 == 0) {
            // mid보다 작을 때
            if (newNode.data < mid.data) {
                mid = mid.pre;
            }
            // mid보다 클 때는 변함없음
            else {
            }
        }
        // 홀수일 때
        else {
            // mid보다 작을 때는 변함없음
            if (newNode.data <= mid.data) {
            }
            // mid보다 클 때
            else {
                mid = mid.next;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1655.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        size = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            size++;
            insert(num);
            System.out.println(mid.data);
        }
    }
}
