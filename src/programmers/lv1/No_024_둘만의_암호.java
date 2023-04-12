package programmers.lv1;

import java.util.*;

/**
 a부터 z까지 알파벳(c)과
 해당 알파벳이 어느 만큼 점프할지(index) 정보를 저장하는 Node를 만들고
 a부터 z까지 정보를 담은 nodes 배열 만듬

 모든 알파벳은 기본 index만큼의 index값을 가지게 되고
 skip 알파벳의 경우
 skip 알파벳의 뒤에 있는 알파벳의 index값을 하나 증가시킨다면
 뒤에 있는 알파벳는 skip 알파벳을 건너뛰는 효과를 가짐

 그래서 skip 알파벳의 뒤에 있는 알파벳 index개 만큼 해당 알파벳의 index를 증가시키면 됨

 다만 skip 알파벳의 뒤에 있는 알파벳이 skip 알파벳일 경우
 탐색 범위(limit)를 한번 늘려줘야됨

 또한 알파벳 개수는 26개 인데
 만약 skip 알파벳이 10개이고
 초기 index가 20이라면
 nodes배열을 한반퀴 더 돌아야 하는 상황 발생
 그러면 newI를 구하는 과정에서 i - j가 -26보다 커질 수 있으므로
 int newI = (52 + i - j) % 26;
 i - j에 26보다 더 큰 52를 더해줌

 생각보다 구현에서 헤멨느아
 설계 더 탄탄히 하즈아
 엣지케이스 더 탄탄하게 가즈아
 */
public class No_024_둘만의_암호 {

    class Solution {

        // 각 알파벳별 index정보 저장
        private static class Node {
            char c;
            int index;

            public Node(char c, int index) {
                this.c = c;
                this.index = index;
            }
        }

        public String solution(String s, String skip, int index) {
            String answer = "";
            Set<Character> set = new HashSet();
            Node[] nodes = new Node[26];

            // set에 skip할 알파벳 저장
            for (int i = 0; i < skip.length(); i++) {
                char c = skip.charAt(i);
                set.add(c);
            }

            // nodes에 각 알파벳 정보 저장
            // 각 알파벳별 기본 index는 index로 설정
            for (int i = 0; i < 26; i++) {
                char c = (char) ((int) 'a' + i);
                nodes[i] = new Node(c, index);
            }

            // 알파벳을 돌면서
            for (int i = 0; i < 26; i++) {
                char c = nodes[i].c;

                // skip이 아닌 경우 패쓰
                if (!set.contains(c)) continue;

                // skip 알파벳의 뒤에 있는 index개의 알파벳들의 index를 증가시켜줌
                int limit = index;
                for (int j = 1; j <= limit; j++) {
                    // i - j가 -26보다 작을 수 있으므로 52 더해줌
                    int newI = (52 + i - j) % 26;

                    // skip 알파벳의 뒤에 있는 알파벳
                    char newC = nodes[newI].c;

                    // 해당 알파벳이 skip 알파벳이라면 -> 탐색 범위 한칸 더 늘려줌
                    if (set.contains(newC)) {
                        limit++;
                        continue;
                    }

                    // 해당 알파벳의 index증가
                    nodes[newI].index++;
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                // 현재 알파벳의 위치와
                int currentIndex = c - 'a';
                // 얼마만큼 점프할지 index값 구해서
                int nextIndex = nodes[currentIndex].index;

                // 둘이 더한 값 만큼의 위치에 있는 알파벳 출력
                char nextC = nodes[(currentIndex + nextIndex) % 26].c;
                sb.append(nextC);
            }

            answer = sb.toString();
            return answer;
        }
    }
}
