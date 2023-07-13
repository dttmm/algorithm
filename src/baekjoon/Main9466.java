package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 설계 13분 구현 9분 디버깅 3분
 문제를 그려보니
 전체 노드 중에서 사이클이 발생하는 노드를 제외하면 되는 것으로 보임
 마침 얼마전에 본 강한결합요소가 생각남
 기억을 더듬어서 원리를 스택으로 구현함
 dfs를 이용하여 스택에 이웃 노드를 담는데
 이미 스택에 있는 노드라면
 사이클이 발생한 것으로
 사이클을 모두 스택에서 빼주면서 전체 노드에서 사이클 개수 빼줌

 틀림
 이미 방문했고 스택에 없는 노드인 경우
 스택에서 pop 해줬는데
 입력이 3 4 1 1인 경우
 stackEmpty에러 남
 어차피 이웃 노드가 하나밖에 없어서
 그냥 이미 방문했고 스택에 없는 노드인 경우에
 종료 시켜줘도 되겠넹

 알고보니 SCC로 푼게 아니라
 그냥 사이클 검사해서 푼거였네
 SCC 공부해서 다시 풀어보려 했는데
 짝이 정해지지 않은 노드도 하나의 SCC로 분류가 되어 버리네..
 */
public class Main9466 {

    static int N;
    static int[] select;
    static int[] p;
    static int answer;

    static void solve(int start) {
        Stack<Integer> stack = new Stack();
        stack.add(start);
        p[start] = start;

        while (!stack.isEmpty()) {
            int v = stack.peek();
            int u = select[v];

            // 이미 스택에 있는 노드인 경우
            if (p[u] == start) {
                while (stack.peek() != u) {
                    stack.pop();
                    answer--;
                }
                stack.pop();
                answer--;
                return;
            }
            // 아직 방문하지 않은 노드인 경우
            else if (p[u] == -1) {
                stack.add(u);
                p[u] = start;
            }
            // 이미 방문했고 스택에 없는 노드인 경우
            else return;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/9466.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            select = new int[N + 1];
            p = new int[N + 1];
            answer = N;

            // 초기화
            for (int i = 1; i <= N; i++) {
                p[i] = -1;
            }

            // 입력 받기
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                int n = Integer.parseInt(st.nextToken());
                select[i] = n;
            }

            // dfs 수행
            for (int i = 1; i <= N; i++) {
                if (p[i] != -1) continue;
                solve(i);
            }

            System.out.println(answer);
        }
    }
}
