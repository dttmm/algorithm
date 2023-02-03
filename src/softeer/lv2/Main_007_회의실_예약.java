package softeer.lv2;

import java.util.*;
import java.io.*;

/**
 더러운 구현 문제
 회의실당 회의 시간을 배열로 가지고 있게함
 */
public class Main_007_회의실_예약 {

    static int N;
    static int M;
    static TreeMap<String, int[]> map;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new TreeMap();
        for (int i = 0; i < N; i++) {
            map.put(br.readLine(), new int[19]);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String key = st.nextToken();
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            // 시간 할당하기
            int[] arr = map.get(key);
            for (int j = start; j < end; j++) {
                arr[j] = 1;
            }
        }

        // map의 마지막 인덱스인지 세기 위함
        int index = 0;
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, int[]> entry : map.entrySet()) {
            sb.append("Room " + entry.getKey() + ":\n");
            int[] arr = entry.getValue();   // 회의실이 가지고 있는 회의 시간 정보

            StringBuilder sb_each = new StringBuilder();
            int count = 0;  // 빈 회의시간이 몇 개인지 카운트
            int start = 0;  // 빈 회의시간의 시작 시간
            int end = 0;    // 빈 회의시간의 종료 시간
            for (int i = 9; i <= 18; i++) {

                // 빈 시간이 있는 경우
                if (arr[i] == 0) {

                    // 마지막 18시간인 경우
                    if (i == 18) {
                        // 이전에 빈 회의시간 이었던 경우
                        if (start != 0) {
                            if (start == 9) {
                                sb_each.append("09");
                            } else {
                                sb_each.append(start + "");
                            }
                            sb_each.append("-" + end + "\n");
                        }
                        continue;
                    }

                    // 빈 회의실 시작 시간 설정
                    if (start == 0) {
                        start = i;
                        count++;
                    }

                    end = i + 1;
                }
                // 이미 사용중인 시간인 경우
                else {
                    // 이전에 빈 회의시간 이었던 경우
                    if (start != 0) {
                        if (start == 9) {
                            sb_each.append("09");
                        } else {
                            sb_each.append(start + "");
                        }
                        sb_each.append("-" + end + "\n");
                    }
                    start = 0;
                }
            }

            // 빈 회의시간이 아무것도 없는 경우
            if (count == 0) {
                sb.append("Not available\n");
            } else {
                sb.append(count + " available:\n");
                sb.append(sb_each);
            }

            // 마지막 출력에는 ----- 제거하기 위함
            if (index == N - 1) continue;
            sb.append("-----\n");
            index++;
        }
        System.out.println(sb);
    }
}
