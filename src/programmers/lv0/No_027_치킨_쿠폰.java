package programmers.lv0;

public class No_027_치킨_쿠폰 {

    class Solution {
        public int solution(int chicken) {
            int answer = 0;

            int sum = 0;
            do {
                int mok = chicken / 10;
                int rest = chicken % 10;
                sum = mok + rest;

                answer += mok;

                chicken /= 10;
                chicken += rest;
            } while (sum >= 10);

            return answer;
        }
    }
}
