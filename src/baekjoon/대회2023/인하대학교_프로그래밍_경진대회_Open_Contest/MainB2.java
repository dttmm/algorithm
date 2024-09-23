package baekjoon.대회2023.인하대학교_프로그래밍_경진대회_Open_Contest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MainB2 {

    static final int R = 1000000007;
    static long N;
    static int M;
    static int[][] arr;
    static long answer;
    static Map<Long, Long> map;

    static long pow(int x, long n) {
        if (n == 0) return 1;
        if (n == 1) return 6;
        if (map.get(n) != null) return map.get(n);

        long half = pow(x, n / 2) % R;

        if (n % 2 == 1) {
            long result = (((x * half) % R) * half) % R;
            map.put(n, result);
            return result;
        } else {
            long result = (half * half) % R;
            map.put(n, result);
            return result;
        }
    }

    static void solve(int k, int pick_j, int sum) {

        if (sum >= M) {
            answer = (answer + pow(6, N - k)) % R;
            return;
        }

        if (k == N) return;

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 2; i++) {
                if (j == pick_j) solve(k + 1, j, sum + arr[i][j] / 2);
                else solve(k + 1, j, sum + arr[i][j]);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/인하대학교_프로그래밍_경진대회_Open_Contest/B2.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[2][3];
        answer = 0;
        map = new HashMap();

        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                int n = Integer.parseInt(st.nextToken());
                arr[i][j] = n;
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                solve(1, j, arr[i][j]);
            }
        }

        System.out.println(answer);
    }
}
