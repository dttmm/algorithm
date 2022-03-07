package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1929 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1929.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] arr = new int[end + 1];
        for (int i = start; i < end + 1; i++) {
            arr[i] = i;
        }
        for (int i = 2; i < end + 1; i++) {
            for (int j = 2 * i; j < end + 1; j += i) {
                arr[j] = 0;
            }
        }
        for (int i = 2; i < end + 1; i++) {
            if (arr[i] != 0) {
                System.out.println(i);
            }
        }
    }
}
