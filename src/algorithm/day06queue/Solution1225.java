package algorithm.day06queue;

import java.io.FileInputStream;
import java.util.Scanner;

// 38분
public class Solution1225 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day06_1225.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {
            int case_num = sc.nextInt();
            int N = 8;
            int[] arr = new int[N];
            int front = -1;
            int rear = -1;
            for (int i = 0; i < N; i++) {
                arr[++rear] = sc.nextInt();
            }

            boolean flag = false;
            while (true) {
                for (int i = 1; i <= +5; i++) {
                    front = (front + 1) % N;
                    rear = (rear + 1) % N;
                    arr[rear] = arr[front] - i;
                    if (arr[rear] <= 0) {
                        arr[rear] = 0;
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }

            System.out.print("#" + case_num + " ");
            for (int i = 0; i < N; i++) {
                System.out.print(arr[(front + 1 + i) % N] + " ");
            }
            System.out.println();
        }
    }
}