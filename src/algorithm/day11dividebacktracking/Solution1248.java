package algorithm.day11dividebacktracking;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 32분
public class Solution1248 {
    static Node[] arr;
    static int[] q;
    static boolean[] count;
    static int front, rear;
    static int answerNode;
    static int answerSize;

    static class Node {
        int parent;
        int child1;
        int child2;
    }

    public static void setParent(int target) {
        while (arr[target].parent != 0) {
            count[arr[target].parent] = true;
            target = arr[target].parent;
        }
    }

    public static void getParent(int target) {
        q[++rear] = target;
        while (front != rear) {
            target = q[++front];
            if (count[arr[target].parent]) {
                answerNode = arr[target].parent;
                break;
            } else {
                q[++rear] = arr[target].parent;
            }
        }
    }

    public static void bfs(int v) {
        q[++rear] = v;
        while (front != rear) {
            v = q[++front];
            answerSize++;
            if (arr[v].child1 != 0) {
                q[++rear] = arr[v].child1;
            }
            if (arr[v].child2 != 0) {
                q[++rear] = arr[v].child2;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day11_1248.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int target1 = Integer.parseInt(st.nextToken());
            int target2 = Integer.parseInt(st.nextToken());
            arr = new Node[V + 1];
            for (int i = 1; i <= V; i++) {
                arr[i] = new Node();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                if (arr[parent].child1 == 0) {
                    arr[parent].child1 = child;
                } else {
                    arr[parent].child2 = child;
                }
                arr[child].parent = parent;
            }

            // 첫번째 타켓의 부모들을 카운트 변수에 담음
            count = new boolean[V + 1];
            setParent(target1);

            // 두번째 타켓의 부모들을 하나씩 꺼내보면서 카운트 변수에 있는 부모와 일치하는 경우 그 부모가 두 타겟의 제일 빠른 공통조상임
            q = new int[2 * V];
            front = -1;
            rear = -1;
            getParent(target2);

            // bfs를 돌려 공통 조상의 크기를 구함
            q = new int[2 * V];
            front = -1;
            rear = -1;
            answerSize = 0;
            bfs(answerNode);

            System.out.println("#" + test_case + " " + answerNode + " " + answerSize);
        }
    }
}
