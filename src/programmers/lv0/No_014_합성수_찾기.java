package programmers.lv0;

import java.util.LinkedList;


public class No_014_합성수_찾기 {


    class Solution {

        boolean[] arr;   // true이면 합성수

        public int solution(int n) {
            int answer = 0;
            arr = new boolean[n + 1];

            for (int i = 2; i <= n; i++) {
                if (!arr[i]) {
                    for (int j = i * 2; j <= n; j += i) {
                        arr[j] = true;
                    }
                }
            }

            for (int i = 1; i <= n; i++) {
                System.out.println(i + " " + arr[i]);
            }

            for (int i = 1; i <= n; i++) {
                if (arr[i]) answer++;
            }

            return answer;
        }
    }
}
