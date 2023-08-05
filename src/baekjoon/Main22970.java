package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 11분 구현 5분
 완탐
 5000*5000 = 2천5백만 이므로
 모든 점에 대해서
 자신이 산의 꼭대기라고 할때
 자신보다 왼쪽 산의 길이(left)와
 자신보다 오른쪽 산의 길이(right)를 구해주면
 왼쪽 + 오른쪽 산의 길이의 최대값을 구해줌
 left와 right에 자기 자신은 중복해서 한번 더 들어가므로
 마지막에 최대값을 구해주고 1을 빼줌
 */
public class Main22970 {

    static int N;
    static int[] arr;
    static int[] right; // 자신이 산의 꼭대기 일때 오른쪽 산의 길이
    static int[] left;  // 왼쪽 산의 길이

    // 산의 길이 구하기
    static void solve() {
        for (int i = 0; i < N; i++) {

            // 오른쪽 산의 길이 구하기
            int L = i;  // 기준
            int R = i + 1;
            int count = 1;

            while (R < N) {
                // 오른쪽으로 갈수록 높이 낮아져야함
                if (arr[L] <= arr[R]) break;
                count++;
                L++;
                R++;
            }

            right[i] = count;

            // 왼쪽 산의 길이 구하기
            L = i - 1;
            R = i;  // 기준
            count = 1;

            while (L >= 0) {
                // 왼쪽으로 갈수록 높이 낮아져야함
                if (arr[L] >= arr[R]) break;
                count++;
                L--;
                R--;
            }

            left[i] = count;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/22970.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        right = new int[N];
        left = new int[N];

        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        // 산의 높이 구하기
        solve();

        // 최대값 구하기
        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, left[i] + right[i]);
        }

        System.out.println(max - 1);
    }
}
