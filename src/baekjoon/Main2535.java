package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 설계 1분 구현 8분
 일단
 점수순으로 나열해서
 3등까지의 국가와 번호를 구함
 그리고 각 국가에서 몇 개의 메달을 땄는지
 map을 통해 관리해줌
 */
public class Main2535 {

    static int N;
    static Node[] arr;
    static Map<Integer, Integer> map;

    // 학생 정보
    private static class Node implements Comparable<Node> {
        int country;
        int num;
        int score;

        public Node(int country, int num, int score) {
            this.country = country;
            this.num = num;
            this.score = score;
        }

        @Override
        public int compareTo(Node o) {
            // 점수 높은 순
            return -(this.score - o.score);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2535.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new Node[N];
        map = new HashMap();

        // 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int country = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());

            arr[i] = new Node(country, num, score);
        }

        // 점수순으로 정렬
        Arrays.sort(arr);

        int count = 0;  // 몇 명 뽑았는지
        int index = 0;  // 배열 인덱스
        StringBuilder sb = new StringBuilder();
        // 3명 뽑을 때까지 반복
        while (count < 3) {
            Node node = arr[index];

            // 이미 해당 국가가 메달을 딴 경우
            if (map.containsKey(node.country)) {
                // 메달을 2개 이상 딴 경우
                if (map.get(node.country) >= 2) {
                    index++;
                    continue;
                }

                map.put(node.country, map.get(node.country) + 1);
            }
            // 해당 국가가 메달이 없는 경우
            else {
                map.put(node.country, 1);
            }

            sb.append(node.country + " " + node.num + "\n");
            count++;
            index++;
        }

        System.out.println(sb);
    }
}
