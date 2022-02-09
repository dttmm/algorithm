package baekjoon;

import java.io.FileInputStream;
import java.util.*;

public class Main1181 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1181.txt"));

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            set.add(sc.next());
        }

        for (int k = 1; k <= 50; k++) {
            List<String> list = new ArrayList<>();
            for (String s : set) {
                if (s.length() == k) {
                    list.add(s);
                }
            }
            Collections.sort(list);
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }
    }
}
