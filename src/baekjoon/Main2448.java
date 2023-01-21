package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 재귀 연습겸 풀어봄
 재귀보다는 뭔가 문자열 연습한 느낌..
 최소 단위가 d[0]으로
   *
  * *
 *****
 이 모양을 최소 모양으로 잡고
 재귀를 돌리면서 d[k-1]을 윗줄에 쌓고
 아랫줄에 d[k-1]과 blank[k-1] 과 d[k-1]을 이어 붙였음

 이어 붙이는 과정을 생각하기가 까다로웠음
 String을 \n 기준으로 분해해서 d랑 blank랑 이어붙였음

 마지막에 d[k]를 뽑아서
 각 줄마다 앞 뒤로 공백 추가해 주는 작업 했음
 공백을 앞 뒤로 추가 해주어야됨
 앞에만 추가했더니 출력 형식 잘못 뜸
 */
public class Main2448 {

    static int N;
    static StringBuilder[] d;       // k까지의 트리 저장
    static StringBuilder[] blank;   // 공백으로된 거꾸로 세모 저장
    static StringBuilder[] blank_row;   // 각 줄마다 추가할 앞 뒤 공백

    // java8에서는 String.repeat 지원 안돼서 만들어줌
    static StringBuilder repeatBlank(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(" ");
        }
        return sb;
    }

    // blank 반환
    static StringBuilder getBlank(int k) {
        if (blank[k] == null) {
            StringBuilder sb = new StringBuilder();
            int limit = 3 * (int) Math.pow(2, k);
            for (int i = limit; i > 0; i--) {

                sb.append(repeatBlank(i * 2 - 1));

                sb.append("\n");
            }
            blank[k] = sb;
        }
        return blank[k];
    }

    // 트리 아랫줄에 d[k-1]과 blank[k-1]을 합치는 작업
    static StringBuilder sum(StringBuilder sb1, StringBuilder sb2) {
        String[] ss1 = sb1.toString().split("\n");
        String[] ss2 = sb2.toString().split("\n");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ss1.length; i++) {
            sb.append(ss1[i]);
            sb.append(ss2[i]);
            sb.append(ss1[i]);
            sb.append("\n");
        }
        return sb;
    }

    static StringBuilder solve(int k) {
        if (k == 0) {
            if (d[0] == null) {
                d[0] = new StringBuilder("*\n* *\n*****\n");
            }
            return d[0];
        }

        // 이전 트리가지고 트리 합치기
        if (d[k] == null) {
            StringBuilder sb = new StringBuilder(solve(k - 1));
            sb.append(sum(solve(k - 1), getBlank(k - 1)));
            d[k] = sb;
        }
        return d[k];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2448.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 3*2^k에서 몇 k인지 구하기
        int k = 0;
        N /= 3;
        while (N != 1) {
            N /= 2;
            k++;
        }
        d = new StringBuilder[k + 1];
        blank = new StringBuilder[k + 1];

        // 트리 만들기
        solve(k);

        // 각 줄에 앞뒤로 공백 추가하기
        StringBuffer sb = new StringBuffer();
        String[] s = d[k].toString().split("\n");
        blank_row = new StringBuilder[s.length];
        for (int i = 0; i < s.length; i++) {
            blank_row[i] = repeatBlank(s.length - i - 1);
            sb.append(blank_row[i]);
            sb.append(s[i]);
            sb.append(blank_row[i]);

            // 맨 마지막줄에 공백 제거
            if (i == s.length - 1) continue;
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
