package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 1시간+
 네트워크 플로우 공부
 dinic 알고리즘 적용해서 풀어봄
 아직 알고리즘이 익숙하지 않아서 구현하는데 힘들었음

 아 양방향이라서
 capacity값을 += 해줘야 되네..

 대문자 알파벳과 소문자 알파벳의 정보를
 한 배열안에 같이 관리할 방법을 못찾아서
 배열 대신 map 사용했는데 너무 복잡하네
 그래서 결국 서칭해서 방법 찾음..
 */
public class Main6086 {

    static final int start = 0;
    static final int end = 25;
    static final int size = ('Z' - 'A' + 1) * 2;
    static int N;
    static int[] level;
    static int[][] capacity;
    static int[][] flow;
    static List<Integer>[] lists;

    // 알파벳 배열 인덱스로 변환
    static int cToInt(char c) {
        if (c >= 'A' && c <= 'Z') return c - 'A';
        return (c - 'a') + 26;
    }

    // 레벨 설정 - bfs
    static boolean setLevel() {
        for (int i = 0; i < size; i++) {
            level[i] = -1;
        }

        Queue<Integer> queue = new LinkedList();
        queue.add(start);
        level[start] = 0;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int u : lists[v]) {
                // 이미 방문했으면 패쓰
                if (level[u] != -1) continue;
                // 용량 다 찼으면 패쓰
                if (capacity[v][u] - flow[v][u] <= 0) continue;
                queue.add(u);
                level[u] = level[v] + 1;
            }
        }

        // 도착점에 도달 했는지 안했는지
        return level[end] != -1;
    }

    // 시작점에서부터 유량 계산 - dfs
    static int getFlow(int v, int amount) {
        // 도착점에 도달한 경우 -> 지금까의 경로 중에 흐를 수 있는 최소 유량 리턴
        if (v == end) return amount;

        for (int u : lists[v]) {
            // 다음 레벨이 아닌 경우 패쓰
            if (level[u] != level[v] + 1) continue;
            // 용량 다 찼으면 패쓰
            if (capacity[v][u] - flow[v][u] <= 0) continue;

            int result = getFlow(u, Math.min(amount, capacity[v][u] - flow[v][u]));
            // 유량이 흐를 수 있는 경우
            if (result > 0) {
                flow[v][u] += result;
                flow[u][v] -= result;
                return result;
            }
        }
        // 도착점에 가지 못하거나 더이상 흐를 유량이 없는 경우
        return 0;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/6086.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        level = new int[size];
        capacity = new int[size][size];
        flow = new int[size][size];
        lists = new List[size];

        // 초기화
        for (int i = 0; i < size; i++) {
            lists[i] = new ArrayList();
        }

        // 입력 받기
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = cToInt(st.nextToken().charAt(0));
            int to = cToInt(st.nextToken().charAt(0));
            int c = Integer.parseInt(st.nextToken());

            capacity[from][to] += c;
            capacity[to][from] += c;
            lists[from].add(to);
            lists[to].add(from);
        }

        // 최대 유량 구하기
        int total = 0;
        while (setLevel()) {
            int result = getFlow(0, Integer.MAX_VALUE);
            if (result == 0) break;
            total += result;
        }

        System.out.println(total);
    }
}
