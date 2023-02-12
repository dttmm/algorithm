package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 3가지의 수를 각각 모듈러 연산을 했는데
 결과가 같게 되는 경우가 뭐가있지???
 한참 고민했는데
 문제 예시 보니까
 그냥 다 같은 수 일때만 가능하다는 것을 깨달음
 그래서 그냥 세개 중에서 최소값 고르면
 그게 정답인 것 같긴하데 찝찝하긴 한데
 아무리봐도 그게 정답이어서
 3개의 숫자중에서 최소값 고름
 근데 진짜 정답이네
 */
public class Main25494 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/25494.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int min = 60;

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
