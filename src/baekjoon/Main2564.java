package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;

// 26분
public class Main2564 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2564.txt"));

        Scanner sc = new Scanner(System.in);
        int w = sc.nextInt();
        int h = sc.nextInt();

        int N = sc.nextInt();

        int[][] arr = new int[2][N];

        // 각 상점의 방향과 위치 받음
        for (int i = 0; i < N; i++) {
            arr[0][i] = sc.nextInt();
            arr[1][i] = sc.nextInt();
        }

        // 동근이 방향과 위치 받음
        int[][] dong = new int[2][1];
        dong[0][0] = sc.nextInt();
        dong[1][0] = sc.nextInt();

        int sum = 0;
        int min = Integer.MAX_VALUE;
        switch (dong[0][0]) {

            // 동근이가 북쪽일때
            case 1:
                for (int i = 0; i < N; i++) {
                    // 상점이 북쪽일때
                    if (arr[0][i] == 1) {
                        min = Math.abs(dong[1][0] - arr[1][i]);
                    }
                    // 상점이 남쪽일때
                    else if (arr[0][i] == 2) {
                        min = Math.min(dong[1][0] + h + arr[1][i], w - dong[1][0] + h + w - arr[1][i]);
                    }
                    // 상점이 서쪽일때
                    else if (arr[0][i] == 3) {
                        min = dong[1][0] + arr[1][i];
                    }
                    // 상점이 동쪽일때
                    else {
                        min = w - dong[1][0] + arr[1][i];
                    }
                    sum += min;
                }
                break;

            // 동근이가 남쪽일때
            case 2:
                for (int i = 0; i < N; i++) {
                    if (arr[0][i] == 1) {
                        min = Math.min(dong[1][0] + h + arr[1][i], w - dong[1][0] + h + w - arr[1][i]);
                    } else if (arr[0][i] == 2) {
                        min = Math.abs(dong[1][0] - arr[1][i]);
                    } else if (arr[0][i] == 3) {
                        min = dong[1][0] + h - arr[1][i];
                    } else {
                        min = w - dong[1][0] + h - arr[1][i];
                    }
                    sum += min;
                }
                break;

            // 동근이가 서쪽일때
            case 3:
                for (int i = 0; i < N; i++) {
                    if (arr[0][i] == 1) {
                        min = dong[1][0] + arr[1][i];
                    } else if (arr[0][i] == 2) {
                        min = h - dong[1][0] + arr[1][i];
                    } else if (arr[0][i] == 3) {
                        min = Math.abs(dong[1][0] - arr[1][i]);
                    } else {
                        min = Math.min(dong[1][0] + w + arr[1][i], h - dong[1][0] + w + h - arr[1][i]);
                    }
                    sum += min;
                }
                break;

            // 동근이가 동쪽일때
            case 4:
                for (int i = 0; i < N; i++) {
                    if (arr[0][i] == 1) {
                        min = dong[1][0] + w - arr[1][i];
                    } else if (arr[0][i] == 2) {
                        min = h - dong[1][0] + w - arr[1][i];
                    } else if (arr[0][i] == 3) {
                        min = Math.min(dong[1][0] + w + arr[1][i], h - dong[1][0] + w + h - arr[1][i]);
                    } else {
                        min = Math.abs(dong[1][0] - arr[1][i]);
                    }
                    sum += min;
                }
                break;
        }
        System.out.println(sum);
    }
}