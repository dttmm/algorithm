package programmers.lv0;

public class No_019_분수의_덧셈 {

    class Solution {
        public String solution(String my_string, int num1, int num2) {
            String answer = "";

            char[] arr = my_string.toCharArray();

            char temp = arr[num1];
            arr[num1] = arr[num2];
            arr[num2] = temp;

            answer = String.valueOf(arr);

            return answer;
        }
    }
}
