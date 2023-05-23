package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 도저히 아이디어가 생각 안나서
 문제 유형 봤음
 근데도 생각 안나서 헬프

 와
 이분탐색의 기준을 두 사이의 거리로 할 수도 있구나
 나는 계속 집의 위치를 어떤 것을 골라야할지 고민중이었음
 어렵다 어려워
 설계 14분 구현 9분
 */
public class Main2110 {

    static int N;
    static int C;
    static int answer;
    static int[] arr;

    // 이분 탐색
    static void solve(int s, int e) {

        if (s > e) return;

        // 두 공유이 사이의 거리
        int mid = (s + e) / 2;

        // 두 공유기 사이의 거리가 mid이상인 경우 카운트
        int count = 1;
        int prev = arr[0];
        for (int i = 1; i < N; i++) {
            int diff = arr[i] - prev;
            if (diff >= mid) {
                prev = arr[i];
                count++;
            }
        }

        // 두 공유기 사이의 거리가 mid이상인 경우가 C이상일 경우
        if (count >= C) {
            solve(mid + 1, e);
            answer = Math.max(answer, mid);
        }
        // 두 공유기 사이의 거리가 mid이상인 경우가 C미만일 경우 -> mid 줄여서 count 더 세야됨
        else {
            solve(s, mid - 1);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2110.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        answer = 0;
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            arr[i] = n;
        }

        Arrays.sort(arr);

        solve(1, 1000000000);
        System.out.println(answer);
    }
}
