package programmers.lv0;

public class No_028_OX퀴즈 {

    class Solution {
        public String[] solution(String[] quiz) {
            int size = quiz.length;
            String[] answer = new String[size];

            for (int i = 0; i < size; i++) {
                String[] sArr = quiz[i].split(" ");

                boolean flag = true;    // 연산 플래스 true: 더하기
                int sum = 0;
                int result = Integer.parseInt(sArr[sArr.length - 1]);
                for (int j = 0; j < sArr.length - 2; j++) {
                    String s = sArr[j];
                    // 연산자인 경우
                    if (j % 2 == 1) {
                        if (s.equals("+")) {
                            flag = true;
                        } else {
                            flag = false;
                        }
                    } else {
                        int n = Integer.parseInt(s);
                        if (flag) sum += n;
                        else sum -= n;
                    }
                }

                if (sum == result) answer[i] = "O";
                else answer[i] = "X";
            }

            return answer;
        }
    }
}
