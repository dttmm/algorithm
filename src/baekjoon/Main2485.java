package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 설계 6분 구현 10분
 gcd
 각 가로수 사이의 거리차이를 구한다음
 거리 차이들의 최대공약수를 구함
 최대공약수로 거리 차이들을 나눈 몫-1을 더해주면 끝

 어떤 거리차이를 최대공약수로 나누고 -1을 해주면
 이것이 바로 두 가로수 사이에 심어야 하는 가로수 개수임
 */
public class Main2485 {

    static int N;
    static Set<Integer> diffSet;    // 가로수 사이의 거리 차이 <- 중복 없이 담기 위함
    static int[] diffArr;           // 가로수 사이의 거리 차이

    // 최대공약수 구하기
    static int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }

    // 가로수 사이의 거리차이들의 최대공약수 반환
    static int solve() {
        int a = -1;
        // 거리차이를 담은 set을 돌면서
        for (int n : diffSet) {
            if (a == -1) {
                a = n;
                continue;
            }

            // 두 값의 최대공약수를 구하고
            int ret = gcd(Math.min(a, n), Math.max(a, n));
            // 최소 최대공약수를 업데이트함 <- 지금까지 비교한 거리차이들의 총 최대공약수인 셈
            a = Math.min(a, ret);
        }

        return a;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2485.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        diffSet = new HashSet();
        diffArr = new int[N - 1];

        // 입력 받기
        int pre = -1;
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            if (pre == -1) {
                pre = n;
                continue;
            }

            // 두 가로수 사이의 거리
            int diff = n - pre;
            diffSet.add(diff);
            diffArr[i - 1] = diff;

            pre = n;
        }

        // 전체 가로수 사이의 거리차이 최대공약수 구함
        int min = solve();

        // 가로수 사이에 심어야 하는 가로수 개수 구함
        int answer = 0;
        for (int i = 0; i < diffArr.length; i++) {
            int n = diffArr[i];
            answer += n / min - 1;
        }

        System.out.println(answer);
    }
}
