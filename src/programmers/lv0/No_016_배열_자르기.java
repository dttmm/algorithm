package programmers.lv0;

import java.util.*;

public class No_016_배열_자르기 {


    class Solution {
        public int[] solution(int[] numbers, int num1, int num2) {
            int[] answer = {};

            int[] arr = Arrays.copyOfRange(numbers, num1, num2 + 1);

            answer = arr;
            return answer;
        }
    }
}
