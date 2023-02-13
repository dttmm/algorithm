package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 숫자 3개 중에서 최소값을 구하는 문제
 세수끼리 mod연산을 한 결과가 모두 같은 경우는
 숫자 3개가 모두 동일한 경우밖에 없음
 */
public class Main25487 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/25487.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int min = 100000;
            for (int i = 0; i < 3; i++) {
                int n = Integer.parseInt(st.nextToken());
                min = Math.min(min, n);
            }

            bw.write(min + "\n");
        }

        bw.flush();
        bw.close();
    }
}
