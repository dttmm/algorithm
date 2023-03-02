package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 배열을 뒤에서 부터 돌려서
 상담 완료일이 제한을 넘어가는 경우와 그렇지 않은 경우로 분기해서
 최대값을 구해줌

 1년전 뉴비시절에 전혀 이해조차 안됐었던 문제
 이젠 껌이쥬???
 */
public class Main15486 {

    static int N;
    static int[] trr;
    static int[] prr;
    static int[] d;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/15486.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        trr = new int[N + 2];   // 기간 담을 배열
        prr = new int[N + 2];   // 금액 담을 배열
        d = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            trr[i] = t;
            prr[i] = p;
        }

        for (int i = N; i > 0; i--) {
            // 완료일이 제한을 넘어가는 경우 -> 다음 날 값 그대로 가져옴
            if (i + trr[i] > N + 1) d[i] = d[i + 1];

            // 완료일이 제한 안에 있는 경우 -> 다음 날 값과 완료일 값 + 현재 일이 끝났을 때의 금액 중 최대값 고름
            else {
                d[i] = Math.max(d[i + 1], d[i + trr[i]] + prr[i]);
            }
        }

        System.out.println(d[1]);
    }
}
