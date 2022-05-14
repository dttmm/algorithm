package baekjoon.제1회곰곰컵;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainD {
    static int N;
    static int[] count;
    static int size;
    static int[][] arr;
    static int[] tr;
    static String answer;

    public static void solve(int k) {
        if (k == N) {
            int start = 1;
            for (int i = 0; i < N; i++) {
                System.out.print(tr[i]);
                int n = tr[i];
                start += n;
                if (start % 7 == 5) {
                    answer = "YES";
                    break;
                }
            }
            System.out.println();
        } else {
            for (int i = 0; i < size; i++) {
                if (answer.equals("YES")) break;
                if (arr[i][1] > 0) {
                    arr[i][1]--;
                    tr[k] = arr[i][0];
                    solve(k + 1);
                    arr[i][1]++;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/제1회곰곰컵/D.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        count = new int[100001];
        size = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (count[n] == 0) size++;
            count[n]++;
        }

        arr = new int[size][2];
        tr = new int[N];
        int idx = 0;
        for (int i = 1; i <= 100000; i++) {
            if (count[i] != 0) {
                arr[idx][0] = i;
                arr[idx++][1] = count[i];
            }
        }

        answer = "NO";
        solve(0);

        System.out.println(answer);
    }
}
