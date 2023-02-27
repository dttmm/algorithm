package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 간단한 유니온 파인드 문제
 그냥 유니온 파인드 문제..
 정답률이 낮길래 뭔가 함정이 있나하고 쫄았음..
 */
public class Main1717 {

    static int N;
    static int M;
    static int[] p;

    // 부모 구하기
    static int getP(int x) {
        if (x == p[x]) return x;
        return p[x] = getP(p[x]);
    }

    // 합치기
    static void unionP(int a, int b) {
        int aP = getP(a);
        int bP = getP(b);

        if (aP < bP) p[bP] = aP;
        else p[aP] = bP;
    }

    // 부모 같은지 확인
    static boolean isSameP(int a, int b) {
        return getP(a) == getP(b);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1717.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        p = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            p[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (type == 0) {
                // 이미 부모 같으면 패쓰
                if (isSameP(a, b)) continue;    // 이거 없어도 속도차이 비슷하네
                unionP(a, b);
            } else {
                if (isSameP(a, b)) bw.write("YES\n");
                else bw.write("NO\n");
            }
        }

        bw.flush();
        bw.close();
    }
}
