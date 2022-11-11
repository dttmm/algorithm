package programmers.lv0;

public class No_011_순서쌍의_개수 {

    class Solution {
        public int solution(int n) {
            int answer = 0;

            for (int i = 1; i < Math.sqrt(n); i++) {
                if (n % i == 0) answer++;
            }

            answer *= 2;

            // 제곱 수 인지 확인
            int sqrt = (int) Math.sqrt(n);
            if (sqrt * sqrt == n) answer++;

            return answer;
        }
    }
}
