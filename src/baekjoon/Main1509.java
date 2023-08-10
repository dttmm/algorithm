package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 설계 32분 구현 44분
 dp
 최소값을 찾기 위해 모든 경우를 탐색해 보는 수 밖에 없어서
 효율적인 방법을 찾음
 어떤 구간 [s ~ e]에서의 최소값은
 [s ~ k]구간의 최소값 + [k+1 ~ e]구간의 최소값 (s <= k < e)으로 구했음
 [s ~ e]구간이 팰린드롬인지에 대한 정보를 dp를 이용하여 f[][]에 미리 구해놓고
 [s ~ e]에서의 최소값도 dp를 이용하여 d[][]에 구함

 시간 초과
 f[][]를 구하는 과정은 N^2이라 시간 초과에 영향을 주지 않는데
 d[][]를 구하기 위해 계속 쪼개는 과정에서 시간 초과에 영향을 주는 것으로 보임
 최소값을 중간에서 가지치기 하여 연산을 줄이려고 했지만
 구간을 쪼개 들어가다 보니
 처음부터 끝까지 모든 구간에 대하여 검사를 했는지 여부를 확인할 수가 없어 실패

 결국 도움!
 와
 아이디어 너무 어렵다
 일차원 배열을 이용하여 d[] i구간 까지의 최소값을 구할 수 있군하
 [k~i]구간이 팰린드롬이 아니면 무시하고
 팰리드롬이면 갱신을 해나가는 발상이 신기하다
 어차피 마지막에 [i~i]구간은 무조건 팰린드롬이여서
 팰린드롬이 아닌 구간을 무시해도
 뒤로 갈수록 알아서 최소값 갱신이 되는 군하
 */
public class Main1509 {

    static int N;
    static char[] arr;
    static boolean[][] f;   // [s][e]: s~e구간이 팰린드롬인지
    static int[] d;         // i구간 까지 팰린드롬 분할의 최소값

    // s~e 구간이 팰린드롬인지 구하기
    static void findF() {
        for (int e = 1; e <= N; e++) {
            for (int s = 1; s <= e; s++) {

                // 한 문자인 경우
                if (s == e) {
                    f[s][e] = true;
                    continue;
                }

                // 양 끝의 문자가 다른 경우
                if (arr[s] != arr[e]) continue;

                // s와 e사이 문자열이 없을 경우
                if (s + 1 > e - 1) {
                    f[s][e] = true;
                    continue;
                }

                // s와 e사이의 문자열이 팰린드롬이 아닌 경우
                if (!f[s + 1][e - 1]) continue;

                f[s][e] = true;
            }
        }
    }

    // i구간 까지 팰린드롬 분할의 최소값 구하기
    static void solve() {
        for (int R = 1; R <= N; R++) {
            for (int L = 1; L <= R; L++) {
                // L~R 구간이 팰린드롬이 아닌 경우
                if (!f[L][R]) continue;
                d[R] = Math.min(d[R], d[L - 1] + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1509.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        N = s.length();
        f = new boolean[N + 2][N + 2];
        arr = new char[N + 1];
        d = new int[N + 1];

        // 입력 받기
        for (int i = 1; i <= N; i++) {
            char c = s.charAt(i - 1);
            arr[i] = c;

            // 초기화
            d[i] = Integer.MAX_VALUE;
        }

        // 팰린드롬 찾기
        findF();

        // 최소값 찾기
        solve();

        System.out.println(d[N]);
    }
}
