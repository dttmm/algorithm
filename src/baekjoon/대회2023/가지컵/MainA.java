package baekjoon.대회2023.가지컵;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 조수들중에서
 한명 이상의 조수가 가지고 있는 품종이 모두 W인 경우에만
 키위가 교배했을 때 흰색 가지가 나올 수 있으므로
 모든 조수를 돌면서 가지고 있는 모든 품종이 W인 조수가 나타나면
 W 출력
 */
public class MainA {

    static int N;
    static int M;
    static int K;
    static char[] arr;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/가지컵/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new char[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            char c = st.nextToken().charAt(0);
            arr[i] = c;
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        boolean flag = false;   // true : 가지고 있는 품종이 모두 W임
        // 모든 조수를 돌면서
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (arr[n] == 'P') break;

                // 마지막 품종까지 모두 W인 경우
                if (j == K - 1) flag = true;
            }
            if (flag) break;
        }

        if (flag) System.out.println('W');
        else System.out.println('P');
    }
}
