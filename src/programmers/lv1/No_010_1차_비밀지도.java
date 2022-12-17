package programmers.lv1;

/**
 arr1과 arr2를 비트연산 해준뒤
 연산 결과를 이진수 String으로 변환
 다만 최대 길이가 5인데, 1을 이진수 String으로 변환(s)하면
 "1"로 되는 문제 발생. 원하는 건 "00001"임
 그래서 format을 이용하여 s가 n보다 짧을 경우, s를 n만큼 길이를 늘려줌
 마지막에 "1"을 "#"으로, "0"을 " "로 바꿔줌
 */

public class No_010_1차_비밀지도 {

    class Solution {
        public String[] solution(int n, int[] arr1, int[] arr2) {
            String[] answer = new String[n];

            for (int i = 0; i < n; i++) {
                arr1[i] |= arr2[i];

                String s = Integer.toBinaryString(arr1[i]);
                String formatS = String.format("%%%ds", n);
                s = String.format(formatS, s);

                answer[i] = s.replace("1", "#").replace("0", " ");
            }

            return answer;
        }
    }
}
