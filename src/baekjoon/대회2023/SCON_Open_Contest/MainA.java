package baekjoon.대회2023.SCON_Open_Contest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 1분 구현 2분
 간단한 분기 문제
 N과 B중에 더 큰 시간이 최종적으로 지하철까지 타는데 걸리는 시간이므로
 N과 B중 최대값을 구해서 B에 저장하고
 A와 B를 비교하여 분기처리함
 */
public class MainA {

    static int N;
    static int A;
    static int B;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/SCON_Open_Contest/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 지하철까지 걸어가는 시간
        A = Integer.parseInt(st.nextToken());   // 버스가 올 때까지 걸리는 시간
        B = Integer.parseInt(st.nextToken());   // 지하철이 올 때까지 걸리는 시간

        B = Math.max(N, B);

        if (A > B) {
            System.out.println("Subway");
        } else if (A < B) {
            System.out.println("Bus");
        } else {
            System.out.println("Anything");
        }
    }
}
