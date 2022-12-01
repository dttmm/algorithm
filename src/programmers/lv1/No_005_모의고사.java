package programmers.lv1;

import java.util.*;

/**
 * 최대 정답 개수를 구한다음에
 * 최대 정답자를 찾은 다음에
 * 마지막으로 정답자들을 정렬해야 되는데
 * 처음에 최대 정답자를 리스트에 넣었다가
 * 배열로 옮겨 담는 과정이 뭔가 비효율적이다
 * 뭔가뭔가 효율적인 코드가 생각이 안난다
 */

public class No_005_모의고사 {


    class Solution {

        int[] n1 = {1, 2, 3, 4, 5};
        int[] n2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] n3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        public int[] solution(int[] answers) {
            int[] answer = new int[3];

            int index1 = 0;
            int index2 = 0;
            int index3 = 0;
            for (int i = 0; i < answers.length; i++) {
                int n = answers[i];

                if (n == n1[index1++]) answer[0]++;
                if (n == n2[index2++]) answer[1]++;
                if (n == n3[index3++]) answer[2]++;

                index1 %= n1.length;
                index2 %= n2.length;
                index3 %= n3.length;
            }

            // 최대 정답 개수 구함
            int max = answer[0];
            max = Math.max(max, answer[1]);
            max = Math.max(max, answer[2]);

            // 최대 정답 개수인 사람들 리스트에 담음
            List<Integer> list = new ArrayList();
            for (int i = 0; i < 3; i++) {
                if (max == answer[i]) list.add(i + 1);
            }

            // 리스트에서 배열로 담음
            answer = new int[list.size()];
            int index = 0;
            for (int i : list) {
                answer[index++] = i;
            }

            Arrays.sort(answer);

            return answer;
        }
    }
}
