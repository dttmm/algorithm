package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 기본 조합 문제에 정렬만 추가한 간단한 문제
 입력받은 배열을 정렬한 뒤
 조합 돌려주었음
 */
public class Main15654 {

    static int N;
    static int M;
    static int[] arr;
    static int[] tr;
    static boolean[] visited;
    static BufferedWriter bw;

    static void p(int k) throws Exception {
        if (k == M) {
            for (int i = 0; i < M; i++) {
                bw.write(tr[i] + " ");
            }
            bw.newLine();
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
        System.setIn(new FileInputStream("res/baekjoon/15654.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        tr = new int[M];
        visited = new boolean[N];

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
