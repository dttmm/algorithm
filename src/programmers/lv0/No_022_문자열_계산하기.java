package programmers.lv0;

public class No_022_문자열_계산하기 {

    class Solution {
        public int solution(String my_string) {
            int answer = 0;

            String[] s = my_string.split(" ");

            answer = Integer.parseInt(s[0]);
            for (int i = 0; i < s.length; i++) {
                if (i % 2 == 0) continue;  // 숫자인 경우

                if (s[i].equals("+")) {
                    answer += Integer.parseInt(s[i + 1]);
                } else {
                    answer -= Integer.parseInt(s[i + 1]);
                }
            }

            return answer;
        }
    }
}
