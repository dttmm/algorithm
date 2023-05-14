package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 설계 1분 구현 2분
 춤을 추는 사람들을 set에 저장함
 춤을 추는 사람과 같이 있는 사람을 set에 추가해줌
 마지막에 set 사이즈 출력해주면 춤추는 사람 수 구할 수 있음
 */
public class Main26069 {

    static int N;
    static Set<String> set;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/26069.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        set = new HashSet();
        set.add("ChongChong");  // 총총이 추가

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String s1 = st.nextToken();
            String s2 = st.nextToken();

            if (set.contains(s1)) set.add(s2);
            else if (set.contains(s2)) set.add(s1);
        }

        System.out.println(set.size());
    }
}
