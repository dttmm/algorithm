package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 설계 14분 구현 12분
 누적합
 1000*1000이라 모든 경우의 수를 구하여 완탐해도 가능할 것으로 판단
 두 배열의 누적합(sumA, sumB)을 각각 구한뒤
 누적합을 이중 포문으로 돌면서
 부 배열의 합으로 나올 수 있는 모든 경우의 수를 각각의 해시(mapA, mapB)에 저장함

 하나의 해시(mapA)를 돌면서
 다른 해시(mapB)에 있는 수와 합하여
 T를 만족할 수 있는 경우가 있는 경우
 경우의 수를 더해주었음
 */
public class Main2143 {

    static int N;
    static int M;
    static int T;
    static int[] arrA;
    static int[] arrB;
    static int[] sumA;  // A의 누적합
    static int[] sumB;  // B의 누적합
    static Map<Integer, Integer> mapA;  // A 부 배열 합의 모든 경우의 수
    static Map<Integer, Integer> mapB;  // B 부 배열 합의 모든 경우의 수

    // 부 배열의 합 경우의수 구하기
    static void setMap() {
        // A 부 배열 합의 모든 경우의 수 구하기
        for (int i = 0; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                int sum = sumA[j] - sumA[i];

                if (mapA.containsKey(sum)) mapA.put(sum, mapA.get(sum) + 1);
                else mapA.put(sum, 1);
            }
        }

        // B 부 배열 합의 모든 경우의 수 구하기
        for (int i = 0; i <= M; i++) {
            for (int j = i + 1; j <= M; j++) {
                int sum = sumB[j] - sumB[i];

                if (mapB.containsKey(sum)) mapB.put(sum, mapB.get(sum) + 1);
                else mapB.put(sum, 1);
            }
        }
    }

    // T를 만족하는 경우 구하기
    static long solve() {
        long answer = 0;

        // A 해시 돌면서
        for (int a : mapA.keySet()) {
            int b = T - a;

            // T를 만족할 수 있는 값이 B 해시에 있는 경우
            if (mapB.containsKey(b)) answer += (long) mapA.get(a) * mapB.get(b);
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2143.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        arrA = new int[N];
        sumA = new int[N + 1];
        mapA = new HashMap();

        // A 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arrA[i] = n;
            sumA[i + 1] = sumA[i] + n;
        }

        M = Integer.parseInt(br.readLine());
        arrB = new int[M];
        sumB = new int[M + 1];
        mapB = new HashMap();

        // B 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int n = Integer.parseInt(st.nextToken());
            arrB[i] = n;
            sumB[i + 1] = sumB[i] + n;
        }

        // 부 배열의 합 경우의수 구하기
        setMap();

        // T를 만족하는 경우 구하기
        long answer = solve();

        System.out.println(answer);
    }
}
