package programmers.lv0;

import java.util.*;


public class No_013_문자열_밀기 {


    class Solution {

        LinkedList<Character> list;
        int count;

        public int solution(String A, String B) {
            int answer = -1;
            count = 0;
            list = new LinkedList();

            for (int i = 0; i < A.length(); i++) {
                char c = A.charAt(i);
                list.add(c);
            }

            for (int i = 0; i < list.size(); i++) {
                boolean flag = true; // 둘이 같은지 플래그

                // 둘이 비교
                for (int j = 0; j < list.size(); j++) {
                    char c1 = list.get(j);
                    char c2 = B.charAt(j);

                    if (c1 != c2) {
                        flag = false;
                        break;
                    }
                }

                // 둘이 같은 경우
                if (flag) {
                    answer = count;
                    break;
                }

                char c = list.pollLast();
                list.addFirst(c);
                count++;
            }

            return answer;
        }
    }
}
