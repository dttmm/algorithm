package programmers.lv0;

import java.util.*;

public class No_025_소인수분해 {

    class Solution {

        boolean[] sosu; // false: 소수
        Set<Integer> set;

        public int[] solution(int n) {
            int[] answer = {};
            set = new TreeSet();

            // 소수 구하고
            sosu = new boolean[10001];
            for (int i = 2; i <= 10000; i++) {
                if (sosu[i]) continue;

                for (int j = i * 2; j <= 10000; j += i) {
                    sosu[j] = true;
                }
            }

            // 소수로 나누기
            while (n != 1) {
                for (int i = 2; i <= 10000; i++) {
                    if (sosu[i]) continue;

                    while (n % i == 0) {
                        n /= i;
                        set.add(i);
                    }
                }
            }

            answer = new int[set.size()];
            int index = 0;
            for (int i : set) {
                answer[index++] = i;
            }

            return answer;
        }
    }
}
