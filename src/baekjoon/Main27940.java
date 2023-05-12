package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 3분 구현 17분
 아놔
 낚시 문제 였네
 예시가 너무 예쁘게 배열 이용하라고 나와있어서
 배열 이용해서 층마다 빗물 누적해 주었는데
 시간초과 나길래
 대체 어떻게 최적화를 할까
 treeset으로 살짝 고민하고 있었는데

 층마다 빗물 누적할 필요 없이
 1층의 빗물값만 누적해주면 되는 거였네
 아무 층이나 출력하라는 게 핵심이었네
 이런~
 */
public class Main27940 {

    static int N;
    static int M;
    static int K;
    static int total;   // 1층에 누적된 빗물

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/27940.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] answer = new int[2];  // 0: i번째 비 1: 무너진 층
        boolean flag = false;
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            flag = false;

            total += r;
            if (total > K) {
                answer[0] = i;
                answer[1] = 1;
                flag = true;
                break;
            }
        }

        if (flag) System.out.println(answer[0] + " " + answer[1]);
        else System.out.println("-1");
    }
}
