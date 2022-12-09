package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 각 줄에서 빨강, 초록, 파랑을 선택했을 때의
 최소값을 담은 d배열 만듬
 내가 만약 n번째 줄에서 빨강(0)을 선택한 경우,
 바로 윗줄(d[n-1])에서 빨강을 제외한 나머지 두개 중 최소값을 고르고
 나의 비용(arr[n][0])과 더하면 n번쨰 줄에서 빨강색을 골랐을 떄의 최소값(d[n][0])이 되는 거임
 이렇게 파랑, 초록일때의 최소값도 다 구해주고
 이 과정을 n번 반복해주면 됨

 아 min 초기값 그냥 편하게 Integer.MAX_VALUE써야겠다..
 괜히 1000으로 했다가 예외 생각지도 못했네
 */
public class Main1149 {

    static int N;
    static int[][] arr;
    static int[][] d;

    // d의 n-1번쨰 줄에서 1,2,3번 중 index를 제외한 번호 중 d 값이 큰 것을
    // arr의 index와 더해서 d의 n번째줄에 갱신 시키는 함수
    static void select(int n, int index) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            if (i == index) continue;

            min = Math.min(min, d[n - 1][i]);
        }

        d[n][index] = min + arr[n][index];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1149.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][3];
        d = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }

        d[0] = arr[0];
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                select(i, j);
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            min = Math.min(min, d[N - 1][i]);
        }

        System.out.println(min);
    }
}
