package programmers.lv1;

/**
 * 처음에 재귀로 풀 생각을 못했다
 * 확실히 재귀는 기초이지만
 * 구현할 때마다 헷갈린다
 * 하지만, 확실히 다른 알고리즘보다 재밌는 방식이다
 */

public class No_006_푸드_파이트_대회 {

    class Solution {

        StringBuilder sb = new StringBuilder();
        int[] arr;

        public void solve(int num) {

            if (num == arr.length) {
                sb.append('0');
                return;
            }

            for (int i = 0; i < arr[num] / 2; i++) {
                sb.append(num);
            }

            solve(num + 1);

            for (int i = 0; i < arr[num] / 2; i++) {
                sb.append(num);
            }
        }

        public String solution(int[] food) {
            String answer = "";
            arr = food;

            solve(1);

            answer = sb.toString();
            return answer;
        }
    }
}
