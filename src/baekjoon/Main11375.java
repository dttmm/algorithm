package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 구현 14분
 네트워크 플로우 복습하려고 했는데
 이분매칭이었네
 그냥 이분매칭 복습함
 원리 복기하면서 로직 짜봄
 */
public class Main11375 {

    static int N;
    static int M;
    static List<Integer>[] lists;
    static int[] matched;
    static boolean[] isCompleted;

    // isCompleted배열 초기화
    static void initCompleted() {
        isCompleted = new boolean[M + 1];
    }

    // 이분 매칭
    static boolean solve(int v) {
        // 아무 것도 매칭 안되있는 경우
        if (v == 0) return true;

        for (int u : lists[v]) {
            if (isCompleted[u]) continue;

            isCompleted[u] = true;

            if (solve(matched[u])) {
                matched[u] = v;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11375.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lists = new List[N + 1];
        matched = new int[M + 1];

        // 입력 받기 & 초기화
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            lists[i] = new ArrayList();
            for (int j = 0; j < n; j++) {
                int work = Integer.parseInt(st.nextToken());
                lists[i].add(work);
            }
        }

        // 이분 매칭
        for (int i = 1; i <= N; i++) {
            initCompleted();
            solve(i);
        }

        // 정답 구하기
        int answer = 0;
        for (int i = 1; i <= M; i++) {
            if (matched[i] == 0) continue;
            answer++;
        }

        System.out.println(answer);
    }
}
