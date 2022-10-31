package baekjoon;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main10866 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10866.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "push_front": {
                    int n = Integer.parseInt(st.nextToken());
                    deque.addFirst(n);
                    break;
                }
                case "push_back": {
                    int n = Integer.parseInt(st.nextToken());
                    deque.addLast(n);
                    break;
                }
                case "pop_front": {
                    if (deque.isEmpty()) bw.write("-1\n");
                    else bw.write(deque.pollFirst() + "\n");
                    break;
                }
                case "pop_back": {
                    if (deque.isEmpty()) bw.write("-1\n");
                    else bw.write(deque.pollLast() + "\n");
                    break;
                }
                case "size": {
                    bw.write(deque.size() + "\n");
                    break;
                }
                case "empty": {
                    if (deque.isEmpty()) bw.write("1\n");
                    else bw.write("0\n");
                    break;
                }
                case "front": {
                    if (deque.isEmpty()) bw.write("-1\n");
                    else bw.write(deque.peekFirst() + "\n");
                    break;
                }
                case "back": {
                    if (deque.isEmpty()) bw.write("-1\n");
                    else bw.write(deque.peekLast() + "\n");
                    break;
                }
            }
        }
        bw.flush();
        bw.close();
    }
}
