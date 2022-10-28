package baekjoon;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main10845 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10845.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> queue = new ArrayDeque();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "push": {
                    int n = Integer.parseInt(st.nextToken());
                    queue.add(n);
                    break;
                }
                case "pop": {
                    if (queue.isEmpty()) bw.write("-1\n");
                    else bw.write(queue.poll() + "\n");
                    break;
                }
                case "size": {
                    bw.write(queue.size() + "\n");
                    break;
                }
                case "empty": {
                    if (queue.isEmpty()) bw.write("1\n");
                    else bw.write("0\n");
                    break;
                }
                case "front": {
                    if (queue.isEmpty()) bw.write("-1\n");
                    else bw.write(queue.getFirst() + "\n");
                    break;
                }
                case "back": {
                    if (queue.isEmpty()) bw.write("-1\n");
                    else bw.write(queue.getLast() + "\n");
                }

            }
        }
        bw.flush();
        bw.close();
    }
}
