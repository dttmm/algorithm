package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 제곱을 엄청 많이 한다??? -> 바로 그냥 분할정복 뚝딱뚝딱
 나머지 연산을 어디에다 추가 해줘야 할지만 잘 고려해주면 됨

 if (k == 1) 일때 나머지연산을 안해 주어서 처음 틀렸음
 입력이 10 1 10인 경우 출력이 10이 나오게 되어서 오답
 나머지 연산을 해주어야 됨
 */
public class Main1629 {

    static long A;
    static long B;
    static long C;

    static long solve(long k) {
        if (k == 1) return A % C;

        long half = solve(k / 2);
        if (k % 2 == 0) return (half * half) % C;
        else return ((half * half) % C * A) % C;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1629.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());

        System.out.println(solve(B));
    }
}
