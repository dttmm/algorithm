package baekjoon.대회2023.브실컵;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 설계 9분 구현 12분
 해시
 */
public class MainJ {

    static int N;
    static int K;
    static Map<Integer, Set<Integer>> map;
    static List<Integer> list_i;
    static List<Integer> list_j;
    static int[] di = {-2, 0, 0, 2};
    static int[] dj = {0, 2, -2, 0};

    static boolean isIn(int i, int j) {
        if (i > 0 && i <= N && j > 0 && j <= N) return true;
        return false;
    }

    static int solve() {
        int total = 0;

        for (int x = 0; x < list_i.size(); x++) {
            int i = list_i.get(x);
            int j = list_j.get(x);
            for (int dir = 0; dir < 4; dir++) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];

                if (!isIn(newI, newJ)) continue;

                if (map.get(newI) == null) {
                    map.put(newI, new HashSet());
                } else {
                    if (map.get(newI).contains(newJ)) continue;
                }
                map.get(newI).add(newJ);
                total++;
            }
        }
        return total;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/브실컵/J.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new HashMap();
        list_i = new ArrayList();
        list_j = new ArrayList();

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            if (map.get(i) == null) map.put(i, new HashSet());

            map.get(i).add(j);
            list_i.add(i);
            list_j.add(j);
        }

        int result = solve();

        System.out.println(result);
    }
}
