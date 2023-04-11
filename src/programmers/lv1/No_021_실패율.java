package programmers.lv1;

import java.util.*;

/**
 각 스테이지가 가지고 있는 정보 (도착 횟수, 실패 횟수)를 관리해주기 위해
 Node클래스 만들어 줘서 관리함

 실패율 비교의 경우 비율을 이용하여 우선순위 판정함
 비율 구하기 위해서 reach, fail 곱할 때
 int 범위 벗어날 수 있으므로 long변환 해줘야됨
 만약 스테이지 1의 도착횟수가 8, 실패횟수가 1이고
 스테이지 2의 도착횟수가 7, 실패횟수가 3인 경우
 rate1 = 스테이지1 도착횟수 * 스테이지2 실패횟수 = 8 * 3 = 24
 rate2 = 스테이지2 도착횟수 * 스테이지1 실패횟수 = 7 * 1 = 7로
 rate2가 더 작으므로 스테이지2의 실패율이 더 높다고 할 수 있음 -> 스테이지2의 우선순위 더 높음

 각 플레이어가 도달한 스테이지 돌면서
 각 스테이지별로 도착횟수 ++해주고
 실패 스테이지의 경우 실패 횟수 ++해줌

 마지막에 우선순위대로 정렬하고 출력
 */
public class No_021_실패율 {

    class Solution {

        // 스테이지 정보
        private static class Node implements Comparable<Node> {
            int stageNum;   // 스테이지 번호
            int reach;  // 스테이지 달성 횟수
            int fail;   // 스테이지 실패 횟수

            public Node(int stageNum, int reach, int fail) {
                this.stageNum = stageNum;
                this.reach = reach;
                this.fail = fail;
            }

            public int compareTo(Node o) {
                // 둘의 비율을 비교
                long rate1 = (long) this.reach * o.fail;
                long rate2 = (long) this.fail * o.reach;

                if (rate1 != rate2) {
                    // rate1이 더 작을 경우 -> this의 우선순위가 더 높음(실패율이 더 높음)
                    if (rate1 < rate2) return -1;
                        // rate2가 더 작을 경우 -> this의 우선순위가 더 낮음(실패율이 더 낮음)
                    else return 1;
                }
                // 실패율이 같을 경우
                return this.stageNum - o.stageNum;
            }
        }

        public int[] solution(int N, int[] stages) {
            int[] answer = new int[N];
            Node[] nodes = new Node[N]; // 스테이지 정보 담은 배열

            for (int i = 0; i < N; i++) {
                nodes[i] = new Node(i + 1, 0, 0);
            }

            for (int i = 0; i < stages.length; i++) {
                int stage = stages[i];

                // 해당 스테이지 까지 가면서
                for (int j = 1; j <= stage; j++) {
                    if (j == N + 1) continue;    // 범위를 벗어난 경우(끝까지 클리어한 경우)

                    // 스테이지 도착
                    nodes[j - 1].reach += 1;

                    // 클리어하지 못한 스테이지
                    if (j != stage) continue;
                    nodes[j - 1].fail += 1;
                }
            }

            // 정렬
            Arrays.sort(nodes);

            for (int i = 0; i < N; i++) {
                answer[i] = nodes[i].stageNum;
            }

            return answer;
        }
    }
}
