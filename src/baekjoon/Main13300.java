package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main13300 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/13300.txt"));

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] mArr = new int[6];
        int[] fArr = new int[6];

        for (int i = 0; i < N; i++) {
            int gender = sc.nextInt();
            int grade = sc.nextInt();
            if (gender == 0) {
                fArr[grade - 1]++;
            } else {
                mArr[grade - 1]++;
            }
        }

        int sum = 0;
        for (int i : mArr) {
            sum += i / K;
            if (i % K != 0) {
                sum += 1;
            }
        }
        for (int i : fArr) {
            sum += i / K;
            if (i % K != 0) {
                sum += 1;
            }
        }
        System.out.println(sum);
    }
}
