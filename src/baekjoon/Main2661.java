package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 설계 1시간 구현 21분
 규칙을 찾아보려 했으나 규칙이 안보여서
 이거슨 완탐이밖에 길이 없다고 판단

 처음에는 자릿수를 하나씩 늘려가며
 나쁜 수열이 나올 경우 숫자를 +1하면서
 좋은 수열을 찾을 때까지 반복하려고 했는데
 +1하는 경우 해당 자릿수가 3을 넘어 오버플로우가 발생할 경우
 다음 자릿수를 +1해주어야 하는데
 다음 자릿수를 +1해주어야되는 상황이 연속적으로 발생하는 경우를
 처리를 할 수가 없어서 다른 방법을 찾아보다가
 알고리즘 분류 살짝 봤는데
 백트래킹 보고 뒤통수가 얼얼해짐

 와 어떻게 완탐할 생각하면서
 왜 백트래킹을 생각하지 못했을까..
 백트래킹 단어 보자마자 바로 설계 뚝딱뚝딱

 맨 뒤에 추가한 새로운 숫자와
 그 앞에 있는 숫자들을 x개씩 비교해가면서
 서로 일치하는 숫자들이 있으면 나쁜 수열이라고 판단
 처음에는 x의 증가량을 2배로 잡았다가 실패함
 2배로 잡을 이유가 없구나
 그냥 1씩 증가시키면서 다 비교를 해봐야 되는군하
 */
public class Main2661 {

    static int N;
    static boolean flag;    // 제일 작은 수 찾았는지 플래그
    static String answer;   // 정답

    // 좋은 수열인지 나쁜 수열인지 판단
    static boolean check(int k, String s) {
        // 맨 뒤에서부터 x개씩 비교해봄
        for (int x = 1; k - (2 * x) + 1 >= 0; x++) {
            // 비교했는데 둘이 일치하면 나쁜 수열
            if (s.substring(k - x + 1).equals(s.substring(k - (2 * x) + 1, k - x + 1))) return false;
        }
        // 좋은 수열인 경우
        return true;
    }

    // 백트래킹
    static void solve(int k, String s) {
        // 나쁜 수열인 경우
        if (!check(k, s)) return;

        // N자리 수인 경우
        if (k == N - 1) {
            flag = true;
            answer = s;
            return;
        }

        if (!flag) solve(k + 1, s + "1");
        if (!flag) solve(k + 1, s + "2");
        if (!flag) solve(k + 1, s + "3");
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2661.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        flag = false;

        solve(-1, "");
        System.out.println(answer);
    }
}