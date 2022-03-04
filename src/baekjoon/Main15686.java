package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main15686 {
    static int N;
    static int M;
    static int[][] arr;
    static int[][] chicken;
    static int[][] home;
    static int chicken_amount;
    static int home_amount;
    static int[][] dir;
    static int[] tr;
    static int min;

    public static void c(int k, int s) {
        if (k == M) {
            int sum = 0;
            for (int j = 0; j < home_amount; j++) {
                int length = Integer.MAX_VALUE;
                for (int i = 0; i < M; i++) {
                    length = Math.min(length, dir[tr[i]][j]);
                }
                sum += length;
            }
            min = Math.min(min, sum);
        } else {
            for (int i = s; i < chicken_amount; i++) {
                tr[k] = i;
                c(k + 1, i + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/15686.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        chicken = new int[2][13];
        home = new int[2][2 * N];
        chicken_amount = 0;
        home_amount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 2) {
                    chicken[0][chicken_amount] = i;
                    chicken[1][chicken_amount++] = j;
                } else if (arr[i][j] == 1) {
                    home[0][home_amount] = i;
                    home[1][home_amount++] = j;
                }
            }
        }


        dir = new int[chicken_amount][home_amount];
        for (int i = 0; i < chicken_amount; i++) {
            for (int j = 0; j < home_amount; j++) {
                dir[i][j] = Math.abs(chicken[0][i] - home[0][j]) + Math.abs(chicken[1][i] - home[1][j]);
            }
        }

        tr = new int[M];
        min = Integer.MAX_VALUE;
        c(0, 0);
        System.out.println(min);
    }
}
