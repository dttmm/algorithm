package programmers.lv1;

/**
 진법 변환과 문자열 뒤집기하는 방법을 습득하기 좋은 문제
 문자열 뒤집기는 StringBuilder이용함
 */
public class No_014_3진법_뒤집기 {

    class Solution {
        public int solution(int n) {
            int answer = 0;

            String s = Integer.toString(n, 3);
            StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb = sb.reverse();
            answer = Integer.valueOf(sb.toString(), 3);

            return answer;
        }
    }
}
