package programmers.lv0;

public class No_026_최빈값_구하기 {

    class Solution {

        int[] count;

        public int solution(int[] array) {
            int answer = 0;
            count = new int[1000];

            for (int i = 0; i < array.length; i++) {
                int index = array[i];
                count[index]++;
            }

            // 최빈값의 개수 찾기
            int max = 0;
            for (int i = 0; i < 1000; i++) {
                int c = count[i];
                max = Math.max(max, c);
            }

            boolean flag = false;   // 최빈값 여러개인지 확인 플래그
            for (int i = 0; i < 1000; i++) {
                int c = count[i];

                // 이미 최빈값이 나온 경우
                if (c == max) {
                    if (flag) {
                        answer = -1;
                        break;
                    }

                    max = c;
                    flag = true;
                    answer = i;
                }
            }

            return answer;
        }
    }
}
