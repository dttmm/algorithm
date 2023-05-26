package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 설계 6분 구현 9분
 여행 경로를 다니기 위해서는
 여행 경로에 포함되어 있는 도시들이 모두 다 연결 되어 있어야함
 바로 유니온파인드 유레카

 도시들의 연결 정보를 받을 때
 도시가 연결 되어 있을 경우 바로 도시들을 union해줌
 그리고
 예행 계획에 포함되어 있는 도시들을 하나의 set에 넣고
 set을 돌면서
 안에 있는 도시들이 다 연결 되어 있는지 확인함
 */
public class Main1976 {

    static int N;
    static int M;
    static int[] p;

    // 부모 찾기
    static int getP(int x) {
        if (x == p[x]) return x;
        return p[x] = getP(p[x]);
    }

    // 부모 합치기
    static void union(int a, int b) {
        int aP = getP(a);
        int bP = getP(b);

        if (aP <= bP) p[bP] = aP;
        else p[aP] = bP;
    }

    // 부모 같은지 확인
    static boolean isSameP(int a, int b) {
        if (getP(a) == getP(b)) return true;
        return false;
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1976.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        p = new int[N + 1];

        // 부모 초기화
        for (int i = 1; i <= N; i++) {
            p[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int n = Integer.parseInt(st.nextToken());

                if (n == 0) continue;

                // 부모 합치기
                union(i, j);
            }
        }

        // 여행 계획 도시 추리기
        Set<Integer> set = new HashSet();
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            int n = Integer.parseInt(st.nextToken());
            set.add(n);
        }

        // 부모 같은지 확인
        boolean flag = true;    // 모두 다 부모가 같은지 플래그
        int parent = -1;
        for (int n : set) {
            if (parent == -1) {
                parent = p[n];
                continue;
            }

            // 부모가 다른 경우
            if (parent != p[n]) {
                flag = false;
                break;
            }
        }

        if (flag) System.out.println("YES");
        else System.out.println("NO");
    }
}
