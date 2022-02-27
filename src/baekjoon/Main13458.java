package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main13458 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/13458.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long count = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] > 0) {
                arr[i] -= B;
                count++;
                if (arr[i] > 0) {
                    if (arr[i] % C == 0) {
                        count += (arr[i] / C);
                    } else {
                        count += (arr[i] / C) + 1;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
