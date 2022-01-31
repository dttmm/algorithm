package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;
// 16분 + 출력형식이 잘못됐다고 해서 해결한거 합치면 30분
public class Main1244 {
    static int N;

    public static boolean isIn(int pos) {
        if (pos >= 0 && pos < N) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1244.txt"));

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int K = sc.nextInt();
        for (int k = 0; k < K; k++) {
            int gender = sc.nextInt();
            int pos = sc.nextInt();
            // 남학생
            if (gender == 1) {
                for (int i = 0; i < N; i++) {
                    if ((i + 1) % pos == 0) {
                        arr[i] = (arr[i] + 1) % 2;
                    }
                }
            }
            // 여학생
            else {
                for (int i = 0; isIn(pos - 1 - i) && isIn(pos - 1 + i); i++) {
                    if (i == 0) {
                        arr[pos - 1] = (arr[pos - 1] + 1) % 2;
                    } else {
                        if (arr[pos - 1 - i] == arr[pos - 1 + i]) {
                            arr[pos - 1 - i] = (arr[pos - 1 - i] + 1) % 2;
                            arr[pos - 1 + i] = (arr[pos - 1 + i] + 1) % 2;
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.print(arr[i] + " ");
            if ((i + 1) % 20 == 0) {
                System.out.println();

            }
        }
    }
}