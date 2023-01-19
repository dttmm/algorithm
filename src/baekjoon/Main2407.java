package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 그냥 큰 수 다루는 문제인듯?
 직접 공식을 사용해서 구해도 되고
 찾아보니까 규칙을 이용해서 dp로도 구할 수 있는 듯
 */
public class Main2407 {

    static int N;
    static int M;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2407.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        BigInteger answer = new BigInteger("1");
        for (int i = 2; i <= N; i++) {
            answer = answer.multiply(new BigInteger(i + ""));
        }

        for (int i = 2; i <= M; i++) {
            answer = answer.divide(new BigInteger(i + ""));
        }

        for (int i = 2; i <= N - M; i++) {
            answer = answer.divide(new BigInteger(i + ""));
        }

        System.out.println(answer);
    }
}
