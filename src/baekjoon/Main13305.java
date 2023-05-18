package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 5분 구현 3분
 우선 문제 형태를 보고 dp인줄 알았음
 근데 현재 있는 위치가 다음 위치들 보다 비용이 싼 경우
 현재 위치에서 미리 주유를 하면 됨
 순차적으로 가면서 제일 싼 가격을 기록해두고
 제일 싼 가격과 현재 위치 가격 중 더 싼 가격으로 주유를 하면 됨

 예제의 경우
 각 위치의 비용이
 5 2 4 1 인데
 각 위치에서 제일 싼 비용을 구하면
 5 2 2 이렇게 됨
 세번째 위치에서는 2가 제일 싼 비용인데
 이말은 이전에 2만큼의 비용이 드는 위치에서 미리 주유를 했다는 말을 의미함

 추가로 마지막에서 더이상 이동 안하니까
 마지막 비용은 고려할 필요 없음
 */
public class Main13305 {

    static int N;
    static int[] d;     // 거리
    static int[] price; // 해당 위치에서 제일 싼 가격

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/13305.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        d = new int[N - 1];
        price = new int[N - 1];

        // 거리 정보 저장
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            int n = Integer.parseInt(st.nextToken());
            d[i] = n;
        }

        // 각 위치에서 제일 싼 가격 저장
        st = new StringTokenizer(br.readLine());
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N - 1; i++) {
            int n = Integer.parseInt(st.nextToken());
            min = Math.min(min, n);

            price[i] = min;
        }

        // 각 위치에서 제일 싼 가격으로 이동할 거리만큼 주유
        long total = 0;
        for (int i = 0; i < N - 1; i++) {
            total += (long) price[i] * d[i];
        }

        System.out.println(total);
    }
}
