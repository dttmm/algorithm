package programmers.lv1;

import java.util.*;

/**
 재귀를 이용하여 풀었음

 문자열에서 "aya", "ye", "woo", "ma"로 시작하는 경우
 해당 문자열을 잘라내고 재귀를 돌림
 단, 같은 문자열이 연속되면 안되므로
 type을 이용해서 중복을 방지함

 예를 들어, 어떤 문자열 s가 "aya"로 시작했다면
 s에서 "aya"를 잘라내고
 잘라낸 s는 "aya"로 시작하는지 검사할 필요가 없음을 type으로 분기처리함

 매번 문자열 검사를 하는 것이 찜찜했지만
 문자열 길이가 최대 30으로 제한되어있어
 상관 없다고 판단
 */
public class No_015_옹알이2 {

    class Solution {

        String[] arr = {"aya", "ye", "woo", "ma"};
        int answer = 0;

        // type: 같은 문자열 검사 중복 방지용
        public void solve(String s, int type) {
            if (s.length() == 0) {
                answer++;
                return;
            }

            if (type != 0 && s.startsWith(arr[0])) solve(s.substring(3), 0);
            else if (type != 1 && s.startsWith(arr[1])) solve(s.substring(2), 1);
            else if (type != 2 && s.startsWith(arr[2])) solve(s.substring(3), 2);
            else if (type != 3 && s.startsWith(arr[3])) solve(s.substring(2), 3);
        }

        public int solution(String[] babbling) {

            for (int i = 0; i < babbling.length; i++) {
                String s = babbling[i];
                solve(s, -1);
            }
            return answer;
        }
    }
}
