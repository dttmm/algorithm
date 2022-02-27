package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1961 {
    static int N;
    static int[][] arr;
    static String[] s;

    public static void turn() {
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = arr[N - 1 - j][i];
            }
        }
        arr = temp;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s[i] += arr[i][j];
            }
            s[i] += " ";
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/swea/1961.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            s = new String[N];
            for (int i = 0; i < N; i++) {
                s[i] = "";
            }
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            turn();
            turn();
            turn();
            System.out.println("#" + test_case + " ");
            for (int i = 0; i < N; i++) {
                System.out.println(s[i]);
            }
        }
    }
}