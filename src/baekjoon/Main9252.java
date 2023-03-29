package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 LCS 길이구하는 것은 간단
 경로 구하는 것이 헷갈렸음
 d배열의 끝에서 시작해서
 위쪽 왼쪽을 탐색하면서
 자신과 값이 같은 경우 이동하고
 값이 같은 경우가 없는 경우
 경로에 추가하고 대각선으로 점프!

 처음에는 위쪽만 검사했는데
 문자열 순서 바꾸니까 이상한 답 나오더라
 왼쪽에서 온 경우도 고려해서 왼쪽도 검사해 줘야됨
 */
public class Main9252 {

    static String s1;
    static String s2;
    static int[][] d;
    static char[] answer;
    static int index;

    // LCS 길이 구하기
    static int getLCSLength() {
        for (int j = 1; j <= s2.length(); j++) {
            char c1 = s2.charAt(j - 1);
            for (int i = 1; i <= s1.length(); i++) {
                char c2 = s1.charAt(i - 1);

                if (c1 == c2) {
                    d[i][j] = d[i - 1][j - 1] + 1;
                } else {
                    d[i][j] = Math.max(d[i - 1][j], d[i][j - 1]);
                }
            }
        }

        return d[s1.length()][s2.length()];
    }

    // LCS 경로 구하기
    static void solve() {
        Queue<Integer> queue_i = new LinkedList();
        Queue<Integer> queue_j = new LinkedList();
        queue_i.add(s1.length());
        queue_j.add(s2.length());

        while (!queue_i.isEmpty()) {
            int i = queue_i.poll();
            int j = queue_j.poll();
            int n = d[i][j];
            if (n == 0) return;


            // 위쪽 검사
            if (d[i - 1][j] == n) {
                queue_i.add(i - 1);
                queue_j.add(j);
            }
            // 왼쪽 검사
            else if (d[i][j - 1] == n) {
                queue_i.add(i);
                queue_j.add(j - 1);
            }
            // 대각선 점프
            else {
                answer[index--] = s1.charAt(i - 1);
                queue_i.add(i - 1);
                queue_j.add(j - 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/9252.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s1 = br.readLine();
        s2 = br.readLine();
        d = new int[s1.length() + 1][s2.length() + 1];

        int length = getLCSLength();

        answer = new char[length];
        index = length - 1;
        solve();

        StringBuilder sb = new StringBuilder();
        sb.append(length);

        if (length != 0) {
            sb.append("\n");
            for (int i = 0; i < length; i++) {
                sb.append(answer[i]);
            }
        }

        System.out.println(sb);
    }
}
