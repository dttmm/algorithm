package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;
// 34분
public class Main10157 {
    // x축
    static int C;
    // y축
    static int R;
    static int[][] arr;
    static int newX;
    static int newY;

    // 배열 안쪽 인지 확인
    public static boolean isIn() {
        if ((newX >= 0 && newX < C) && ((newY >= 0) && (newY < R))) {
            return true;
        }
        return false;
    }

    // 해당 인덱스에 이미 자리가 있는지 확인
    public static boolean check() {
        if (arr[newY][newX] == 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10157.txt"));

        Scanner sc = new Scanner(System.in);
        // x축
        C = sc.nextInt();
        // y축
        R = sc.nextInt();

        int K = sc.nextInt();

        arr = new int[R][C];

        // 위->오른->아래->왼
        int[] delX = {0, 1, 0, -1};
        int[] delY = {-1, 0, 1, 0};
        int dir = 0;

        // 시작점
        newX = 0;
        newY = R - 1;

        int X = newX;
        int Y = newY;

        // 좌석을 배정할 수 없는 경우
        if (K > C * R) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < K; i++) {
            X = newX;
            Y = newY;
            arr[Y][X] = 1;
            newX = X + delX[dir];
            newY = Y + delY[dir];
            // 배열을 벗어나거나 해당 인덱스에 이미 자리가 있을 경우 방향을 바꿈
            if (!(isIn() && check())) {
                dir = (dir + 1) % 4;
                newX = X + delX[dir];
                newY = Y + delY[dir];
            }
        }
        System.out.println((X + 1) + " " + (R - Y));
    }
}
