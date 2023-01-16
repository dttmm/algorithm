package softeer.lv3;

import java.util.*;
import java.io.*;

/**
 재귀를 이용하여 풀음
 배열을 이용해서 트리를 만들고
 리프 노드가 나올 때 까지 재귀를 타고 들어가서
 자신의 queue에서 업무를 하나 빼서 상사의 queue에 추가해주면 됨
 단, 현재 트리의 높이와 전체 트리의 높이에 따라
 어느 자식을 먼저 처리해야 되는지가 달라짐

 분명히 로직은 맞는데 계속 틀리길래
 보니까 상수 잘못 사용해서 시간날림...!!!😡
 */
public class Main_001_업무_처리 {

    public class Main {

        static int H;
        static int K;
        static int R;
        static Queue<Integer>[] queues;
        static int size;

        static void solve(int index, int k) {

            // 상사한테 일 넘기기
            if (!queues[index].isEmpty() && index != 1) {
                int n = queues[index].poll();
                queues[index / 2].add(n);
            }

            // 리프 노드가 나올 때까지 재귀
            if (index < size / 2) {
                // 현재 트리의 높이, 전체 트리의 높이에 따라 처리할 업무 순서 바뀜
                if ((H - k) % 2 == 0) {
                    solve(index * 2, k + 1);
                    solve(index * 2 + 1, k + 1);
                } else {
                    solve(index * 2 + 1, k + 1);
                    solve(index * 2, k + 1);
                }
            }
        }

        public static void main(String args[]) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            H = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            size = (int) Math.pow(2, H + 1);

            queues = new LinkedList[size];
            for (int i = 1; i < size; i++) {
                queues[i] = new LinkedList();
            }

            for (int i = size / 2; i < size; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < K; j++) {
                    int n = Integer.parseInt(st.nextToken());
                    queues[i].add(n);
                }
            }

            long answer = 0;
            for (int i = 0; i < R; i++) {
                // 부서장이 갖고 있는 일 처리하기
                if (!queues[1].isEmpty()) {
                    answer += queues[1].poll();
                }
                solve(1, 0);
            }

            System.out.println(answer);
        }
    }
}
