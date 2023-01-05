package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 우선 가능한 해의 최대값은 M,N의 최소 공배수로
 최악의 경우 해의 최대값은 40000 * 39999로
 약 16억이 됨
 완탐을 하게 될 경우 시간초과가 나므로 수학적으로 해야함

 일단, x를 기준으로 생각을 해보았다
 x는 1부터 M까지 반복을 하게 되는데
 반복을 하는 횟수는 M,N의 최소공배수/M 만큼 반복을 하게 됨
 예를 들어 M 10, N 12인 경우
 최소 공배수는 60이므로
 x는 1부터 10까지 총 6번 반복을 하게 됨

 x를 고정한 상태에서 한바퀴 반복을 하게 되면
 그에 해당하는 y값이 바뀌게 되는데
 y값은 ((y + M - 1) % N) + 1만큼 바뀌게 됨
 x가 한바퀴 돈 양만큼 y값도 증가하게 되고
 증가한 y값에 모듈러 연산을 해주었음
 그리고 y값이 N을 초과하게 되는 경우 1부터 다시 시작 되므로
 적절하게 1을 빼고 더해주었음

 x값을 우리가 원하는 x값(targetX)로 고정해두고
 이제 변화된 y값이 우리가 원하는 y값(targetY)과 일치하는지 확인해주기만 하면 됨
 일치하지 않는다면 한 사이클을 돌아야 하므로 횟수(count)에 M만큼 추가함

 y = ((y + M - 1) % N) + 1 자세한 분석
 M 10, N 12일 경우
 y가 2였다면
 y = ((2 + 10 - 1) % N) + 1 -> y = 12가 됨
 기존 그냥 모듈러 연산을 하게 될 경우
 y = (2 + 10) % 12 -> y = 0이 되어버림
 */
public class Main6064_2 {

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/6064.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int targetX = Integer.parseInt(st.nextToken());
            int targetY = Integer.parseInt(st.nextToken());

            int g = gcd(Math.max(M, N), Math.min(M, N));
            int k = N / g;

            int x = targetX;        // x를 우리가 원하는 해로 고정을 한다음
            int y = ((targetX - 1) % N) + 1;    // y는 x가 움직인 만큼 움직여준다
            int count = targetX;    // 원하는 해가 몇번째 해인지
            boolean flag = false;   // 우리가 찾고자 하는 해를 찾았는지 확인할 플래그

            for (int i = 0; i < k; i++) {
                if (y == targetY) {
                    flag = true;
                    break;
                }

                count += M;
                y = ((y + M - 1) % N) + 1;
            }

            if (!flag) count = -1;

            bw.write(count + "\n");
        }

        bw.flush();
        bw.close();
    }
}
