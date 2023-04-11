package programmers.lv1;

import java.util.*;

/**
 체육복이 없는 학생, 여분 체육복이 있는 학생 모두 하나의 배열(arr)로 관리
 체육복이 없는 학생의 경우 먼저
 왼쪽 학생(i-1)이 여분이 있는지 확인하고
 없으면 오른쪽 학생(i+1)이 여분이 있는지 확인함
 만약 오른쪽 학생의 체육복을 빌리면
 오른쪽 학생의 체육복 --해줌
 */
public class No_022_체육복 {

    class Solution {
        public int solution(int n, int[] lost, int[] reserve) {
            int answer = 0;
            int[] arr = new int[n + 1];   // 체육복 정보 담을 배열

            // 체육복 없으면 -1저장
            for (int i = 0; i < lost.length; i++) {
                int index = lost[i];
                arr[index]--;
            }

            // 여분 있으면 1저장
            for (int i = 0; i < reserve.length; i++) {
                int index = reserve[i];
                arr[index]++;
            }

            // 학생들 돌면서
            for (int i = 1; i <= n; i++) {
                int cloth = arr[i];

                // 체육복 이미 있는 애들은 패쓰
                if (cloth != -1) continue;

                // 왼쪽 애가 여분이 있는지 확인
                if (i - 1 >= 0 && arr[i - 1] == 1) {
                    arr[i]++;
                    continue;
                }

                // 오른쪽 애가 여분이 있는지 확인
                if (i + 1 <= n && arr[i + 1] == 1) {
                    arr[i + 1]--;
                    arr[i]++;
                }
            }

            // 체육복 있거나 여분 있는 애들 체크
            for (int i = 1; i <= n; i++) {
                if (arr[i] >= 0) answer++;
            }

            return answer;
        }
    }
}
