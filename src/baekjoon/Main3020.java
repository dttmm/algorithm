package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 일단 모든 높이에 대해서 얼마나 충돌하는지를 구해야 최적화된 값을 구할 수 있었기에
 i 구간을 지날 때 석순과 종유석을 각각 몇 번 충돌하는지 나누어 구한다음에
 충동 횟수를 더해주었음

 최대 높이에서부터 높이를 --해가며
 이전 높이에서 충돌한 횟수와 현재 높이에서 새로 충돌할 횟수를 더해가며
 각 구간에서의 충돌 횟수를 구해주었음

 종유석의 경우 거꾸로 되어있기 때문에
 인덱스(H - i + 1)를 생각해내는 것이 까다로웠음

 다른 사람들도 이렇게 까다롭게 풀었나 궁금해서
 다른 풀이를 봤는데 다들 자기만의 이해방식으로 풀었더라
 자기자신이 이해하는 것이 중요한듯
 */
public class Main3020 {

    static int N;
    static int H;
    static Map<Integer, Integer> map1;  // 석순의 높이 정보 저장
    static Map<Integer, Integer> map2;  // 종유석의 높이 정보 저장
    static int[] d1;    // i 구간을 지날 때 충돌하는 석순의 개수
    static int[] d2;    // i 구간을 지날 때 충돌하는 종유석의 개수
    static int[] d;     // i 구간을 지날 때 충돌하는 석순, 종유석 개수의 합

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/3020.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map1 = new HashMap();
        map2 = new HashMap();
        d1 = new int[H + 1];
        d2 = new int[H + 1];
        d = new int[H + 1];

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());

            if (i % 2 == 0) {
                if (map1.get(n) != null) map1.put(n, map1.get(n) + 1);
                else map1.put(n, 1);
            } else {
                if (map2.get(n) != null) map2.put(n, map2.get(n) + 1);
                else map2.put(n, 1);
            }
        }

        // i 구간을 지날 때 충돌하는 석순의 개수 구해줌
        for (int i = H; i > 0; i--) {
            if (i == H) {
                if (map1.get(i) == null) continue;
                d1[i] = map1.get(i);
                continue;
            }

            d1[i] = d1[i + 1];

            if (map1.get(i) == null) continue;
            else d1[i] += map1.get(i);
        }

        // i 구간을 지날 때 충돌하는 종유석의 개수 구해줌
        for (int i = H; i > 0; i--) {
            if (i == H) {
                if (map2.get(i) == null) continue;
                d2[1] = map2.get(i);
                continue;
            }

            d2[H - i + 1] = d2[H - i];

            if (map2.get(i) == null) continue;
            else d2[H - i + 1] += map2.get(i);
        }

        // i 구간을 지날 때 충돌하는 석순, 종유석 개수의 합 구해줌
        for (int i = 1; i <= H; i++) {
            d[i] = d1[i] + d2[i];
        }

        // 정렬
        Arrays.sort(d);

        // 최소값 몇 개 있는지 찾기
        int count = 1;
        for (int i = 2; i <= H; i++) {
            if (d[i] != d[1]) break;

            count++;
        }

        System.out.println(d[1] + " " + count);
    }
}
