package baekjoon;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main10828 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10828.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "push": {
                    int n = Integer.parseInt(st.nextToken());

                    stack.add(n);
                    break;
                }
                case "pop": {
                    if (stack.isEmpty()) bw.write("-1\n");
                    else bw.write(stack.pop() + "\n");
                    break;
                }
                case "size": {
                    bw.write(stack.size() + "\n");
                    break;
                }
                case "empty": {
                    if (stack.isEmpty()) bw.write("1\n");
                    else bw.write("0\n");
                    break;
                }
                case "top": {
                    if (stack.isEmpty()) bw.write("-1\n");
                    else bw.write(stack.peek() + "\n");
                    break;
                }
            }
        }

        bw.flush();
        bw.close();
    }
}
