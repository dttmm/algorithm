package programmers.lv1;

import java.util.*;

/**
 처음에 너무 복잡하게 생갔했었음
 w에 대해 우선순위 세우고
 h에 대해 우선순위 세우고
 각각의 우선순위에서 큰놈들 뽑아가면서
 회전시켜도 조건 만족하는지 검사하면서 최대값 조정했었는데
 너무 복잡했네

 그냥 w, h 구분없이
 둘중에 큰 수와 둘중에 작은 수 나눠서
 큰 수들중 최댓값과 작은 수들중 최댓값 구하면 되넹
 w, h를 구분해서 회전하려고 했던것이 함정이었군하
 */
public class No_018_최소직사각형 {

    class Solution {

        public int solution(int[][] sizes) {
            int answer = 0;

            int max_n1 = 0;
            int max_n2 = 0;
            for (int i = 0; i < sizes.length; i++) {
                int w = sizes[i][0];
                int h = sizes[i][1];

                int n1 = Math.max(w, h);
                int n2 = Math.min(w, h);

                max_n1 = Math.max(max_n1, n1);
                max_n2 = Math.max(max_n2, n2);
            }

            answer = max_n1 * max_n2;
            return answer;
        }
    }
}
