package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 처음에는 펠린드롬이 가능한 모든 조합을 미리 만들었음
 홀수로 만들 수 있는 경우와 짝수로 만들 수 있는 경우를 모두 고려하여
 미리 set에 담는 방식을 사용했는데 시간초과남
 탐색하면서 숫자 하나씩 중심으로 잡고
 양쪽으로 퍼져가면서 가능한 경우에 set에 담음
 2000개를 각각 기준으로 잡고 양쪽으로 검사를 하는 경우
 2000*1000 = 2백만,
 2000개 중에 두개씩 중심으로 기준을 잡고 양쪽으로 검사를 하는 경우에도 2백만
 총 4백만번 연산인데 시간초과..?
 문자열을 만들어서 set에 넣는 과정이 문제인가 싶어
 Node로 set을 관리했는데도 시간 초과
 시간초과가 나는 이유를 모르겠다

 미리 가능한 경우를 만드는 것 말고는
 최적화 방법이 떠오르지 않아서
 문제 유형 봄
 dp길래
 이차원 dp 생각
 이차원 dp에 펠린드롬 표시해보니 규칙이 보임
 두 인덱스의 값이 같고 대각선 위에 있는 값이 펠린드롬이면
 해당 값도 펠린드롬~
 */
public class Main10942 {

    static int N;
    static int M;
    static int[] arr;
    static boolean[][] d;

    static void solve() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                // 두 값이 다른 경우는 패쓰
                if (arr[i] != arr[j]) continue;

                // 인덱스가 같거나
                // 대각선 위에 있는 값이 펠림드롬이거나
                // 범위 내에 검사를 다 한 경우
                if (i == j || d[i - 1][j + 1] || i - 1 < j + 1) d[i][j] = true;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10942.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N + 1];
        d = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }


        solve();

        StringBuilder sb = new StringBuilder();
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if (d[e][s]) sb.append("1\n");
            else sb.append("0\n");
        }
        System.out.println(sb);
    }
}
