package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class Main5430 {

    static Node head;
    static Node tail;
    static Node current;
    static Boolean flag;

    private static class Node {
        int n;
        Node pre;
        Node next;

        public Node(int n) {
            this.n = n;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/5430.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String fun = br.readLine();
            int N = Integer.parseInt(br.readLine());

            head = null;
            tail = null;
            current = null;
            flag = false;

            String s = br.readLine();
            s = s.substring(1, s.length() - 1);
            StringTokenizer st = new StringTokenizer(s, ",");

            for (int i = 0; i < N; i++) {
                int n = Integer.parseInt(st.nextToken());
                Node newNode = new Node(n);

                if (head == null) {
                    head = newNode;
                } else {
                    tail.next = newNode;
                    newNode.pre = tail;
                }
                tail = newNode;
            }

            current = head;

            for (int i = 0; i < fun.length(); i++) {
                char c = fun.charAt(i);

                if (c == 'R') {
                    if (current == head) current = tail;
                    else if (current == tail) current = head;
                } else {
                    if (head == null) {
                        flag = true;
                        break;
                    }

                    if (current == head) {
                        current = head.next;
                        head = head.next;
                        if (head != null) head.pre = null;
                    } else if (current == tail) {
                        current = tail.pre;
                        tail = tail.pre;
                        if (tail != null) tail.next = null;
                    }
                }
            }

            if (flag) {
                bw.write("error\n");
                continue;
            }

            if (current == head) {
                if (head == null) bw.write("[]");
                else {
                    bw.write("[");
                    while (head.next != null) {
                        bw.write(head.n + ",");
                        head = head.next;
                    }
                    bw.write(head.n + "]");
                }
            } else if (current == tail) {
                if (tail == null) bw.write("[]");
                else {
                    bw.write("[");
                    while (tail.pre != null) {
                        bw.write(tail.n + ",");
                        tail = tail.pre;
                    }
                    bw.write(tail.n + "]");
                }
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}
