package programmers.lv1;

public class No_002_숫자_짝꿍 {

    class Solution {

        int[] count = new int[10];          // X가 가지고 있는 숫자 담을 배열
        int[] answer_count = new int[10];   // X와 Y가 가지고 있는 숫자 담을 배열

        public String solution(String X, String Y) {
            String answer = "";
            StringBuilder sb = new StringBuilder();

            // X의 숫자 카운트
            for (int i = 0; i < X.length(); i++) {
                char c = X.charAt(i);
                int n = c - '0';
                count[n]++;
            }

            // Y가 X가 가지고 있는 숫자 가지고 있는지 카운트
            for (int i = 0; i < Y.length(); i++) {
                char c = Y.charAt(i);
                int n = c - '0';
                if (count[n] > 0) {
                    count[n]--;
                    answer_count[n]++;
                }
            }

            // 카운트 배열에서 숫자 빼냄
            for (int i = 9; i >= 0; i--) {
                while (answer_count[i] > 0) {
                    sb.append(i);
                    answer_count[i]--;
                }
            }

            answer = sb.toString();

            // 예외처리
            if (answer.length() == 0) {
                answer = "-1";
            } else if (answer.charAt(0) == '0') answer = "0";

            return answer;
        }
    }
}
