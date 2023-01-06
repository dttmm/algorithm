package programmers.lv1;

import java.util.*;

/**
 일단 로또 당첨 번호를 set에 저장하고
 민우가 고른 번호가 set에 있는지 확인함
 0은 조커카드라 생각하여
 0인 번호가 모두 로또 번호와 일치하게 되면 최대 순위가 나오고
 0인 번호가 로또 번호와 아무것도 일치하지 않는다면 최저 순위가 됨
 */
public class No_017_로또의_최고_순위와_최저_순위 {

    class Solution {

        Set<Integer> set = new HashSet();
        int[] rank = {6, 6, 5, 4, 3, 2, 1}; // 맞춘 개수가 0개 -> 6등 .. 6개 -> 1등

        public int[] solution(int[] lottos, int[] win_nums) {
            int[] answer = new int[2];

            // 당첨번호를 담는다
            for (int i = 0; i < win_nums.length; i++) {
                set.add(win_nums[i]);
            }

            int count = 0;  // 일치하는 개수
            int zero_count = 0;
            // 몇 개 일치하는지 확인
            for (int i = 0; i < lottos.length; i++) {
                int n = lottos[i];
                if (set.contains(n)) count++;
                if (n == 0) zero_count++;
            }

            answer[0] = rank[count + zero_count];
            answer[1] = rank[count];

            return answer;
        }
    }
}
