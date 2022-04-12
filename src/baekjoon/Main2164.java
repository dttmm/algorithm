package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main2164 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2164.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new LinkedList();
        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }

        int result = 0;
        boolean flag = true; // 버릴 차례인지 확인하는 플래그
        while (!queue.isEmpty()) {
            int n = queue.poll();
            if (!flag) {
                queue.add(n);
            }
            flag = !flag;
            result = n;
        }

        System.out.println(result);
    }
}
