package programmers.lv0;

public class No_018_잘라서_배열로_저장하기 {

    class Solution {
        public String[] solution(String my_str, int n) {
            String[] answer = {};

            int size = my_str.length() / n;
            if (my_str.length() % n != 0) size++;

            answer = new String[size];

            String s = "";

            int count = 0;
            int index = 0;
            for (int i = 0; i < my_str.length(); i++) {
                char c = my_str.charAt(i);
                s += c;
                count++;

                if (count == n) {
                    answer[index++] = s;
                    count = 0;
                    s = "";
                }
            }
            if (count != 0) answer[index] = s;

            return answer;
        }
    }
}
