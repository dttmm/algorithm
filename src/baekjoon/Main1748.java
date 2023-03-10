package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 은근 생각할 것이 많은 문제
 관건은 현재 자리수의 숫자의 개수를 구하는 것
 입력이 120이라면
 현재 3자리수 이므로
 3자리수들의 개수는 100~120까지 총 31개
 31개를 구하는 방법은
 현재 숫자에서 자기보다 낮은 자리수의 숫자 개수를 빼주면 됨
 120 - 90(두자리 수 개수) - 9(한자리 수 개수)
 그럼 3자리 숫자가 31개 있으므로
 31 * 3해주고
 나머지 자기보다 낮은 자리수의 숫자 개수들의 길이를 더해주면 됨
 (31 * 3) + (90 * 2) + (9 * 1)
 */
public class Main1748 {

    static int N;
    static int[] d; // i 자리수 숫자의 개수

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1748.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int size = s.length();
        N = Integer.parseInt(s);
        d = new int[size + 1];
        int answer = 0;

        d[1] = 9;   // 1자리수 숫자는 9개
        // 각 자리수 숫자 개수 구하기
        for (int i = 2; i <= size; i++) {
            d[i] = d[i - 1] * 10;
        }

        // 원래 숫자에서 자신보다 낮은 자리수 숫자 개수 빼기
        for (int i = 1; i < size; i++) {
            N -= d[i];
        }

        // size 자리수 숫자들의 개수들의 길이 구하기
        answer += N * size;

        // 자신보다 낮은 자리수 숫자들 길이 더해줌
        for (int i = 1; i < size; i++) {
            answer += (d[i] * i);
        }

        System.out.println(answer);
    }
}
