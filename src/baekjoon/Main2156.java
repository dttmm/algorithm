package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 아이디어 7분 구현 3분
 딱 봐도 dp
 점화식을 찾기 위해 노력함
 3가지가 연속 될 수 없으므로
 3가지가 연속되지 않는 경우의 수 3가지를 비교함

 현재 포도주 + 이전 포도주 + 이이이전 최대값
 현재 포도주 + 이이전 최대값
 이전 최대값

 위 3가지의 경우 중 최대값을 찾음

 첫 번째 원소의 경우 이이이전 최대값을 비교해야 되므로
 배열의 크기를 3칸더 여유있게 잡아서
 계산하기 편하도록 함
 */
public class Main2156 {

    static int N;
    static int[] arr;
    static int[] d;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2156.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 3];
        d = new int[N + 3];

        for (int i = 3; i < N + 3; i++) {
            int n = Integer.parseInt(br.readLine());
            arr[i] = n;
        }

        // dp
        for (int i = 3; i < N + 3; i++) {
            d[i] = Math.max(d[i - 1], arr[i] + d[i - 2]);
            d[i] = Math.max(d[i], arr[i] + arr[i - 1] + d[i - 3]);
        }

        System.out.println(d[N + 2]);
    }
}
