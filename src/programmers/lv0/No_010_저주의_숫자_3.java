package programmers.lv0;

public class No_010_저주의_숫자_3 {

    class Solution {

        int[] arr;

        public int solution(int n) {
            int answer = 0;
            arr = new int[101];

            // 배열에 새로 담는다
            // 3의 배수가 아닌 애들
            // 3이 들어가 있지 않은 애들
            int index = 1;
            int i = 1;
            while (index <= 100) {
                if (i % 3 != 0) {
                    String s = String.valueOf(i);

                    boolean flag = true; // 실패 플래그
                    for (int j = 0; j < s.length(); j++) {
                        char c = s.charAt(j);
                        if (c == '3') flag = false;
                    }

                    if (flag) arr[index++] = i;
                }
                i++;
            }

            answer = arr[n];
            return answer;
        }
    }
}
