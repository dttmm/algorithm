package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1292 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1292.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] arr = new int[1001];

        int index = 1;
        int i = 1;
        while (index <= 1000) {
            for (int j = 0; j < i; j++) {
                if (index > 1000) break;
                arr[index++] = i;
            }
            i++;
        }


        int answer = 0;
        for (int j = start; j <= end; j++) {
            answer += arr[j];
        }
        System.out.println(answer);
    }
}
