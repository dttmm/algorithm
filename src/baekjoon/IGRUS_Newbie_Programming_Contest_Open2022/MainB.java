package baekjoon.IGRUS_Newbie_Programming_Contest_Open2022;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainB {

    static int N;

    public static int solve(int total) {
        int min = total;
        if (N >= 5) {
            min = total - 500;
        }

        if (N >= 10) {
            min = (int) Math.min(min, total * 0.9);
        }

        if (N >= 15) {
            min = Math.min(min, total - 2000);
        }

        if (N >= 20) {
            min = (int) Math.min(min, total * 0.75);
        }

        return min;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/IGRUS_Newbie_Programming_Contest_Open2022/B.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int total = Integer.parseInt(br.readLine());

        int answer = solve(total);
        if (answer < 0) answer = 0;
        System.out.println(answer);
    }
}
