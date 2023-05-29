package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 설계 15븐 구현 37분
 바로 정답을 찾을 수 있는 루트가 안보여서
 완탐으로 정답을 찾아야겠다고 판단

 처음에는 백트래킹을 풀음
 돌이 있을 때 3가지 경우로 재귀 돌림
 1. 가장 작은 돌과 중간 돌을 움직이는 경우
 2. 중간 돌과 가장 큰 돌을 움직이는 경우
 3. 가장 큰 돌과 가장 작은 돌을 움직이는 경우
 하지만 숫자가 커지게 되면 경우의 수가 너무 많아져서
 스택 메모리 초과 남

 조금 더 경우의 수를 줄일 수 없을까 생각함
 가장 작은 돌과 중간 돌 둘 다 평균보다 작을 경우에는
 굳이 두 돌을 움직여봤자 정답을 구하는 것에는 영향을 안준다는 것을 깨달음
 그래서 중간값이 평균값을 넘는지 안넘는지 여부로 경우의 수를 세분화함
 1. 가장 작은 돌과 가장 큰 돌을 움직이는 경우
 2. 중간 돌과 가장 작은 돌을 움직이는 경우 (단, 중간 돌이 평균보다 커야됨)
 3. 중간 돌과 가장 큰 돌을 움직이는 경우 (단, 중간 돌이 평균보다 작아야됨)
 근데도 스택 메모리 초과 남

 재귀를 어떻게 줄일 수 있을까 고민하다
 문제 유형 슬쩍 봄
 앗 bfs..
 재귀를 bfs로 바꾸니 메모리 초과 안남
 왜 저걸 생각 못했을끄앙
 */
public class Main12886 {

    static Set<String> set;
    static int avg;

    // 돌의 개수 순서대로 a,b,c에 저장
    private static class Node {
        int a;
        int b;
        int c;

        public Node(int a, int b, int c) {
            int[] arr = {a, b, c};
            Arrays.sort(arr);

            this.a = arr[0];
            this.b = arr[1];
            this.c = arr[2];
        }

        public boolean isSame() {
            return (this.a == this.b) && (this.b == this.c);
        }

        @Override
        public String toString() {
            return a + " " + b + " " + c;
        }
    }

    static boolean solve(Node startNode) {
        set.add(startNode.toString());
        Queue<Node> queue = new LinkedList();
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            int A = node.a;
            int B = node.b;
            int C = node.c;

            if (node.isSame()) return true;

            // 가장 작은 돌과 가장 큰 돌을 움직이는 경우
            if (C != A) {
                Node newNode = new Node(A + A, B, C - A);
                if (!set.contains(newNode.toString())) {
                    set.add(node.toString());
                    queue.add(newNode);
                }
            }
            // 중간 돌과 가장 작은 돌을 움직이는 경우
            if (B > avg) {
                Node newNode = new Node(A + A, B - A, C);
                if (!set.contains(newNode.toString())) {
                    set.add(node.toString());
                    queue.add(newNode);
                }
            }
            // 중간 돌과 가장 큰 돌을 움직이는 경우
            if (B < avg) {
                Node newNode = new Node(A, B + B, C - B);
                if (!set.contains(newNode.toString())) {
                    set.add(node.toString());
                    queue.add(newNode);
                }
            }
        }
        return false;
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/12886.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        set = new HashSet();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        // 돌의 총합이 3의 배수인 경우
        if ((A + B + C) % 3 == 0) {
            avg = (A + B + C) / 3;
            boolean result = solve(new Node(A, B, C));

            if (result) System.out.println(1);
            else System.out.println(0);
        } else System.out.println(0);


    }
}
