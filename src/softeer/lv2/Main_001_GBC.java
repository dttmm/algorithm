package softeer.lv2;

import java.util.*;
import java.io.*;

/**
 간단한 배열 문제
 먼저 각 층마다의 제한 속도를 배열에 저장하고
 검사한 속도를 배열에 있는 속도와 비교하여 최대값을 뽑아냄
 N과 M의 최대값이 겨우 100이므로 완탐으로는 문제 없음
 */
public class Main_001_GBC {

    final static int limit = 100;
    static int N;
    static int M;
    static int[] arr;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[100];
        int answer = 0;

        // 제한 속도 입력 받음
        int cur = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());

            while (height > 0) {
                arr[cur] = speed;
                height--;
                cur++;
            }
        }

        // 검사한 속도와 제한 속도 비교
        cur = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());

            while (height > 0) {
                if (speed > arr[cur]) answer = Math.max(answer, speed - arr[cur]);
                height--;
                cur++;
            }
        }

        System.out.println(answer);
    }
}
