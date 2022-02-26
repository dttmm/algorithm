package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution4615 {
    static int N;

    public static boolean isIn(int newI, int newJ) {
        if (newI >= 0 && newI < N && newJ >= 0 && newJ < N) return true;
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/swea/4615.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            char[][] arr = new char[N][N];
            arr[N / 2 - 1][N / 2 - 1] = 'W';
            arr[N / 2 - 1][N / 2] = 'B';
            arr[N / 2][N / 2 - 1] = 'B';
            arr[N / 2][N / 2] = 'W';

            int[] di = {-1, -1, 0, 1, 1, 1, 0, -1};
            int[] dj = {0, 1, 1, 1, 0, -1, -1, -1};

            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());
                int j = Integer.parseInt(st.nextToken()) - 1;
                int i = Integer.parseInt(st.nextToken()) - 1;
                int stone = Integer.parseInt(st.nextToken());
                char my_stone = stone == 1 ? 'B' : 'W';
                char your_stone = stone == 1 ? 'W' : 'B';

                arr[i][j] = my_stone;
                for (int dir = 0; dir < 8; dir++) {
                    int newI = i + di[dir];
                    int newJ = j + dj[dir];
                    int count = 0;
                    while (isIn(newI, newJ) && arr[newI][newJ] == your_stone) {
                        newI = newI + di[dir];
                        newJ = newJ + dj[dir];
                        count++;
                    }
                    if (isIn(newI, newJ) && arr[newI][newJ] == my_stone) {
                        newI = i + di[dir];
                        newJ = j + dj[dir];
                        for (int k = 0; k < count; k++) {
                            arr[newI][newJ] = my_stone;
                            newI = newI + di[dir];
                            newJ = newJ + dj[dir];
                        }
                    }
                }

            }
            int B_count = 0;
            int W_count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] == 'B') B_count++;
                    if (arr[i][j] == 'W') W_count++;
                }
            }
            System.out.println("#" + test_case + " " + B_count + " " + W_count);
        }
    }
}