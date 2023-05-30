package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 설계 19분 구현 16분
 trie를 이용하여 데이터를 저장
 데이터를 저장하는 부분과
 데이터를 순회하는 부분만 잘 고려해주면 됨

 root노드를 모아 놓은 rootMap에 데이터 관리했는데
 그냥 rootNode하나 만들고 rootNode.childNode에 데이터 관리했어도 됐었네

 시간을 좀 더 줄이기 위해 층을 나타내는 "--"문자열을 담아놓은
 floors 정의해서 층 정보 반환하도록 함
 */
public class Main14725 {

    static final String standard = "--";
    static int N;
    static Map<String, Node> rootMap;
    static StringBuilder sb;
    static String[] floors;

    private static class Node {
        String data;
        int depth;  // 노드의 깊이
        Map<String, Node> childMap; // 노드의 자식들

        public Node(String data, int depth) {
            this.data = data;
            this.depth = depth;
            childMap = new TreeMap();
        }
    }

    // 층 정보 반환
    static String getFloor(int depth) {
        if (floors[depth] != null) return floors[depth];

        String s = "";
        for (int i = 0; i < depth; i++) {
            s += standard;
        }
        floors[depth] = s;
        return s;
    }

    // dfs
    static void solve(Node node) {
        sb.append(getFloor(node.depth));
        sb.append(node.data + "\n");

        // 자식 노드가 없는 경우
        if (node.childMap.isEmpty()) return;

        // 자식 노드들 탐색
        for (Map.Entry<String, Node> entry : node.childMap.entrySet()) {
            Node childNode = entry.getValue();
            solve(childNode);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/14725.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        rootMap = new TreeMap();
        sb = new StringBuilder();
        floors = new String[16];

        // 데이터 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());

            Node parentNode = null;
            for (int j = 0; j < K; j++) {
                String s = st.nextToken();

                // rootNode인 경우
                if (j == 0) {
                    // 이미 해당 노드 추가되어 있는 경우
                    if (rootMap.containsKey(s)) parentNode = rootMap.get(s);
                        // 해당 노드가 처음인 경우
                    else {
                        Node newNode = new Node(s, 0);
                        rootMap.put(s, newNode);
                        parentNode = newNode;
                    }
                    continue;
                }

                // 자식 노드인 경우

                // 이미 해당 노드 추가되어 있는 경우
                if (parentNode.childMap.containsKey(s)) parentNode = parentNode.childMap.get(s);
                    // 해당 노드가 처음인 경우
                else {
                    Node newNode = new Node(s, parentNode.depth + 1);
                    parentNode.childMap.put(s, newNode);
                    parentNode = newNode;
                }
            }
        }

        // rootNode들 탐색 시작
        for (Map.Entry<String, Node> entry : rootMap.entrySet()) {
            Node node = entry.getValue();
            solve(node);
        }

        // 마지막 불필요한 출력(\n) 제거
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}
