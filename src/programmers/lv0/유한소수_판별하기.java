package programmers.lv0;

public class 유한소수_판별하기 {

    int gcd(int a, int b) {
        int rest = b % a;
        if (rest == 0) return a;
        return gcd(rest, a);
    }

    public int solution(int a, int b) {
        int answer = 1;

        // 최소 공약수를 구한다
        int gcd = gcd(a, b);

        // a, b를 최소공약수로 나눈다
        a /= gcd;
        b /= gcd;

        // 최소공약수로 나누 b가 2나 5로만 나눠지는지 검사
        int pre = -1;
        while (b != 1) {
            if (pre == b) {
                answer = 2;
                break;
            }
            pre = b;

            if (b % 2 == 0) b /= 2;
            if (b % 5 == 0) b /= 5;
        }

        return answer;
    }
}
