package programmers.lv0;

import java.util.*;

public class No_015_문자열_정렬하기_1 {

    class Solution {

        List<Integer> list = new ArrayList();

        public int[] solution(String my_string) {
            int[] answer = {};

            for (int i = 0; i < my_string.length(); i++) {
                char c = my_string.charAt(i);
                if (c >= '0' && c <= '9') {
                    list.add(c - '0');
                }
            }

            Collections.sort(list);

            int size = list.size();
            answer = new int[size];
            for (int i = 0; i < size; i++) {
                answer[i] = list.get(i);
            }
            return answer;
        }
    }
}
