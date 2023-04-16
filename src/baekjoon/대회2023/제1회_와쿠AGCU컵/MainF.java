package baekjoon.대회2023.제1회_와쿠AGCU컵;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 치즈들을 set에 담아서
 set에 4종류 이상의 치즈가 있다면 정답
 중간에 치즈 4개 다 찾으면 탈출시킴
 */
public class MainF {

    static int N;
    static Set<String> set; // 치즈 담을 set

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/제1회_와쿠AGCU컵/F.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        set = new HashSet();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            String s = st.nextToken();

            // Cheese보다 길이 짧으면 패쓰
            if (s.length() < 6) continue;
            // Cheese로 안 끝나면 패쓰
            if (!s.endsWith("Cheese")) continue;

            set.add(s);
            // 치즈 4개 다 찾으면 탈출ㄱㄱ
            if (set.size() >= 4) break;
        }

        // 치즈 개수 검사
        if (set.size() >= 4) System.out.println("yummy");
        else System.out.println("sad");
    }
}
