package algorithm.day06queue;

import java.util.Scanner;

public class Practice2 {
    static int[] qu;
    static int front;
    static int rear;
    static int total;
    static int[] visited;
    static Scanner scanner;
    static int count;
    static int size;

    public static void myzzu() {

        System.out.println("현재 큐에 있는 사람");
        for (int i = 0; i < size; i++) {
            System.out.print(qu[(front + 1 + i) % 20] + " ");
        }
        System.out.println();
        System.out.println("현재 일인당 나눠주는 사탕의 수");
        for (int i = 0; i < 20; i++) {
            if (visited[i] != 0) {
                System.out.printf("%d: %d개", i + 1, visited[i]);
            }
        }
        System.out.println();
        System.out.println("현재 일인당 나눠준 사탕의 수");
        System.out.println(20 - total);

        front = (front + 1) % 20;
        int cur = qu[front];

        visited[cur - 1]++;
        size--;
        if (total - visited[cur - 1] <= 0) {
            System.out.printf("20번째주인공 %d\n", cur);
        } else {
            total -= visited[cur - 1];

            rear = (rear + 1) % 20;
            qu[rear] = cur;
            size++;
            rear = (rear + 1) % 20;
            qu[rear] = ++count;
            size++;
            myzzu();
        }
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        qu = new int[20];
        front = -1;
        rear = -1;
        total = 20;
        visited = new int[20];
        count = 1;
        size = 0;

        qu[++rear] = 1;
        size++;
        myzzu();


    }
}
