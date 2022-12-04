package programmers.lv1;

/**
 단순 수학 구현 문제
 total은 각 케이스 시작 전에 들고 있는 콜라의 총 개수
 케이스마다 total을 a로 나누어 몇 번이나 바꿀 수 있는지 계산을 하고
 b를 곱하여 바꾼 콜라 개수를 얻는다
 바꾼 콜라 개수 만큼 정답에 추가를 하고
 바꾼 콜라 개수에 남았던 콜라 개수(rest)를 더하여 total을 갱신
 */

public class No_007_콜라_문제 {

    class Solution {
        public int solution(int a, int b, int n) {
            int answer = 0;

            int total = n;  // 케이스마다 총 콜라수
            int rest = 0;   // 케이스마다 남는 콜라수
            while (total >= a) {
                rest = total % a;
                answer += (total / a) * b;

                total = (total / a) * b + rest;
            }

            return answer;
        }
    }
}
