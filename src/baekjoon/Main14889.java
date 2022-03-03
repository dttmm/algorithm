package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14889 {
    static int N;
    static int[][] arr;
    static int[] tr1;
    static int min;

    public static void c(int k, int s) {
        if (k == N / 2) {
            int[] tr2 = new int[N / 2];
            int idx = 0;
            for (int i = 1; i <= N; i++) {
                boolean flag = true;
                for (int j = 0; j < N / 2; j++) {
                    if (tr1[j] == i) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    tr2[idx++] = i;
                }
            }

            int sum1 = 0;
            for (int i = 0; i < N / 2; i++) {
                for (int j = i + 1; j < N / 2; j++) {
                    sum1 += arr[tr1[i]][tr1[j]];
                    sum1 += arr[tr1[j]][tr1[i]];
                }
            }

            int sum2 = 0;
            for (int i = 0; i < N / 2; i++) {
                for (int j = i + 1; j < N / 2; j++) {
                    sum2 += arr[tr2[i]][tr2[j]];
                    sum2 += arr[tr2[j]][tr2[i]];
                }
            }

            min = Math.min(min, Math.abs(sum1 - sum2));

        } else {
            for (int i = s; i <= N; i++) {
                tr1[k] = i;
                c(k + 1, i + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/14889.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1][N + 1];
        tr1 = new int[N / 2];
        min = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        c(0, 1);
        System.out.println(min);
    }
}
