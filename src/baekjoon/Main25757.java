package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 설계 0분 구현 2분
 간단한 분기 문제
 참여자들 중복을 제거하고
 게임 타입에 따라 가능한 게임 수 출력
 */
public class Main25757 {

    static int N;
    static String type;
    static Set<String> set;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/25757.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        type = st.nextToken();
        set = new HashSet();

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            set.add(s);
        }

        if (type.equals("Y")) System.out.println(set.size());
        else if (type.equals("F")) System.out.println(set.size() / 2);
        else if (type.equals("O")) System.out.println(set.size() / 3);
    }
}
