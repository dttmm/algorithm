package programmers.lv0;

import java.math.*;

public class No_017_구슬을_나누는_경우의_수 {

    class Solution {

        public int solution(int balls, int share) {
            int answer = 0;

            BigInteger n = new BigInteger("1");

            for (int i = 1; i <= balls; i++) {
                n = n.multiply(new BigInteger(i + ""));
            }
            for (int i = 1; i <= share; i++) {
                n = n.divide(new BigInteger(i + ""));
            }
            for (int i = 1; i <= (balls - share); i++) {
                n = n.divide(new BigInteger(i + ""));
            }
            answer = n.intValue();

            return answer;
        }
    }
}
