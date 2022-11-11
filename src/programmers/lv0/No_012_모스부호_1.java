package programmers.lv0;

import java.util.*;

public class No_012_모스부호_1 {


    class Solution {

        String[] arr = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

        Map<String, Character> map;
        StringBuilder sb = new StringBuilder();

        public String solution(String letter) {
            String answer = "";
            map = new HashMap();

            // map에 모스부호 담기
            for (int i = 0; i < arr.length; i++) {
                map.put(arr[i], (char) (i + 'a'));
            }

            StringTokenizer st = new StringTokenizer(letter);
            while (st.hasMoreTokens()) {
                char c = map.get(st.nextToken());
                sb.append(c);
            }

            answer = sb.toString();
            return answer;
        }
    }
}
