package programmers.lv1;

import java.util.*;

/**
 단순히 서로 다른 숫자들의 개수를 세면
 해당 숫자가 정답이 되는 문제
 단, 해당 숫자의 최대값은 N / 2이다

 근데 괜히 map으로 했네
 set으로 해도 되자나?!?!
 */

public class No_008_폰켓몬 {


    class Solution {

        Map<Integer, Boolean> map = new HashMap();

        public int solution(int[] nums) {
            int answer = 0;
            int N = nums.length;

            for (int i = 0; i < N; i++) {
                int n = nums[i];
                if (map.containsKey(n)) continue;

                map.put(n, true);
            }

            if (map.size() >= N / 2) answer = N / 2;
            else answer = map.size();

            return answer;
        }
    }
}
