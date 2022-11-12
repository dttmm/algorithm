package programmers.lv0;

public class No_024_숨어있는_숫자의_덧셈_2 {

    class Solution {
        public int solution(String my_string) {
            int answer = 0;

            String s = "";
            for (int i = 0; i < my_string.length(); i++) {
                char c = my_string.charAt(i);
                if (c >= '0' && c <= '9') s += c;
                else {
                    if (s.length() > 0) {
                        int num = Integer.parseInt(s);
                        answer += num;
                    }
                    s = "";
                }
            }

            // 마지막이 숫자로 끝난 경우도 고려
            if (s.length() > 0) {
                int num = Integer.parseInt(s);
                answer += num;
            }

            return answer;
        }
    }
}
