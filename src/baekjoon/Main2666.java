package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 4분 구현 4분
 매 선택마다 둘 중 어느 문을 선택할지에 따라 결과가 달라지고
 최대 횟수가 적다? -> 바로 완탐

 매 선택마다 두 개의 문을 각각 선택하는 경우를
 재귀를 통해 구현
 중간에 누적값이 이미 최소값보다 큰 경우 가지치기 함
 */
public class Main2666 {

    static int N;
    static int M;
    static int[] target;    // 사용할 벽장 순서
    static int min;

    // 완탐
    static void solve(int k, int n1, int n2, int total) {
        // 벽장 다 사용한 경우
        if (k == M) min = Math.min(min, total);
        // 이미 최소값보다 큰 경우
        if (total >= min) return;

        int diff1 = Math.abs(target[k] - n1);
        int diff2 = Math.abs(target[k] - n2);

        // 열린문 하나를 선택했을 때
        solve(k + 1, target[k], n2, total + diff1);
        // 나머지 하나를 선택했을 때
        solve(k + 1, n1, target[k], total + diff2);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2666.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        target = new int[M];
        min = Integer.MAX_VALUE;

        for (int i = 0; i < M; i++) {
            int n = Integer.parseInt(br.readLine());
            target[i] = n;
        }

        solve(0, n1, n2, 0);

        System.out.println(min);
    }
}
