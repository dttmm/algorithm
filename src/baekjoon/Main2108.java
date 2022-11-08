package baekjoon;

import java.io.*;
import java.util.Arrays;

public class Main2108 {

    static int N;
    static int[] count;    // 3.최빈값 구하기 위해
    static int[] arr;      // 2.중앙값 구하기 위해


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2108.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        count = new int[8001];
        arr = new int[N];

        int sum = 0;
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            int n4000 = n + 4000;

            count[n4000]++;
            arr[i] = n;

            sum += n;
        }

        Arrays.sort(arr);

        int max = 0;
        for (int i = 0; i < 8001; i++) {
            if (count[i] > max) {
                max = count[i];
            }
        }

        int max_num = 0;
        boolean flag = false;   // 두번째로 작은 최빈값을 찾기 위한 플래그
        for (int i = 0; i < 8001; i++) {
            if (count[i] == max) {
                max_num = i - 4000;
                if (flag) { // 최빈값 또 찾았으면(두번째로 작은 수) 이제 탈출
                    break;
                } else {
                    flag = true;
                }
            }
        }

        bw.write(Math.round((double) sum / N) + "\n");
        bw.write(arr[N / 2] + "\n");
        bw.write(max_num + "\n");
        bw.write(arr[N - 1] - arr[0] + "");

        bw.flush();
        bw.close();
    }
}
