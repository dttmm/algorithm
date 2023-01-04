package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 시간복잡도 안에 해결하기 위해 먼저 문자열 알고리즘을 공부함
 KMP, 라빈카프 공부함

 먼저 라빈카프를 이용하여 문제를 풀었는데
 N이 커질수록 pow값이 기하 급수적으로 커져버려서
 오버플로우를 무시할 수준을 넘어 해시값이 이상해지는 현상 발생

 할수없이 KMP 익힌다음에 KMP로 풀었음
 다만, 문자열에서 패턴을 찾았을 때의 처리를 잘 못해주어서 헤멨음
 패턴 찾았을 때, j=0으로 하고 i를 (패턴 길이+1)만큼 빼서 다음 문자열부터 탐색할 수 있게 했는데
 이렇게하면 시간복잡도가 그대로 O(NM) 최악으로 나와버림
 대신 그냥 j=d[j]로 하면 이미 앞에서 일치하는 것들은 스킵을 할 수 있게 됨
 */
public class Main5525_2 {

    static int N;
    static int M;
    static int d[];

    static void findPattern(String s) {
        int j = 0;
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = d[j - 1];
            }

            if (s.charAt(i) == s.charAt(j)) {
                j++;
                d[i] = j;
            }
        }
    }

    static int KMP(String s, String pattern) {
        int answer = 0;
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != pattern.charAt(j)) {
                j = d[j - 1];
            }

            if (s.charAt(i) == pattern.charAt(j)) {
                if (j == pattern.length() - 1) {
                    answer++;
                    j = d[j];
                } else {
                    j++;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/5525.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        sb.append("I");
        for (int i = 0; i < N; i++) {
            sb.append("OI");
        }

        String target = sb.toString();
        String s = br.readLine();
        d = new int[target.length()];

        findPattern(target);
        int answer = KMP(s, target);

        System.out.println(answer);
    }
}
