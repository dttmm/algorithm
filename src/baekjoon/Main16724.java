package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 서로 연결되어 있는 것의 개수를 찾으면 되므로
 방향 설정만 잘 해주면 되는 bfs문제
 */
public class Main16724 {

    static int N;
    static int M;
    static char[][] arr;
    static int[][] visited; // 어느 그룹에 속해있는지 확인하기 위해 int 사용
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, 1, -1, 0};
    static Map<Character, Integer> map;
    static Queue<Integer> queue_i;
    static Queue<Integer> queue_j;

    // bfs
    static boolean solve(int i, int j, int count) {
        queue_i = new LinkedList();
        queue_j = new LinkedList();
        queue_i.add(i);
        queue_j.add(j);
        visited[i][j] = count;

        while (!queue_i.isEmpty()) {
            i = queue_i.poll();
            j = queue_j.poll();

            int dir = map.get(arr[i][j]);
            int newI = i + di[dir];
            int newJ = j + dj[dir];

            // 방문한 적이 있는 경우
            if (visited[newI][newJ] > 0) {
                // 나와 다른 그룹이라면 -> 새로운 사이클 아님
                if (visited[newI][newJ] != count) return false;
                // 나와 같은 그룹이라면 -> 새로운 사이클임
                else return true;
            }
            queue_i.add(newI);
            queue_j.add(newJ);
            visited[newI][newJ] = count;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/16724.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        visited = new int[N][M];

        map = new HashMap();
        map.put('U', 0);
        map.put('R', 1);
        map.put('L', 2);
        map.put('D', 3);

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                arr[i][j] = c;
            }
        }

        int count = 1;  // 그룹 번호
        int answer = 0; // 독립적인 사이클 개수
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] > 0) continue;
                boolean result = solve(i, j, count);
                count++;
                if (result) answer++;
            }
        }

        System.out.println(answer);
    }
}
