package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 1분 구현 18분
 수학 문제
 제일 작은 원소를 기준으로 정하고
 각각 연산할 때마다 기준값만 변경해가며 풀음

 모든 수에 곱하기 할 때를 고려해주는 경우가 가장 헷갈렸음
 처음에는 곱한 수 누적(multiple)을 더해버려서 틀렸음
 곱해주어야 되는 구나

 설계 더 꼼꼼히이이잉
 */
public class Main27973 {

    static int N;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/27973.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        long standard = 1;  // 제일 작은 수
        int multiple = 1;   // 몇번 곱했는지
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int type = Integer.parseInt(st.nextToken());

            // 더하기
            if (type == 0) {
                long n = Long.parseLong(st.nextToken());

                standard += n;
            }
            // 곱하기
            else if (type == 1) {
                long n = Long.parseLong(st.nextToken());

                multiple *= n;
                standard *= n;
            }
            // 제거
            else if (type == 2) {
                long n = Long.parseLong(st.nextToken());

                standard += multiple * n;
            } else {
                sb.append(standard + "\n");
            }
        }

        System.out.println(sb);
    }
}
