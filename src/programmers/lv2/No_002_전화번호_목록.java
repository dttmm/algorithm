package programmers.lv2;

import java.util.*;

/**
 먼저 번호를 정렬해준다
 숫자를 하나 뽑으면 ex 119
 해당 숫자를 앞에서부터 하나씩 잘라가며 1, 11, 119
 해당 숫자가 set에 있는지 확인한 후
 자신을 set에 add한다

 불필요한 연산을 줄이기 위해, 맨 앞자리의 숫자가 바뀔 때마다 map을 초기화 해주었다
 이렇게 하면, 만약 2로 시작하는 번호를 탐색할 때는
 set에 있던 1로 시작하는 숫자들은 없어지게 된다...?
 어라 잠만 어차피 hash로 찾는거라서 2로 시작하지 않는 다른 숫자가 있어도 크게 상관없네???
 크흠
 */
public class No_002_전화번호_목록 {

    class Solution {

        Set<String> set = new HashSet();

        public boolean solution(String[] phone_book) {
            boolean answer = true;
            char pre = '0';

            Arrays.sort(phone_book);
            for (int i = 0; i < phone_book.length; i++) {
                String s = phone_book[i];
                char cur = s.charAt(0);
                if (pre != cur) {
                    set = new HashSet();
                    set.add(s);
                    pre = cur;
                } else {
                    for (int j = 0; j < s.length(); j++) {
                        String ss = s.substring(0, j + 1);
                        if (set.contains(ss)) {
                            answer = false;
                            break;
                        }
                    }
                    set.add(s);
                }
            }

            return answer;
        }
    }
}
