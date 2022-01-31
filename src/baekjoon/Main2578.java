package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;
// 15분 20초
public class Main2578 {
    public static void find(int[][] arr, int n) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (arr[i][j] == n) {
                    arr[i][j] = 0;
                    return;
                }
            }
        }
    }

    public static boolean check(int[][] arr) {
        int count = 0;

        // 가로줄 검사
        for (int i = 0; i < 5; i++) {
            boolean flag = true;
            for (int j = 0; j < 5; j++) {
                if (arr[i][j] != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                count++;
            }
        }

        // 세로줄 검사
        for (int j = 0; j < 5; j++) {
            boolean flag = true;
            for (int i = 0; i < 5; i++) {
                if (arr[i][j] != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                count++;
            }
        }

        // 대각선 검사
        boolean flag = true;
        for (int i = 0; i < 5; i++) {
            if (arr[i][i] != 0) {
                flag = false;
                break;
            }
        }
        if (flag) {
            count++;
        }

        flag = true;
        for (int i = 0; i < 5; i++) {
            if (arr[i][4 - i] != 0) {
                flag = false;
                break;
            }
        }
        if (flag) {
            count++;
        }
        if (count >= 3) {
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2578.txt"));

        Scanner sc = new Scanner(System.in);
        int[][] arr = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        boolean flag = false;
        int i = 0;

        for (i = 0; !flag; i++) {
            int num = sc.nextInt();
            find(arr, num);
            flag = check(arr);
        }

        System.out.println(i);
    }
}
