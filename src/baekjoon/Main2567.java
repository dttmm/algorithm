package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2567 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2567.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[102][102];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            for (int x = X; x < X + 10; x++) {
                for (int y = Y; y < Y + 10; y++) {
                    arr[y][x] = 1;
                }
            }
        }

        int count = 0;
        for (int i = 1; i < 102; i++) {
            for (int j = 1; j < 102; j++) {
                if (arr[i][j] != arr[i - 1][j]) count++;
            }
        }
        for (int j = 1; j < 102; j++) {
            for (int i = 1; i < 102; i++) {
                if (arr[i][j] != arr[i][j - 1]) count++;
            }
        }
        System.out.println(count);
    }
}
