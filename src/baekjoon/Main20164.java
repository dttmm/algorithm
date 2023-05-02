package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 아이디어 19분 | 구현 36분
 * 아잉
 * 문제를 제대로 안읽었쏘잉
 * 최대값만 구하면 되는줄 알고 코드 다 짰는데
 * 최소값도 구해야 됐었네
 * <p>
 * 숫자의 길이가 3이상일 때는
 * 조합을 통해 숫자를 자르고
 * 자른 숫자를 합쳐서
 * 해당 숫자로 구할 수 있는 홀수의 개수를 구함
 * <p>
 * 조합을 통해 만든 숫자로 구할 수 있는 홀수의 개수의 최대 최소 값을 각각 구함
 * 생각해보니 숫자의 길이가 2이하이면 최대나 최소나 값이 같겠군하
 * <p>
 * 처음에는 최대값만 리턴해주도록 solve, cut 함수 짰었는데
 * 최소값도 같이 리턴할수 있도록 하나의 배열에담아 최소 최대값 리턴해줌
 */
public class Main20164 {

    static String N;

    // 숫자 s로 구할 수 있는 홀수의 최대 최소
    static int[] solve(String s) {
        int[] minMax = new int[2];  // 0: 해당 숫자로 구할 수 있는 홀수의 최소값, 1: 최대값

        // 해당 숫자가 가지고 있는 홀수의 개수 세줌
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int n = c - '0';
            if (n % 2 == 1) {
                minMax[0]++;
                minMax[1]++;
            }
        }

        // 숫자의 길이가 3 이상일 때
        if (s.length() >= 3) {
            // 숫자를 모든 경우로 잘랐을 때의 최대 최소값을 구함
            int[] result = cut(0, 1, s, new int[2]);
            minMax[0] += result[0];
            minMax[1] += result[1];
        }
        // 숫자의 길이가 2일 때
        else if (s.length() == 2) {
            int[] result = solve(String.valueOf((s.charAt(0) - '0') + (s.charAt(1) - '0')));
            minMax[0] += result[0];
            minMax[1] += result[1];
        }

        // 해당 숫자의 최대 최소값 리턴
        return minMax;
    }

    // 조합으로 숫자 3등분함
    static int[] cut(int k, int start, String s, int[] tr) {
        if (k == 2) {
            int sum = 0;
            sum += Integer.parseInt(s.substring(0, tr[0]));
            sum += Integer.parseInt(s.substring(tr[0], tr[1]));
            sum += Integer.parseInt(s.substring(tr[1]));

            // 자른 숫자의 합으로 구할수 있는 최대 최소 구함
            return solve(String.valueOf(sum));
        } else {
            int[] minMax = new int[2];  // 0: 최소, 1: 최대
            minMax[0] = Integer.MAX_VALUE;

            for (int i = start; i < s.length(); i++) {
                tr[k] = i;

                int[] result = cut(k + 1, i + 1, s, tr);
                minMax[0] = Math.min(minMax[0], result[0]);
                minMax[1] = Math.max(minMax[1], result[1]);
            }

            // 숫자 s를 모든 경우의 수로 잘랐을 때의 최대 최소 리턴
            return minMax;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/20164.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = br.readLine();

        int[] answer = solve(N);

        System.out.println(answer[0] + " " + answer[1]);
    }
}
