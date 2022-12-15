package programmers.lv1;

import java.util.HashMap;
import java.util.Map;

/**
 x부터 시작하여 문자 끝까지 돌면서
 문자를 분리해야할 경우
 1. 같은 숫자의 합과 다른 숫자의 합이 같아질 경우
 2. x부터 시작하여 끝까지 탐색한 경우
 이 경우에는 분리를 시켜주기 위해
 x_index값을 마지막 탐색 인덱스i+1 을 해주고
 answer++을 해주어 분리처리를 한다
 */

public class No_009_문자열_나누기 {

    class Solution {
        public int solution(String s) {
            int answer = 0;
            char[] cArr = s.toCharArray();
            int x_index = 0;

            while (x_index < cArr.length) {
                char x = cArr[x_index];

                int i = x_index;
                int same = 0;
                int notSame = 0;
                while (i < cArr.length) {
                    char c = cArr[i];

                    if (x == c) same++;
                    else notSame++;

                    if (same == notSame) break;

                    i++;
                }

                answer++;
                x_index = i + 1;
            }

            return answer;
        }
    }
}
