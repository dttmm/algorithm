package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 설계 26분 구현 6분 디버깅 11분
 이분매칭
 한 그룹에서 다른 그룹으로 최대한 매칭 시키는 문제이므로
 이분매칭으로 접근함
 근데 경우의 수가 1만*1만으로

 시간초과
 이분매칭은 시간 초과가 나므로 다른 아이디어를 생각함
 설계 34분 구현 8분
 그리디
 gi가 낮은 비행기부터
 1번 게이트부터 차례대로 비행기를 도킹시키면서
 가능한 경우의 수르 찾아보려고 했음

 틀림
 결국 도움!
 여기서 유니온파인드를 적용할 수 있다니
 아이디어 너무 어렵다
 */
public class Main10775 {

    static int G;
    static int P;
    static int[] p; // i게이트로 온 경우 value게이트로 가야됨을 알려주는 정보

    // 부모 찾기
    static int getP(int x) {
        if (x == p[x]) return x;
        return p[x] = getP(p[x]);
    }

    // 부모 합치기
    static void unionP(int a, int b) {
        int aP = getP(a);
        int bP = getP(b);
        if (aP < bP) p[bP] = aP;
        else p[aP] = bP;
    }

    // 도킹
    static boolean solve(int n) {
        int parent = getP(n);

        // 가능한 게이트가 없는 경우
        if (parent == 0) return false;

        // 게이트 정보 업데이트
        unionP(parent, parent - 1);

        return true;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10775.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        p = new int[G + 1];

        // 초기화
        for (int i = 1; i <= G; i++) {
            p[i] = i;
        }

        // 도킹
        int count = 0;
        for (int i = 0; i < P; i++) {
            int n = Integer.parseInt(br.readLine());

            boolean result = solve(n);
            if (!result) break;
            count++;
        }

        System.out.println(count);
    }
}
