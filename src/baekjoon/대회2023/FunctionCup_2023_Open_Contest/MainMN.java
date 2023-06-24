package baekjoon.대회2023.FunctionCup_2023_Open_Contest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 20분 구현 6분 디버깅 1분
 트리의 깊이에 해당하는 노드정보를 nodes에서 관리하고
 각 입력을 받을 때마다 nodes정보를 갱신함

 10의 6승을 십만이라고 착각해서 한번 틀림
 */
public class MainMN {

    static int N;
    static Node[] nodes;

    static class Node {
        int count;  // 현재 깊이의 노드 개수
        Node next;  // 현재 노드의 다음 자식 노드 정보

        public Node(int count) {
            this.count = count;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/FunctionCup_2023_Open_Contest/MN.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new Node[1000001];

        for (int i = 0; i <= 1000000; i++) {
            nodes[i] = new Node(0);
        }
        nodes[0].count = 1; // 루트 노드 설정

        // 입력  받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean flag = true;
        int cur = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());

            // 올바른 입력이 아닌 경우 <- 부모 노드가 없는 경우
            if (nodes[n - 1].count == 0) {
                flag = false;
                break;
            }

            // 현재 보다 상위 깊이 받은 경우 -> 현재 레벨의 자식 정보 초기화
            if (n < cur) {
                nodes[n].next = null;
            }

            // 부모 노드의 자식 정보가 없는 경우 -> 자식 정보 새로 추가
            if (nodes[n - 1].next == null) {
                nodes[n] = new Node(1);
                nodes[n - 1].next = nodes[n];
            }
            // 자식 형제 개수 늘려줌
            else {
                nodes[n].count++;
            }

            // 현재 깊이에 해당하는 형제 정보 출력
            sb.append(nodes[n].count + " ");
            cur = n;
        }

        if (flag) System.out.println(sb);
        else System.out.println("-1");
    }
}
