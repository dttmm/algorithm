package algorithm.day09bruteforce;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1시간 8분
public class Solution1245 {
    static int[][] arr;
    static double min;
    static double min_x;
    static int N;

    public static double calForce(double x) {
        double force = 0.0;
        for (int k = 0; k < N; k++) {
            if (arr[0][k] < x) {
                force += arr[1][k] / Math.pow((x - arr[0][k]), 2);
            } else {
                force -= arr[1][k] / Math.pow((x - arr[0][k]), 2);
            }
        }
        return force;
    }

    public static void cal(double start, double end, int count) {
        int n = 100;
        double interval = 0.0;
//        double interval = (end - start) / 100000;
        if ((end - start) / 10 >= 1) {
            interval = 1.0;
            n = (int) end - (int) start;
        } else {
            interval = (end - start) / 100;
        }

        boolean flag = false;
        double x = 0.0;
        for (int i = 1; i < n; i++) {
            x = start + interval * i;
            double force = calForce(x);
            if (Math.abs(force) < min) {
                min = Math.abs(force);
                min_x = x;
            }

            if (Math.abs(force) <= 0.0000000001) {
                flag = true;
                for (int j = -1000; j < 1000; j++) {
                    x = min_x - 0.00000000001 * j;
                    force = calForce(x);
                    if (Math.abs(force) < min) {
                        min = Math.abs(force);
                        min_x = x;
                    }
                }
                return;
            }

            if (flag) break;
        }
        if (!flag) {
            cal(min_x - interval, min_x + interval, count++);

        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day09_1245.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {


            N = Integer.parseInt(br.readLine());
            arr = new int[2][N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[0][i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < N; i++) {
                arr[1][i] = Integer.parseInt(st.nextToken());
            }

            System.out.print("#" + test_case + " ");
            for (int k = 0; k < N - 1; k++) {
                min = Double.MAX_VALUE;
                min_x = 0.0;
                cal(arr[0][k], arr[0][k + 1], k);
                System.out.printf("%.10f ", min_x);
            }
            System.out.println();
        }
    }
}