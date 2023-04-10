package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 먼저 에라토스테네스의 체로 소수들 sosu 배열에 구해준 뒤
 구해준 배열 list에 담음
 list 돌면서 소수의 누적 합 다시 sosu배열에 담아줌

 누적합이 담긴 sosu배열 돌면서
 현재 누적합 - 이전 누적합을 빼면서
 N이 나오는 경우 카운트 해줌
 N을 넘어가는 경우 계속 진행해봤자 계속 N을 넘어가므로 break하여 끊어줌

 그리고 현재 누적합이 N인지 검사 한번 해줌
*/
public class Main1644 {

    static int N;
    static long[] sosu; // 소수 판별할 배열이자 소수 누적합 담을 배열
    static List<Integer> list;  // 소수 담을 리스트
    static int answer;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1644.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sosu = new long[N + 1];
        list = new ArrayList();
        answer = 0;

        for (int i = 2; i <= N; i++) {
            sosu[i] = 1;
        }

        // 소수 구하기
        for (int i = 2; i <= N; i++) {
            if (sosu[i] == 0) continue;
            for (int j = i * 2; j <= N; j += i) {
                sosu[j] = 0;
            }
        }

        // 소수 리스트에 넣기
        for (int i = 2; i <= N; i++) {
            if (sosu[i] == 0) continue;
            list.add(i);
        }

        // 소수 누적합 만들기
        sosu = new long[list.size()];
        long sum = 0;
        for (int i = 0; i < sosu.length; i++) {
            sum += (long) list.get(i);
            sosu[i] = sum;
        }

        // 소수 누적합 돌면서
        for (int i = 0; i < sosu.length; i++) {
            long n = sosu[i];

            // 현재 누적합이 N인 경우
            if (n == N) answer++;

            // 자신의 이전 누적합들을 빼면서
            for (int j = i - 1; j >= 0; j--) {
                long m = sosu[j];
                long subSum = n - m;

                // 현재 누적합과 이전 누적합의 차가 N이상인 경우
                if (subSum > N) break;

                // N을 찾은 경우
               if (subSum == N) answer++;
            }
        }

        System.out.println(answer);
    }
}
