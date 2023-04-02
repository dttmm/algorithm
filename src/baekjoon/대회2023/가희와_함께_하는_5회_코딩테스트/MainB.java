package baekjoon.대회2023.가희와_함께_하는_5회_코딩테스트;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 시분초를 초로 변경하고
 시작 시간에서 +40초 까지
 count배열에 표시함

 count배열에서 false의 수가 정답
 */
public class MainB {

    static int C;
    static int H;
    static boolean[] count;

    // 시간을 초 단위로 변환
    static int convertSec(String[] time) {
        return Integer.parseInt(time[0]) * 60 * 60 + Integer.parseInt(time[1]) * 60 + Integer.parseInt(time[2]);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/가희와_함께_하는_5회_코딩테스트/B.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        count = new boolean[86400];

        // 상행 열차
        for (int i = 0; i < C; i++) {
            String[] time = br.readLine().split(":");
            int sec = convertSec(time);

            for (int j = 0; j < 40; j++) {
                count[sec + j] = true;
            }
        }

        // 하행 열차
        for (int i = 0; i < H; i++) {
            String[] time = br.readLine().split(":");
            int sec = convertSec(time);

            for (int j = 0; j < 40; j++) {
                count[sec + j] = true;
            }
        }

        // 차단기가 올라간 시간 구하기
        int answer = 0;
        for (int i = 0; i < 86400; i++) {
            if (count[i]) continue;
            answer++;
        }

        System.out.println(answer);
    }
}
