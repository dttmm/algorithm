package programmers.lv1;

import java.util.*;

public class No_003_문자열_내_마음대로_정렬하기 {

    class Solution {

        class Node implements Comparable<Node> {
            String word;
            char standard;

            public Node(String word, int n) {
                this.word = word;
                this.standard = word.charAt(n);
            }

            public int compareTo(Node o) {
                if (this.standard != o.standard) return this.standard - o.standard;
                return this.word.compareTo(o.word);
            }
        }

        public String[] solution(String[] strings, int n) {
            int size = strings.length;
            String[] answer = new String[size];
            Node[] nodes = new Node[size];

            for (int i = 0; i < size; i++) {
                Node newNode = new Node(strings[i], n);
                nodes[i] = newNode;
            }

            Arrays.sort(nodes);

            for (int i = 0; i < size; i++) {
                answer[i] = nodes[i].word;
            }

            return answer;
        }
    }
}
