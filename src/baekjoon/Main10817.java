package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main10817 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10817.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[3];
        for (int i = 0; i < 3; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int mid = 0;
        if (arr[0] > arr[1]) {
            if (arr[2] > arr[0]) mid = arr[0];
            else if (arr[2] < arr[1]) mid = arr[1];
            else mid = arr[2];
        } else {
            if (arr[2] < arr[0]) mid = arr[0];
            else if (arr[2] > arr[1]) mid = arr[1];
            else mid = arr[2];
        }
        System.out.println(mid);
    }
}
