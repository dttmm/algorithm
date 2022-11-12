package programmers.lv0;

public class No_023_공_던지기 {

    class Solution {
        public int solution(int[] numbers, int k) {
            int answer = 0;

            // k 줄이기
            int size = numbers.length;
            if (size % 2 == 0) k %= size;
            else k %= size * 2;

            // k 만큼 공 던지기
            int index = 0;
            while (k > 1) {
                index += 2;
                if (index >= size) index -= size;
                k--;
            }

            answer = numbers[index];
            return answer;
        }
    }
}
