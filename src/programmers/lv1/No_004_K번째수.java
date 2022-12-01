package programmers.lv1;

import java.util.*;

/**
 * Arrays.copyOfRange 배웠다
 */

public class No_004_K번째수 {

    class Solution {
        public int[] solution(int[] array, int[][] commands) {
            int[] answer = new int[commands.length];

            for (int i = 0; i < commands.length; i++) {
                int[] arr = commands[i];

                int start = arr[0];
                int end = arr[1];
                int k = arr[2];

                int[] subArr = Arrays.copyOfRange(array, start - 1, end);

                Arrays.sort(subArr);
                answer[i] = subArr[k - 1];
            }

            return answer;
        }
    }
}
