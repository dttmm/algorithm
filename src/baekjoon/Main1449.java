package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 계산하기 편하게 모든 길이에 두배씩 곱해주었음
 처음에는 구멍들만 쏙쏙 뽑아서 구멍을 막으려고 했는데
 그냥 완탐 돌리면서 구멍 찾아도
 N이 겨우 1000개라 시간 충분해 보였음

 완탐하면서 구멍 찾고
 구멍 찾으면 테이프 길이만큼 구멍 메꿔줌
 */
public class Main1449 {

    static final int MAX = 2001;
    static int N;
    static int L;
    static Set<Integer> hole;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1449.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken()) * 2;
        hole = new HashSet();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken()) * 2;
            hole.add(n);

            // 내 앞 뒤로 구멍 뚫기!
            if (n - 1 >= 0) hole.add(n - 1);
            if (n + 1 <= MAX) hole.add(n + 1);
        }

        int answer = 0;

        for (int i = 0; i < MAX; i++) {
            // 구멍이 아닌 경우 패쓰
            if (!hole.contains(i)) continue;

            hole.remove(i);
            answer++;
            // 해당 구멍 위치에서 붙어 테이프 길이만큼 구멍 메꾸기
            for (int j = 1; j <= L; j++) {
                int index = i + j;
                if (!hole.contains(index)) continue;

                hole.remove(index);
                if (hole.size() == 0) break;
            }

            if (hole.size() == 0) break;
        }

        System.out.println(answer);
    }
}
