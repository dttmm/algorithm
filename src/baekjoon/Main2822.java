package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 단순한 객체 정렬 문제
 점수순대로 정렬해서
 상위 5뮨제 뽑고
 뽑은 문제를 또 다시 정렬함
 */
public class Main2822 {

    final static int N = 8;
    static Node[] arr;

    private static class Node implements Comparable<Node> {
        int index;
        int num;

        public Node(int index, int num) {
            this.index = index;
            this.num = num;
        }

        @Override
        public int compareTo(Node o) {
            return -(this.num - o.num);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2822.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new Node[N];
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(br.readLine());
            arr[i - 1] = new Node(i, n);
        }

        Arrays.sort(arr);

        int total = 0;
        int[] answer = new int[5];
        for (int i = 0; i < 5; i++) {
            answer[i] = arr[i].index;
            total += arr[i].num;
        }

        Arrays.sort(answer);

        StringBuilder sb = new StringBuilder(total + "\n");
        for (int i = 0; i < 5; i++) {
            sb.append(answer[i] + " ");
        }

        System.out.println(sb);
    }
}
