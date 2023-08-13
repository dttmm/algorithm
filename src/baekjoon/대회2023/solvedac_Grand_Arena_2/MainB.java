package baekjoon.대회2023.solvedac_Grand_Arena_2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 구현 7분
 그리디
 연속된 4개 숫자 중에는 3의 배수가 최대 2개 들어갈 수 있고
 5의 배수가 최대 1개 들어갈 수 있으므로
 3의 배수가 아니면서 5의 배수가 아닌 수가 무조건 하나 이상 존재하게됨

 그래서 숫자인 녀석을 찾아서
 해당 숫자가 4개 숫자 중 몇 번째 숫자인지를 알아내서
 5번째 숫자가 무엇인지 찾음

 5번째 숫자가 3의 배수인지 5의 배수인지 검사함
 */
public class MainB {

    static int N;

    // 3의 배수인지 5의 배수인지 검사
    static String solve(int n) {
        if (n % 3 == 0 && n % 5 == 0) return "FizzBuzz";
        if (n % 3 == 0) return "Fizz";
        if (n % 5 == 0) return "Buzz";
        return String.valueOf(n);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/solvedac_Grand_Arena_2/B.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = 3;

        int index = -1; // 숫자인 녀석이 몇 번째 수인지
        int num = -1;   // 숫자인 녀석의 수
        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            // 숫자 찾기
            try {
                int n = Integer.parseInt(s);
                num = n;
                index = i;
                break;
            } catch (Exception e) {

            }
        }

        // 5번째 수
        int n = (3 - index) + num;
        String result = solve(n);
        System.out.println(result);
    }
}
