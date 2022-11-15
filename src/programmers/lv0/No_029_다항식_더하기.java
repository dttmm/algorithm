package programmers.lv0;

public class No_029_다항식_더하기 {

    class Solution {
        public String solution(String polynomial) {
            String answer = "";

            String[] sArr = polynomial.split(" ");
            int intSum = 0;
            int xSum = 0;


            for (int i = 0; i < sArr.length; i++) {
                if (i % 2 == 1) continue;

                String s = sArr[i];
                if (s.contains("x")) {
                    String subS = s.substring(0, s.length() - 1);
                    if (subS.length() == 0) xSum++;
                    else xSum += Integer.parseInt(subS);
                } else {
                    intSum += Integer.parseInt(s);
                }
            }

            if (xSum > 0) {
                if (xSum == 1) {
                    answer += "x";
                } else {
                    answer += xSum + "x";
                }

                if (intSum > 0) {
                    answer += " + " + intSum;
                }
            } else {
                answer += intSum;
            }

            return answer;
        }
    }
}
