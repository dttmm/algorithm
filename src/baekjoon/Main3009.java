package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main3009 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/3009.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] arr = new int[2][3];
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[0][i] = Integer.parseInt(st.nextToken());
            arr[1][i] = Integer.parseInt(st.nextToken());
        }

        int answer_x = 0;
        int answer_y = 0;
        if (arr[0][0] == arr[0][1]) {
            answer_x = arr[0][2];
        } else if (arr[0][0] == arr[0][2]) {
            answer_x = arr[0][1];
        } else {
            answer_x = arr[0][0];
        }

        if (arr[1][0] == arr[1][1]) {
            answer_y = arr[1][2];
        } else if (arr[1][0] == arr[1][2]) {
            answer_y = arr[1][1];
        } else {
            answer_y = arr[1][0];
        }
        System.out.println(answer_x + " " + answer_y);
    }
}
