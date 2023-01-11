package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 라빈 카프 + 배열을 이용한 스택으로 풀었음
 우선 폭발 문자열이 문자열안에 있는지 판단하기 위해서
 라빈 카프를 이용하였음
 해시 값을 비교하고
 해시값이 일치하면 다시한번 폭발 문자열과 문자 하나하나가 일치하는지 확인하고
 완전히 일치하면
 직접 만든 배열 스택에서
 폭발 문자열 길이 만큼 top을 감소시키고
 스택에 문자가 있다면
 해당 문자들로 해시를 다시 구해주었음

 문자열을 탐색하면서 매번 폭발 문자열과 같은 문자인지
 확인하는 것은 시간 초과가 날 것이라고 판단하여 라빈카프를 사용하였는데
 다른 풀이 보니까 그냥 스택만 이용해서
 폭발 문자열과 문자 하나하나가 일치하는지 일일이 확인했눼??
 시간 초과 안나눼??
 내 풀이는 344ms인데
 게다가 시간 차이도 Stack 자료구조를 이용하면 604ms로 1.7배
 StringBuilder를 스택처럼 이용하면 380ms 1.1배 밖에 차이 안나눼?
 아 일일이 확인해도
 1,000,000,000 * 36해도 3600만 밖에 안되는 군하
 */
public class Main9935 {

    static String s;
    static String target;       // 폭발 문자열
    static long hash;
    static long target_hash;    // 폭발 문자열의 해시
    static char[] stack;
    static int top;
    static int pow;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/9935.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        target = br.readLine();
        hash = 0;
        target_hash = 0;
        stack = new char[s.length() + 1];
        top = -1;
        pow = 0;

        // // 폭발 문자열의 hash 구하기
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            target_hash = target_hash * 2 + c;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            stack[++top] = c;

            // 지금까지 입력받은 문자의 해시 구하기 (라빈 카프)
            if (pow < target.length()) {
                hash = hash * 2 + c;
                pow++;
            } else {
                hash = (hash - (long) Math.pow(2, pow - 1) * stack[top - pow]) * 2 + c;
            }

            // 해시 일치하면 다시 한번 문자 일일이 확인 (해시 충돌 처리)
            if (hash != target_hash) continue;
            boolean flag = true;
            for (int j = 0; j < target.length(); j++) {
                if (target.charAt(j) != stack[top - target.length() + 1 + j]) {
                    flag = false;
                    break;
                }
            }

            // 폭발 문자열일 경우
            if (!flag) continue;
            top -= target.length(); // 스택포인터 위치 재조정
            hash = 0;
            pow = 0;

            // 스택에 남은 문자들의 해시값 구함
            for (int j = 0; j < target.length(); j++) {
                if (top - target.length() + 1 + j < 0) continue;
                hash = hash * 2 + stack[top - target.length() + 1 + j];
                pow++;
            }
        }

        String answer = "";
        if (top == -1) {
            answer = "FRULA";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <= top; i++) {
                sb.append(stack[i]);
            }
            answer = sb.toString();
        }

        System.out.println(answer);
    }
}
