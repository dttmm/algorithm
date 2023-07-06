package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 17분
 이분매칭 공부함
 로직이 신기하고 어지럽네
 */
public class Main2188 {

    static int N;
    static int M;
    static List<Integer>[] lists;   // 연결 정보 담음
    static int[] matched;   // B그룹 원소(i)와 어떤 A그룹 원소(matched[i])가 매칭 되었는지
    static boolean[] completed; // B그룹 원소(i) 매칭 완료 여부

    // completed 초기화
    static void initCompleted() {
        completed = new boolean[M + 1];
    }

    // A그룹의 v원소 매칭 시키기
    static boolean match(int v) {
        // 아무것도 매칭 되지 않은 경우
        if (v == 0) return true;

        for (int u : lists[v]) {
            if (completed[u]) continue;
            completed[u] = true;

            if (match(matched[u])) {
                matched[u] = v;
                return true;
            }
        }
        // 어떤한 것도 매칭 실패
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2188.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lists = new List[N + 1];
        matched = new int[M + 1];
        completed = new boolean[M + 1];

        // 초기화
        for (int i = 1; i <= N; i++) {
            lists[i] = new ArrayList();
        }

        // 입력 받기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            for (int j = 0; j < t; j++) {
                int n = Integer.parseInt(st.nextToken());
                lists[i].add(n);
            }
        }

        // A그룹 원소 돌면서 매칭 시키기
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            initCompleted();

            boolean result = match(i);
            if (result) answer++;
        }
        System.out.println(answer);
    }
}
