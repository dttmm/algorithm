package programmers.lv1;

public class No_001_삼총사 {

    class Solution {

        int[] arr;
        int[] tr = new int[3];
        int N;
        int R = 3;
        int count = 0;

        void solve(int k, int start) {
            if (k == R) {
                if (tr[0] + tr[1] + tr[2] == 0) count++;
            } else {
                for (int i = start; i < N; i++) {
                    tr[k] = arr[i];
                    solve(k + 1, i + 1);
                }
            }
        }

        public int solution(int[] number) {
            int answer = 0;
            arr = number;
            N = number.length;

            solve(0, 0);

            answer = count;
            return answer;
        }
    }
}
