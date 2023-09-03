package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 설계 2분 구현 8분
 중복순열

 아빠 엄마의 색을 모두 하나의 set에 담아두고
 set을 돌면서 조합할 수 있는 경우의 수를 answer에 저장함
 경우의 수를 구할 때는 중복순열로 모든 경우를 구함

 +
 2주만에 알고리즘 풀려니까
 코드가 손에 안익는다
 머리도 굳은 듯
 */
public class Main28445 {

    static Set<String> set;
    static TreeSet<String> answer;
    static String[] tr;

    // 중복순열
    static void solve(int k) {
        if (k == 2) {
            String s = tr[0] + " " + tr[1];
            answer.add(s);
        } else {
            for (String s : set) {
                tr[k] = s;
                solve(k + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/28445.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        set = new HashSet();
        answer = new TreeSet();
        tr = new String[2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        set.add(st.nextToken());
        set.add(st.nextToken());

        st = new StringTokenizer(br.readLine());
        set.add(st.nextToken());
        set.add(st.nextToken());

        // 경우의수 구하기
        solve(0);

        // 출력
        StringBuilder sb = new StringBuilder();
        for (String s : answer) {
            sb.append(s + "\n");
        }

        System.out.println(sb);
    }
}
