package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 나이브하게 풀면 N^2으로 30만*30만 시간 초과 발생
 정렬을 통해 탐색 횟수를 줄일 수 있을 것으로 보였음
 보석의 경우 먼저 가치가 높은 것을 고르고
 해당 보석을 담을 수 있는 가방을 고르면 됨

 그래서 보석은 가치가 높은 것 부터
 그 다음 무게가 가벼운 것 순으로 정렬함

 가방도 무게 순으로 정렬하고
 보석 하나를 뽑았을 때
 담을 수 있는 가방을 이분탐색으로 찾아냄
 근데 시간 초과

 생각해보니 무게가 똑같은 가방이 여러개 있으면
 불필요한 탐색이 발생할거라 생각함
 예를 들어, 보석 30만개가 모두 무게가 101 넘는데
 가방 30만개의 무게가 모두 100 이라면
 보석 30만개를 돌면서 가방을 이분탐색하면
 30만 * log(30만) = 30만 * 19 * 100 = 약 6억
 그래서 중복되는 가방을 Map에 담아서 관리해줌

 어라? 생각해보니까 30만 * 19 = 약 600만이네
 100 왜 곱해줬지??
 보석 30만개를 돌면서 가방을 이분탐색하는 경우는
 시간 초과의 원인이 아니라는 것인데
 뭐지..?
 중복되는 가방 관리만 해줬을뿐인데 왜 시간초과 안나지..?

 ArrayList의 경우 remove가 O(n)의 시간 복잡도를 가지고
 LinkedList는 O(1)의 시간 복잡도를 가진다고 해서
 remove하는 부분이 문제인가 싶어서 기존 시간 초과 코드에서
 LinkedList를 사용했는데 그래도 시간 초과 났믜

 보석을 1 1 ~ 30만 30만 까지 만들고
 가방도 1 ~ 30만 까지 만들어서
 돌렸더니 시간 초과 안남
 이번엔 1짜리 가방을 30만개인 경우, 30만 짜리 가방을 30만개인 경우 만들고
 각각의 경우를 중복 검사 없이 돌렸더니 시간 초과 안남
 가방이 중복되었을 때 문제가 발생하는 경우를 못찼겠흐어

 2시간 넘는 삽질 끝에 찾았따
 가방이 중복되었을 때 문제가 발생하는 것이 아니라
 리스트 삭제와 관련된 문제였따

 보석을 1 30만 ~ 30만 1 까지 만들고
 무게가 30만 이상인 가방 30만개를 중복되지 않게 만들면
 보석을 돌면서
 가방 리스트에서 제일 앞에 있는 인덱스를 삭제하기 때문에
 삭제할 때마다 O(가방의 개수)만큼 시간복잡도 발생
 보석 개수 * 가방의 개수 = 30만 * 30만 으로 시간 초과 발생함

 긍까 원래는 틀린 코드라는 것뜨아
 백준에 데이터 없는것 같아서 데이터 요청함
 */
public class Main1202 {

    static int N;
    static int K;
    static Node[] nodes;    // 보석들
    static List<Integer> bags;  // 가방 (중복 제거된)무게들 정보
    static Map<Integer, Integer> count; // 동일한 무게를 가진 가방 개수 카운트

    private static class Node implements Comparable<Node> {
        int m;
        int v;

        public Node(int m, int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Node o) {
            // 가치가 높은 것이 우선
            if (this.v != o.v) return -(this.v - o.v);
            // 무게가 작은 것이 우선
            return this.m - o.m;
        }
    }

    // 이분 탐색 -> 최적의 가방 인덱스 반환
    static int solve(int s, int e, int target, int best) {
        if (s > e) return best;

        int mid = (s + e) / 2;
        int bagC = bags.get(mid);

        if (bagC == target) return mid;
        else if (bagC < target) return solve(mid + 1, e, target, best);
        else {
            best = mid;
            return solve(s, mid - 1, target, best);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1202.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        nodes = new Node[N];
        bags = new ArrayList();
        count = new HashMap();

        // 보석 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(m, v);
        }

        // 가방 입력
        for (int i = 0; i < K; i++) {
            int n = Integer.parseInt(br.readLine());

            if (count.get(n) == null) {
                count.put(n, 1);
                bags.add(n);

            } else count.put(n, count.get(n) + 1);
        }

        // 정렬
        Arrays.sort(nodes);
        Collections.sort(bags);

        long answer = 0;
        // 보석들 돌면서
        for (int i = 0; i < N; i++) {
            Node node = nodes[i];
            // 해당 보석을 담을 수 있는 가장 작은 가방 찾음
            int bagIndex = solve(0, bags.size() - 1, node.m, -1);

            // 담을 수 있는 가방 찾지 못한 경우는 패쓰
            if (bagIndex == -1) continue;

            // 보석 담아주고
            answer += node.v;

            int bagC = bags.get(bagIndex);
            // 해당 무게를 가진 가방의 카운트를 하나 줄여주고
            count.put(bagC, count.get(bagC) - 1);
            // 해당 무게를 가진 가방을 다 사용하면 리스트에서 제거
            if (count.get(bagC) == 0)
                bags.remove(bagIndex);
        }

        System.out.println(answer);
    }
}
