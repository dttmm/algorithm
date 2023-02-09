package programmers.lv1;

/**
 단순 분기문으로 처리함
 성격유형 4가지에 따라서 변수를 만들고
 들어오는 질문에 따라 분기해준뒤
 답변에 따라 점수 산정을 함

 변수의 경우
 사전순으로 앞에 있는 놈의 점수를 얻을 때마다 양수를 더해주어서
 마지막에 변수가 0이상이면 앞에 있는 놈을 출력하면 된
 */
public class No_020_성격_유형_검사하기 {

    class Solution {

        int RT;
        int CF;
        int JM;
        int AN;

        public String solution(String[] survey, int[] choices) {
            String answer = "";

            for (int i = 0; i < survey.length; i++) {
                String s = survey[i];
                int n = choices[i];

                if (s.equals("RT")) RT += 4 - n;
                else if (s.equals("TR")) RT += n - 4;
                else if (s.equals("CF")) CF += 4 - n;
                else if (s.equals("FC")) CF += n - 4;
                else if (s.equals("JM")) JM += 4 - n;
                else if (s.equals("MJ")) JM += n - 4;
                else if (s.equals("AN")) AN += 4 - n;
                else if (s.equals("NA")) AN += n - 4;
            }

            if (RT >= 0) answer += "R";
            else answer += "T";

            if (CF >= 0) answer += "C";
            else answer += "F";

            if (JM >= 0) answer += "J";
            else answer += "M";

            if (AN >= 0) answer += "A";
            else answer += "N";

            return answer;
        }
    }
}
