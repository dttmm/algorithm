package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 설계 10분 구현 2분
 아이디어
 입력을 하나씩 받으면서
 조건을 분기함
 1. 레이저를 만났을 때 -> 가지고 있는 막대기를 잘라줌
 2. 막대기가 추가되었을 때 -> 막대기 개수 증가
 3. 막대기 끝에 도달하였을 때 -> 자른개수 하나 증가시키고 막대기 없앰
 */
public class Main10799 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10799.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        char pre = '\0';    // 이전 입력
        int answer = 0;     // 잘려진 막대기 개수
        int count = 0;      // 현재 가지고 있는 막대기 개수
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);

            // 레이저를 만났을 때
            if (pre == '(' && cur == ')') answer += count;
            // 막대기가 추가되었을 때
            else if (pre == '(' && cur == '(') count++;
            // 막대기 끝에 도달하였을 때
            else if (pre == ')' && cur == ')') {
                answer++;
                count--;
            }

            pre = cur;
        }

        System.out.println(answer);
    }
}
