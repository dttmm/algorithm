package baekjoon.대회2023.solvedac_Grand_Arena_2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 8분 구현 5분
 dp
 k번째 연산을 수행할때는 k-1번째 연산 결과로 나온 모든 값에 대해서
 연산을 수행해야 됨
 최대 연산 수는 2^200000가 됨
 만약 +1연산이 있다면
 6에 +1을 하거나 13에 +1을 하거나
 연산 결과는 똑같이 7의 배수가 되고
 6과 13을 7로 나눈 나머지는 동일하게 6이므로
 7로 나눈 나머지가 같다면
 어떤 연산을 하든, 연산 결과를 7로 나눈 나머지도 동일하게 됨

 그래서 각 연산마다 7로 나눈 나머지(0~6)중에서 어떤 수가 나왔었는지 연산 결과를 arr에 저장하고
 이전 연산 결과에서 나온 나머지를 바탕으로 다음 가능한 연산을 수행함
 */
public class MainF {

    static int N;
    static boolean[][] arr; // i번째 연산에서 7로 나눈 나머지가 j인 결과가 있는지

    // 더하기 연산
    static void add(int k, int n) {
        for (int i = 0; i < 7; i++) {
            if (!arr[k - 1][i]) continue;

            int result = (n + i) % 7;
            arr[k][result] = true;
        }
    }

    // 곱하기 연산
    static void multiple(int k, int n) {
        for (int i = 0; i < 7; i++) {
            if (!arr[k - 1][i]) continue;

            int result = (n * i) % 7;
            arr[k][result] = true;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/solvedac_Grand_Arena_2/F.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            arr = new boolean[N + 1][7];
            arr[0][1] = true;

            // 입력 받기
            for (int k = 1; k <= N; k++) {
                st = new StringTokenizer(br.readLine());

                // 연산 두번 받기
                for (int j = 0; j < 2; j++) {
                    String op = st.nextToken();
                    int n = Integer.parseInt(st.nextToken());

                    // 더하기 연산
                    if (op.equals("+")) add(k, n);
                    // 곱하기 연산
                    else multiple(k, n);
                }
            }

            if (arr[N][0]) sb.append("LUCKY\n");
            else sb.append("UNLUCKY\n");
        }
        System.out.println(sb);
    }
}
