package baekjoon.대회2023.제2회_초콜릿컵;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 설계 5분 구현 11분
 bfs를 이용하여 연결되어 있는 초콜릿의 개수를 구하고
 각 개수를 카운트하여
 입력으로 받은 개수와 맞는지 확인함
 */
public class MainB {

    static int[][] arr;
    static int[] counts;    // 연결된 칸의 개수 카운트
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, 1, -1, 0};

    // 범위 체크
    static boolean isIn(int i, int j) {
        if (i >= 0 && i < 3 && j >= 0 && j < 3) return true;
        return false;
    }

    // bfs
    static int solve(int start_i, int start_j) {
        Queue<Integer> queue_i = new LinkedList();
        Queue<Integer> queue_j = new LinkedList();
        queue_i.add(start_i);
        queue_j.add(start_j);
        arr[start_i][start_j] = 0;
        int count = 1;

        while (!queue_i.isEmpty()) {
            int i = queue_i.poll();
            int j = queue_j.poll();
            for (int dir = 0; dir < 4; dir++) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];

                // 범위 벗어나면 패쓰
                if (!isIn(newI, newJ)) continue;
                // 초콜릿이 아니거나 이미 방문했으면 패쓰
                if (arr[newI][newJ] != 1) continue;

                arr[newI][newJ] = 0;
                queue_i.add(newI);
                queue_j.add(newJ);
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/제2회_초콜릿컵/B.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            arr = new int[3][3];
            counts = new int[9];

            // 입력 받기
            for (int i = 0; i < 3; i++) {
                String s = br.readLine();
                for (int j = 0; j < 3; j++) {
                    char c = s.charAt(j);

                    if (c == 'O') arr[i][j] = 1;
                }
            }

            // 연결된 칸의 개수 구하기
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (arr[i][j] != 1) continue;

                    int n = solve(i, j);
                    counts[n]++;
                }
            }

            // 입력 받기
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int[] counts2 = new int[9];
            for (int i = 0; i < M; i++) {
                int n = Integer.parseInt(st.nextToken());
                counts2[n]++;
            }

            // 입력과 비교하기
            boolean flag = true;
            for (int i = 1; i <= 8; i++) {
                if (counts[i] == counts2[i]) continue;

                flag = false;
                break;
            }

            if (flag) sb.append("1\n");
            else sb.append("0\n");
        }
        System.out.println(sb);
    }
}
