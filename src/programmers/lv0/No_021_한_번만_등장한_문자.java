package programmers.lv0;

public class No_021_한_번만_등장한_문자 {

    class Solution {

        int[] count;

        public String solution(String s) {
            String answer = "";
            StringBuilder sb = new StringBuilder();

            count = new int[26];

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int index = c - 'a';
                count[index]++;
            }

            for (int i = 0; i < 26; i++) {
                if (count[i] == 1) sb.append((char) (i + 'a'));
            }

            answer = sb.toString();
            return answer;
        }
    }
}
