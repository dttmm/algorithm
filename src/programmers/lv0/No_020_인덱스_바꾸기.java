package programmers.lv0;

public class No_020_인덱스_바꾸기 {

    class Solution {

        int gcd(int a, int b) {
            if (b == 0) return a;
            return gcd(b, a % b);
        }

        public int[] solution(int denum1, int num1, int denum2, int num2) {

            int n = gcd(num1, num2);
            int n1 = num1 / n;
            int n2 = num2 / n;

            int max = n1 * n2 * n; // 최소공배수

            denum1 *= max / num1;
            denum2 *= max / num2;

            int denum = denum1 + denum2;

            n = gcd(denum, max); // 최종 분자 분모의 gcd
            denum /= n;
            max /= n;

            int[] answer = {denum, max};

            return answer;
        }
    }
}
