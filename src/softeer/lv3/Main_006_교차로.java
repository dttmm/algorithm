package softeer.lv3;

import java.util.*;
import java.io.*;

/**
 와우 B형 기출 유형이 나오네
 전형적인 tree 사용하면 편한 문제
 treemap을 이용하여 자동차가 온 순서대로
 교차로에 추가해주고
 교차로에 추가해주기 전에
 이미 교차로에 있는 차들 처리해줌
 */
public class Main_006_교차로 {

    public class Main {

        static int N;
        static Node[] nodes;
        static TreeMap<Integer, List<Node>> map;    // Integer시간에 온 자동차들 List에담을거임
        static Queue<Node>[] queues;    // 교차로
        static int lastTime;

        private static class Node {
            int road;
            int time;
            int exitTime = -1;

            public Node(int road, int time) {
                this.road = road;
                this.time = time;
            }
        }

        // limitTime시간에 온 자동차들을 교차로에 추가해주기전
        // 교차로에 남아있는 자동차 처리
        static boolean solve(int limitTime) {
            int count = 0;
            while (count != 4) {
                count = 0;  // 교차로에서 탈출할 수 있는 자동차의 수
                int deadlock = 0;   // 데드락 플래그
                boolean[] exits = new boolean[4];

                for (int dir = 0; dir < 4; dir++) {
                    if (queues[dir].isEmpty()) continue;
                    if (queues[(dir + 3) % 4].isEmpty()) {
                        exits[dir] = true;
                        count++;
                    } else deadlock++;
                }

                // 데드락이 발생한 경우
                if (deadlock == 4) return true;

                // 교차로에 아무 차도 없는 경우
                if (count == 0) break;

                // 교차로에서 탈출 가능한 차들 탈출
                for (int i = 0; i < 4; i++) {
                    if (!exits[i]) continue;

                    Node node = queues[i].poll();
                    node.exitTime = lastTime;
                }

                lastTime++;
                if (lastTime == limitTime) break;
            }
            return false;
        }

        public static void main(String args[]) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;

            N = Integer.parseInt(br.readLine());
            nodes = new Node[N];
            map = new TreeMap();
            queues = new LinkedList[4];
            lastTime = 0;

            for (int i = 0; i < 4; i++) {
                queues[i] = new LinkedList();
            }

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int time = Integer.parseInt(st.nextToken());
                int road = st.nextToken().charAt(0) - 'A';

                if (!map.containsKey(time)) map.put(time, new LinkedList());

                Node newNode = new Node(road, time);
                map.get(time).add(newNode);
                nodes[i] = newNode;
            }

            // 마지막까지 map에 남아있는 녀석들 처리해주기 위해
            map.put(1000000001, new LinkedList());

            // 자동차들이 온 시간순으로
            while (!map.isEmpty()) {
                Map.Entry<Integer, List<Node>> entry = map.pollFirstEntry();

                // entry.getKey()시간에 온 자동차들을 교차로에 추가해주기전
                // 교차로에 남아있는 자동차 처리
                boolean deadlock = solve(entry.getKey());

                // 데드락인 경우
                if (deadlock) break;

                // 교차로에 entry.getKey()시간에 온 자동차들 추가
                for (Node node : entry.getValue()) {
                    queues[node.road].add(node);
                }

                lastTime = entry.getKey();
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                int exitTime = nodes[i].exitTime;
                sb.append(exitTime + "\n");
            }

            System.out.println(sb);
        }
    }
}
