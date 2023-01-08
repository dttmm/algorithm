package baekjoon.대회2023.제1회_보라매컵_예선_Open_Contest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class MainB {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/제1회_보라매컵_예선_Open_Contest/B.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Set<Integer> set = new HashSet();

        int answer = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int type = Integer.parseInt(st.nextToken());

            if (type == 1) {
                if (set.contains(n)) answer++;
                set.add(n);
            } else {
                if (!set.contains(n)) answer++;
                set.remove(n);
            }
        }

        answer += set.size();

        System.out.println(answer);
    }
}
