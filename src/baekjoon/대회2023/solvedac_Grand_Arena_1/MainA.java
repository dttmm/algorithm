package baekjoon.대회2023.solvedac_Grand_Arena_1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 설계 0분 구현 1뷴
 자료구조
 양말이 처음 나오면 set에 추가해주고
 같은 양말이 한번 더 나오면 set에서 삭제해줌
 */
public class MainA {

    static int N = 5;
    static Set<Integer> set;

    // 양말 추가 or 삭제
    static void solve(int n) {
        // 이미 양말이 있는 경우
        if (set.contains(n)) set.remove(n);
        // 양말이 없는 경우
        else set.add(n);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/solvedac_Grand_Arena_1/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        set = new HashSet();

        // 입력 받기
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());

            // 양말 추가 or 삭제
            solve(n);
        }

        for (int n : set) {
            System.out.println(n);
        }
    }
}
