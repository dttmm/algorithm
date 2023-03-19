package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 유니온파인드 기본 문제
 M만큼 주어진 입력 돌면서 부모 합치다가
 같은 부모인 경우 탈출
 부모가 같다는 것은 사이클을 의미
 */
public class Main20040 {

    static int N;
    static int M;
    static int[] p;

    // 부모 구하기
    static int getP(int x) {
        if (x == p[x]) return x;
        return p[x] = getP(p[x]);
    }

    // 부모 합치기
    static void unionP(int a, int b) {
        int aP = getP(a);
        int bP = getP(b);

        if (a < b) p[aP] = bP;
        else p[bP] = aP;
    }

    // 부모 같은지 확인
    static boolean isSameP(int a, int b) {
        return getP(a) == getP(b);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/20040.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        p = new int[N];

        for (int i = 1; i < N; i++) {
            p[i] = i;
        }

        int count = 0;
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 사이클 만난 경우(부모가 같은 경우)
            if (isSameP(a, b)) {
                count = i;
                break;
            }

            unionP(a, b);
        }

        System.out.println(count);
    }
}
