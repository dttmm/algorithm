package baekjoon;

import java.io.*;

/**
 비트마스킹을 이용하여 문제를 풀려고 했다
 비트마스킹을 이용하기 위해서는
 n이 2의 몇 승인지 알아야 되는데
 2의 몇 승인지 구한다음 비트마스킹까지 하는 것은 너무 비효율적이라 판단
 그냥 단순하게 2로 나누면서 1이 나오는 경우를 구함
 */
public class Main3460 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/3460.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());

            int index = 0;
            while (n != 0) {
                if (n % 2 == 1) bw.write(index + " ");

                n /= 2;
                index++;
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}
