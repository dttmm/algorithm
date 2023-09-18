package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 5분 구현 14분
 순열
 N이 무척 작으므로 모든 경우의 수를 따져도 됨
 순열을 이용해서 사람들의 배치하고
 왼쪽에서 자기보다 큰 사람의 수를 센 결과가
 우리가 원하는 결과일 때를 찾음
 */
public class Main1138 {

    static int N;
    static int[] tr;
    static int[] target;
    static String answer;
    static boolean[] visited;
    static boolean flag;    // 정답 찾았는지 플래그

    // 순열
    static void solve(int k) {
        if (k == N) {
            int[] count = new int[N];
            // 자기보다 왼쪽에서 큰 사람 수 세기
            for (int i = 0; i < N; i++) {
                int n = tr[i];
                for (int j = i - 1; j >= 0; j--) {
                    if (tr[j] > n) count[n]++;
                }
            }

            flag = true;
            // 원한느 결과와 일치하는지 검사
            for (int i = 0; i < N; i++) {
                if (target[i] != count[i]) {
                    flag = false;
                    break;
                }
            }
        } else {
            for (int i = 0; i < N; i++) {
                if (visited[i]) continue;

                visited[i] = true;
                if (flag) return;   // 정답 찾았으면 완성된 배열 건들면 안됨
                tr[k] = i;
                solve(k + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1138.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tr = new int[N];
        target = new int[N];
        answer = "";
        visited = new boolean[N];
        flag = false;

        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            target[i] = n;
        }

        // 순열
        solve(0);

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(tr[i] + 1 + " ");
        }

        sb.deleteCharAt(sb.length() - 1);

        System.out.println(sb);
    }
}
