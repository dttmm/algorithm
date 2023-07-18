package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 설계 8분 구현 12분 디버깅 4분
 dp + bfs
 계단을 올라 가면서
 몇번 째 계단에 몇 번만에 올라갔는지 저장하면서
 목적 계단에 도달 했는지 체크함

 틀림
 배열의 크기를 백만으로 잡았는데
 백만보다 큰 계단을 올라가는 경우를 고려 못했음
 범위 체크 추가함

 틀림
 목적 계단 보다 높이 올라가는 것이 아니라
 정확히 목적 계단에 도달할 수 있는지 없는지 확인하는 거였구나
 */
public class Main28069 {

    static final int LIMIT = 1000000;
    static int N;
    static int K;
    static int[] d; // i번째 계단에 몇 번만에 도착했는지

    // dp + bfs
    static boolean solve() {
        Queue<Integer> queue = new LinkedList();
        queue.add(0);
        d[0] = 0;

        // 정답 플랙그
        boolean flag = false;
        while (!queue.isEmpty()) {
            // 현재 계단
            int cur = queue.poll();

            // 목적 계단에 도착한 경우
            if (cur == N) {
                flag = true;
                break;
            }

            // 이동 횟수 다 쓴 경우
            if (d[cur] == K) continue;

            // 계단을 한칸 올라갈 수 있는 경우
            if (cur + 1 > LIMIT) continue;  // 범위 체크
            if (d[cur + 1] == -1) {
                d[cur + 1] = d[cur] + 1;
                queue.add(cur + 1);
            }

            // 계단을 i + i/2칸 올라갈 수 있는 경우
            if (cur + cur / 2 > LIMIT) continue;  // 범위 체크
            if (d[cur + cur / 2] == -1) {
                d[cur + cur / 2] = d[cur] + 1;
                queue.add(cur + cur / 2);
            }
        }

        return flag;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/28069.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        d = new int[LIMIT + 1];

        // 초기화
        for (int i = 0; i <= LIMIT; i++) d[i] = -1;

        // 정답 구하기
        boolean result = solve();

        if (result) System.out.println("minigimbob");
        else System.out.println("water");
    }
}
