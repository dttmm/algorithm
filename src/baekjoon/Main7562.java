package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main7562 {
    static int N;
    static int[][] arr;
    static int answerI;
    static int answerJ;
    static int[] di = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dj = {-2, -1, 1, 2, 2, 1, -1, -2};

    public static boolean isIn(int newI, int newJ) {
        if (newI >= 0 && newI < N && newJ >= 0 && newJ < N) return true;
        return false;
    }

    public static int solve(int i, int j) {
        int count = 0;
        Queue<Integer> queue = new LinkedList();
        queue.add(i);
        queue.add(j);
        arr[i][j] = 1;
        while (!queue.isEmpty()) {
            i = queue.poll();
            j = queue.poll();
            for (int dir = 0; dir < 8; dir++) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];
                if (isIn(newI, newJ) && arr[newI][newJ] == 0) {
                    count = arr[i][j] + 1;
                    if (newI == answerI && newJ == answerJ) return count;
                    arr[newI][newJ] = count;
                    queue.add(newI);
                    queue.add(newJ);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/7562.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            int startI = Integer.parseInt(st.nextToken());
            int startJ = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            answerI = Integer.parseInt(st.nextToken());
            answerJ = Integer.parseInt(st.nextToken());

            if (startI == answerI && startJ == answerJ) {
                System.out.println(0);
                continue;
            }

            int answer = solve(startI, startJ) - 1;
            System.out.println(answer);
        }
    }
}
