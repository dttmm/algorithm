package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 15분 구현 11분
 완탐
 N이 50이라 모든 점끼리 기울기를 구해도 시간은 충분함
 문제는 기울기를 어떻게 활용할 것인가임
 먼저 하나의 건물을 기준으로 잡고
 해당 건물 옆에 있는 건물들을 하나씩 비교해봄
 이전 건물들과 비교했을 때의 기울기의 최대값보다 새로 비교한 건물의 기울기가 더 크면
 이전 건물들을 포함하여 새로 비교한 건물도 기준으로 잡은 건물에서 보임

 다만, 기울기를 직접 계산해서 기울기를 비교하면
 소수점 오차가 생길 수 있으므로
 비율을 이용해서 기울기 크기를 비교함

 건물 하나를 기준으로 잡았을때
 해당 건물 양쪽을 검사하는 것보다는
 오른쪽으로 가면서 오른쪽 건물만 검사하고
 만약 서로 보이는 두 건물을 찾았을 경우
 기준으로 잡은 건물의 오른쪽에 해당 건물이 하나 보인다고 표시하고 right[]++
 나머지 건물에서는 왼쪽에 기준으로 잡은 건물이 하나 보인다고 표시함 left[]++
 마지막에 오른쪽에 보이는 건물의 수 right와
 왼쪽에 보이는 건물의 수 left값의 합의 최대값을 구하면 됨
 */
public class Main1027 {

    static int N;
    static Node[] arr;  // 건물들
    static int[] right; // i건물에서 오른쪽을 봤을 때 보이는 건물 개수
    static int[] left;  // i건물에서 왼쪽을 봤을 때 보이는 건물 개수

    // 건물 위치 정보
    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 오른쪽으로 탐색하며 보이는 건물 찾기
    static void solve() {
        for (int j = 1; j <= N; j++) {
            Node node = arr[j]; // 기준으로 잡을 건물
            Node maxNode = new Node(node.x, -1);    // 기울기는 제일 작게 되도록 건물 좌표 초기화

            for (int jj = j + 1; jj <= N; jj++) {
                Node targetNode = arr[jj];  // 비교할 건물

                // 기울기를 비교
                // 이미 비교한 건물들 중에서 기울기가 가장 높은 건물(maxNode)보다 기울기가 작은 경우 -> 패쓰
                if ((long) (maxNode.y - node.y) * (targetNode.x - node.x) >= (long) (maxNode.x - node.x) * (targetNode.y - node.y))
                    continue;

                // 기울기가 가장 높은 건물이 갱신된 경우
                maxNode = targetNode;

                // 기준 건물에서 오른쪽에 보이는 건물 개수 업데이트
                right[j]++;
                // 비교 건물에서 왼쪽에 보이는 건물 개수 업데이트
                left[jj]++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1027.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new Node[N + 1];
        right = new int[N + 1];
        left = new int[N + 1];

        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());
            Node newNode = new Node(i, n);
            arr[i] = newNode;
        }

        // 보이는 건물 개수 구하기
        solve();

        // 최대값 구하기
        int max = 0;
        for (int i = 1; i <= N; i++) {
            int n = left[i] + right[i];
            max = Math.max(max, n);
        }

        System.out.println(max);
    }
}
