package programmers.lv1;

/**
 단순 구현 문제
 길이 비교를 하기 위해 각 키들의 위치 정보를 배열에 담아놨음
 각 손의 위치 정보도 변수로 담아놨음

 단순이 조건을 따져가면서 분기해주면 됨
 0,2,5,8인 경우 거리를 재고
 거리가 같은 경우 어느손잡이인지 분기해 주었음
 */
public class No_012_키패드_누르기 {

    class Solution {

        final String LEFT = "L";
        final String RIGHT = "R";

        // 각 키들의 위치정보
        int[] keys_i = {3, 0, 0, 0, 1, 1, 1, 2, 2, 2};
        int[] keys_j = {1, 0, 1, 2, 0, 1, 2, 0, 1, 2};

        // 각 손의 위치정보
        int R_i = 3;
        int R_j = 2;
        int L_i = 3;
        int L_j = 0;

        public String solution(int[] numbers, String hand) {
            String answer = "";
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < numbers.length; i++) {
                int n = numbers[i];

                // 1,4,7인 경우
                if (n == 1 || n == 4 || n == 7) {
                    sb.append(LEFT);
                    L_i = keys_i[n];
                    L_j = keys_j[n];
                }
                // 3,6,9인 경우
                else if (n == 3 || n == 6 || n == 9) {
                    sb.append(RIGHT);
                    R_i = keys_i[n];
                    R_j = keys_j[n];
                }
                // 0,2,5,8인 경우
                else {
                    int L_distance = Math.abs(L_i - keys_i[n]) + Math.abs(L_j - keys_j[n]);
                    int R_distance = Math.abs(R_i - keys_i[n]) + Math.abs(R_j - keys_j[n]);

                    // 왼쪽 손이 더 가까운 경우
                    if (L_distance < R_distance) {
                        sb.append(LEFT);
                        L_i = keys_i[n];
                        L_j = keys_j[n];
                    }
                    // 오른쪽 손이 더 가까운 경우
                    else if (R_distance < L_distance) {
                        sb.append(RIGHT);
                        R_i = keys_i[n];
                        R_j = keys_j[n];
                    }
                    // 거리가 같은 경우
                    else {
                        // 오른손잡이인 경우
                        if (hand.equals("right")) {
                            sb.append(RIGHT);
                            R_i = keys_i[n];
                            R_j = keys_j[n];
                        }
                        // 왼손잡이인 경우
                        else {
                            sb.append(LEFT);
                            L_i = keys_i[n];
                            L_j = keys_j[n];
                        }
                    }
                }
            }
            answer = sb.toString();
            return answer;
        }
    }
}
