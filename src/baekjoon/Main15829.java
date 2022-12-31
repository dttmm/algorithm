package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 1트때는 r을 곱해줄 때마다 모듈러 연산을 안해주어서 틀렸음
 2트때는 r을 곱해줄 때마다도 모듈러 연산을 해주었음
 그리고 당연히 hash에 누적 값을 더할 때마다도 모듈러 연산을 해주었음
 근데도 L이 커지면 틀리는 거임
 도저히 아무리봐도 틀릴 이유가 없는데 틀리길래 결국 정답 봄
 hash에서 모듈러 연산할 때 한가지 놓친 점이 있었음
 모듈러 연산을 다음과 같이 했는데
 hash += (n * rr) % M;
 이렇게 하면 더하고난 hash가 M보다 커질 수 있다는 점을 간과했음
 hash에 값을 더하고, 더하고 난 hash를 모듈러연산 해주어야했음
 */
public class Main15829 {

    final static long r = 31;
    final static long M = 1234567891;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/15829.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());
        String s = br.readLine();

        long hash = 0;
        long rr = 1;
        for (int i = 0; i < L; i++) {
            int n = s.charAt(i) - 'a' + 1;

            hash = (hash + (n * rr)) % M;
            rr = (rr * r) % M;
        }
        System.out.println(hash);
    }
}
