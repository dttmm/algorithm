package baekjoon.대회2023.solvedac_Grand_Arena_1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 설계 18분 구현 4분 디버깅 30분
 아이디어
 주어진 막대를 짧은 것부터 순회하며
 해당 막대로 만들 수 있는 배수 길이를 카운트 해줌
 예를 들어 길이1 막대로 만들 수 있는 배수 길이는 2, 3... 100000이고
 길이2 막대로 만들 수 있는 배수 길이는 4, 6... 100000인데
 이미 2는 1을 이용하여 만들었으로
 2의 배수들은 (1로 2를 만든 경우  + 2로 2를 만든 경우)(d[n]+1)로 카운트 해줌

 틀림
 입력 예제는 잘 돌아가는데
 예외를 발견함
 길이가 1인 막대 밖에 없을 때는
 4를 만들기 위해
 1*4, 1*2*2의 경우가 나올 수 있음
 근데 위의 방법으로는 1*4의 경우만 구해짐
 길이가 2인 막대가 없기 때문에 2를 곱하여 만들 수 있는 막대를 구하는 방법이 없음

 아이디어를 바꿈
 길이가 1인 막대부터 10만인 막대까지 순회하면서
 해당 막대의 길이에서
 배수를 해가며
 배수에 해당 막대 길이로 만들 수 있는 경우를 누적해감
 */
public class MainG {

    static int N;
    static int Q;
    static int[] d;             // i길이 막대를 만들기 위한 경우의 수
    static Set<Integer> set;    // 가지고 있는 막대 정보 저장

    // 만들 수 있는 막대 구하기
    static void solve() {
        for (int n = 1; n <= 100000; n++) {
            // 해당 길이의 막대를 가지고 있는 경우
            if (set.contains(n)) d[n]++;

            // 해당 길이의 막대를 만들 수 있는 경우의 수
            int count = d[n];

            // 해당 길이의 막대로 만들 수 있는 배수에 경우의 수 누적해줌
            int limit = 100000 / n;
            for (int x = 2; x <= limit; x++) {
                d[n * x] += count;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/solvedac_Grand_Arena_1/G.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        d = new int[100001];
        set = new HashSet();

        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            set.add(n);
        }

        // 만들 수 있는 막대 구하기
        solve();

        // 정답 출력
        Q = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int n = Integer.parseInt(st.nextToken());
            sb.append(d[n] + " ");
        }

        System.out.println(sb);
    }
}
