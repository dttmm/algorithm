package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 일반적인 순열 문제에 중복되는 숫자가 있는 케이스
 중복되는 순열을 거르기 위해 set으로
 이미 만들어진 순열이 다시 나오면 패쓰시킴
 */
public class Main15663 {

    static int N;
    static int M;
    static int[] arr;
    static int[] tr;
    static boolean[] visited;
    static Set<String> set;
    static BufferedWriter bw;

    static void p(int k) throws Exception {
        if (k == M) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                sb.append(tr[i] + " ");
            }
            String s = sb.toString();
            if (!set.contains(s)) {
                set.add(s);
                bw.write(s + "\n");
            }
        } else {
            for (int i = 0; i < N; i++) {
                if (visited[i]) continue;

                visited[i] = true;
                tr[k] = arr[i];
                p(k + 1);
                visited[i] = false;

            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/15663.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        tr = new int[M];
        visited = new boolean[N];
        set = new HashSet();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        p(0);

        bw.flush();
        bw.close();
    }
}
