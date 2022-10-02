package baekjoon.IGRUS_Newbie_Programming_Contest_Open2022;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MainC {

    static int N;
    static int M;
    static Queue<Character> queue;
    static Map<Character, Boolean> map;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/IGRUS_Newbie_Programming_Contest_Open2022/C.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        queue = new LinkedList();
        map = new HashMap();

        String s = br.readLine();
        for (int i = 0; i < N; i++) {
            char c = s.charAt(i);
            queue.add(c);
            map.put(c, true);
        }
        char currnet = s.charAt(s.length() - 1);

        M = Integer.parseInt(br.readLine());
        s = br.readLine();
        int sum = 0;
        for (int i = 0; i < M; i++) {
            char c = s.charAt(i);
            if (map.get(c) == null) {
                sum = 0;
                break;
            }

            while (currnet != c) {
                sum++;
                char newC = queue.poll();
                currnet = newC;
                queue.add(newC);
            }
            sum++;
            char newC = queue.poll();
            currnet = newC;
            queue.add(newC);
        }
        System.out.println(sum - 1);
    }
}
