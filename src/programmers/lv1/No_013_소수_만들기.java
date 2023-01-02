package programmers.lv1;

import java.util.*;

/**
 단순 조합 + 소수 문제
 소수 판별은 에라토스테네스의 체를 이용함

 먼저 소수 판별을 다 하고나서
 조합으로 뽑은 수의 합이 소수인지 아닌지 판별함
 */
public class No_013_소수_만들기 {


    class Solution {

        // 소수여부 담겨있는 배열
        boolean[] isSosu = new boolean[3000];

        int N;
        int R = 3;
        int[] arr;
        int tr[] = new int[3];
        int count = 0;

        // 3개 고르는 함수
        public void c(int k, int start) {
            if (k == R) {
                int n = tr[0] + tr[1] + tr[2];
                if (isSosu[n]) count++;
            } else {
                for (int i = start; i < N; i++) {
                    tr[k] = arr[i];
                    c(k + 1, i + 1);
                }
            }
        }

        public int solution(int[] nums) {
            int answer = 0;
            arr = nums;
            N = nums.length;

            Arrays.fill(isSosu, true);

            // 소수여부 판단하기
            for (int i = 2; i < 3000; i++) {
                if (!isSosu[i]) continue;
                for (int j = i * 2; j < 3000; j += i) {
                    isSosu[j] = false;
                }
            }

            // 3개 고르기
            c(0, 0);

            answer = count;
            return answer;
        }
    }
}
