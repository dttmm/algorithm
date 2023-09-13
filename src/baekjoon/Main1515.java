package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 설계 8분 구현 9분 디버깅 3분
 링크드리스트
 1부터 3000까지의 수를 하나씩 쪼개서
 링크드 리스트로 연결함
 그리고 입력받은 target과 리스트에 있는 수가
 하나씩 일치하는지 확인하여 target 검사 완료 하면
 리스트에 쪼개기 전의 숫자를 정답으로 처리함

 틀림
 완탐
 정답으로 가능한 수가 3000이 최대가 아니라
 입력으로 주어지는 target의 길이가 최대 3000이라는 것이었네
 그냥 완탐이었네
 1부터 증가시킬 i와
 target에 있는 수를 하나하나 검사하면서
 i를 증가시킴
 target에 검사 완료하면 i를 정답으로 처리
 */
public class Main1515 {

    static String target;   // 입력
    static int answer;

    // N 찾기
    static int solve() {
        int cursor = 0; // target을 가리키는 인덱스
        int i = 1;      // N을 찾기 위해 하나씩 비교할 수
        while (true) {
            String s = String.valueOf(i);
            // 수 i의 길이만큼 반복
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                // target에 있는 수와 i가 가지고 있는 수가 일치 하지 않는 경우 -> 다음 i의 수 탐색
                if (c != target.charAt(cursor)) continue;

                // 일치하는 경우
                cursor++;   // 다음 target의 수 탐색

                // target 다 검사한 경우
                if (cursor == target.length()) break;
            }
            if (cursor == target.length()) break;
            i++;
        }
        return i;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1515.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = br.readLine();
        answer = 0;

        int ret = solve();

        System.out.println(ret);
    }
}
