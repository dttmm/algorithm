package baekjoon;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 완전 탐색을 해야되는 문제
 4가지 경우를 재귀를 이용해서 풀음
 이미 한번 확인한 숫자가 나올 경우는 또 확인할 필요가 없으므로
 visited를 만들어서 방문 표시겸 지금까지 해당 숫자가 나오기 위해 한 명령어들음 담음

 처음에는 Node 클래스에 지금까지 해당 숫자가 나오기 위해 했던 명령어들을
 Node마다 가지고 있었는데, 이 것이 시간초과의 원인이었음
 visited 배열로 그냥 한번에 관리가 가능했더라
 또한, 방문 표시를 하지 않아 메모리 초과도 났었음

 L, R 같은 경우
 숫자를 똘려야 하기 때문에
 관리가 편하도록 char 배열로 숫자를 관리함

 그러고보니 이게 바로 BFS였구나..
 */
public class Main9019 {

    private static class Node {
        char[] num = {'0', '0', '0', '0'};

        public Node(int n) {
            int index = 3;
            while (n != 0) {
                num[index--] = (char) ((n % 10) + '0');
                n /= 10;
            }
        }
    }

    static int D(int num) {
        return num * 2 % 10000;
    }

    static int S(int num) {
        return num == 0 ? 9999 : num - 1;
    }

    static char[] L(char[] num) {
        char[] newNum = new char[4];
        for (int i = 0; i < 4; i++) {
            newNum[i] = num[(i + 1) % 4];
        }
        return newNum;
    }

    static char[] R(char[] num) {
        char[] newNum = new char[4];
        for (int i = 0; i < 4; i++) {
            newNum[(i + 1) % 4] = num[i];
        }
        return newNum;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/9019.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            String[] visited = new String[10000];   // 지금 숫자 까지의 정보를 담을 배열겸 방문 표시

            Queue<Node> queue = new LinkedList();
            queue.add(new Node(A));
            visited[A] = "";

            while (!queue.isEmpty()) {
                Node node = queue.poll();
                int num = Integer.parseInt(String.valueOf(node.num));

                // 목표 숫자 찾은 경우
                if (num == B) {
                    bw.write(visited[num] + "\n");
                    break;
                }

                String s = visited[num];
                // D
                int result = D(num);
                if (visited[result] == null) {
                    queue.add(new Node(result));
                    visited[result] = s + "D";
                }

                // S
                result = S(num);
                if (visited[result] == null) {
                    queue.add(new Node(result));
                    visited[result] = s + "S";
                }

                // L
                char[] chars = L(node.num);
                num = Integer.parseInt(String.valueOf(chars));
                if (visited[num] == null) {
                    queue.add(new Node(num));
                    visited[num] = s + "L";
                }

                // R
                chars = R(node.num);
                num = Integer.parseInt(String.valueOf(chars));
                if (visited[num] == null) {
                    queue.add(new Node(num));
                    visited[num] = s + "R";
                }
            }
        }

        bw.flush();
        bw.close();
    }
}
