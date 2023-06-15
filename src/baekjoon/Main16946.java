package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 설계 9분 구현 15분
 각 벽에서 갈 수 있는 빈 칸들의 개수를 알기 위해서는
 각 빈공간 마다 몇개의 묶음으로 이루어져 있는지 관리해주고
 벽에서는 해당 묶음의 개수만큼 수를 세어주면 됨

 서로 연결되어 있는 빈공간을 하나의 그룹으로 만들어주고
 각 그룹에 번호를 할당하고
 각 칸이 어느 그룹에 속해있는지 저장함 (group[][])
 그리고 각 그룹이 몇 개의 빈칸으로 연결되어 있는지 저장함 (count[])

 벽 주위에 그룹이 있다면
 해당 그룹이 가지고 있는 빈칸의 개수만큼 더해주고
 같은 그룹을 계산하는 중복을 없애기 위해 set활용함
 */
public class Main16946 {

    static int N;
    static int M;
    static int[][] arr;
    static int[][] group;   // 각 칸이 어느 그룹에 속해있는지
    static int[] count;     // 각 그룹이 몇 개의 빈칸으로 연결되어 있는지
    static int[][] answer;  // 정답에 사용할 배열
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, 1, -1, 0};
    static boolean[][] visited;

    // 범위 체크
    static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < M) return true;
        return false;
    }

    // 그룹 정보 저장
    static void setGroup(int start_i, int start_j, int groupNum) {
        Queue<Integer> queue_i = new LinkedList();
        Queue<Integer> queue_j = new LinkedList();

        int total = 1;
        queue_i.add(start_i);
        queue_j.add(start_j);
        visited[start_i][start_j] = true;
        group[start_i][start_j] = groupNum;

        while (!queue_i.isEmpty()) {
            int i = queue_i.poll();
            int j = queue_j.poll();

            for (int dir = 0; dir < 4; dir++) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];

                // 범위 벗어나면 패쓰
                if (!isIn(newI, newJ)) continue;
                // 벽이면 패쓰
                if (arr[newI][newJ] == 1) continue;
                // 이미 방문했으면 패쓰
                if (visited[newI][newJ]) continue;

                queue_i.add(newI);
                queue_j.add(newJ);
                visited[newI][newJ] = true;
                group[newI][newJ] = groupNum;
                total++;
            }
        }
        // 그룹이 가지고 있는 빈칸 개수 업데이트
        count[groupNum] = total;
    }

    // 이동할 수 있는 빈칸 개수 구하기
    static void setCount(int i, int j) {
        int total = 1;
        Set<Integer> set = new HashSet();

        for (int dir = 0; dir < 4; dir++) {
            int newI = i + di[dir];
            int newJ = j + dj[dir];

            // 범위 벗어나면 패쓰
            if (!isIn(newI, newJ)) continue;
            // 벽이면 패쓰
            if (arr[newI][newJ] == 1) continue;

            int groupNum = group[newI][newJ];
            // 이미 방문한 그룹이면 패쓰
            if (set.contains(groupNum)) continue;

            set.add(groupNum);
            int num = count[groupNum];
            total += num;
        }
        // 해당 벽에서 갈 수 있는 빈칸 개수 업데이트
        answer[i][j] = total % 10;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/16946.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        group = new int[N][M];
        count = new int[5000001];   // group 번호는 최대 (1000*1000)/2까지 부여 가능 <- 입력이 0101010.. 이렇게 주어질 경우
        answer = new int[N][M];
        visited = new boolean[N][M];

        // 입력 받기
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);

                if (c == '0') continue;
                arr[i][j] = 1;
            }
        }

        // 그룹 번호 설정하기
        int groupNum = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 1) continue;
                if (visited[i][j]) continue;

                setGroup(i, j, groupNum);
                groupNum++;
            }
        }

        // 벽에서 갈수 있는 빈칸 구하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) continue;
                setCount(i, j);
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(answer[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
