package softeer.lv2;

import java.util.*;
import java.io.*;

/**
 간단한 bfs문제
 visited를 arr로 사용함
 방문하면 1 -> 0으로 바꿔줌

 bfs 돌릴 때 마다
 count세서
 센 개수 모아 놓음 counts 리스트에 넣어주고
 마지막에 리스트 정렬해서
 오름차순을 출력
 */
public class Main_004_장애물_인식_프로그램 {

    static int N;
    static int[][] arr;
    static List<Integer> counts;
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};

    static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < N) return true;
        return false;
    }

    static void solve(int i, int j) {
        int count = 1;
        Queue<Integer> queue_i = new LinkedList();
        Queue<Integer> queue_j = new LinkedList();
        queue_i.add(i);
        queue_j.add(j);
        arr[i][j] = 0;

        while (!queue_i.isEmpty()) {
            i = queue_i.poll();
            j = queue_j.poll();

            for (int dir = 0; dir < 4; dir++) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];

                if (!isIn(newI, newJ)) continue;
                if (arr[newI][newJ] == 0) continue;

                arr[newI][newJ] = 0;
                queue_i.add(newI);
                queue_j.add(newJ);
                count++;
            }
        }

        counts.add(count);
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        counts = new ArrayList();

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = s.charAt(j);
                arr[i][j] = c - '0';
            }
        }

        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 0) continue;

                solve(i, j);
                total++;
            }
        }

        Collections.sort(counts);
        StringBuilder sb = new StringBuilder();
        sb.append(total + "\n");
        for (int i : counts) {
            sb.append(i + "\n");
        }

        System.out.println(sb);
    }
}
