package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 설계 2분 구현 4분 디버깅 4분
 기준이 되는 첫번째 단어의 각 알파벳 개수를 배열(arr)에 저장해두고
 나머지 단어들도 각 알파벳 개수를 배열(brr)에 저장해두고
 두 배열을 돌면서 각 알파벳 개수의 차이의 합(diff)을 계산
 diff가 1이라면 알파벳을 하나 추가하거나 삭제할 수 있는 비슷한 단어라고 판별함

 근데 AAB와 AAC는 diff가 2인데도 C를 B로 바꾸면 비슷한 단어가 되는 예외 발생
 diff가 2일때도 비슷한 단어가 되는 경우는 두 문자의 길이가 같을 때 이므로 조건 추가해줌
 */
public class Main2607 {

    static int N;
    static int[] arr;
    static String standard; // 기준이 되는 첫 번째 단어

    // 첫 번째단어와 다른 단어의 알파벳 차이 개수 구하기
    static int solve(int[] brr) {
        int diff = 0;
        for (int i = 0; i < 26; i++) {
            diff += Math.abs(arr[i] - brr[i]);
        }
        return diff;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2607.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[26];
        int answer = 0;

        // 입력 받기
        for (int i = 0; i < N; i++) {
            // 첫 번째 단어 입력 받기
            if (i == 0) {
                standard = br.readLine();
                for (int j = 0; j < standard.length(); j++) {
                    char c = standard.charAt(j);
                    arr[c - 'A']++;
                }
                continue;
            }

            // 나머지 단어들 입력 받기
            int[] brr = new int[26];
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                brr[c - 'A']++;
            }

            int diff = solve(brr);

            // 비슷한 단어인 경우
            if (diff <= 1) answer++;
            else if (diff == 2 && standard.length() == s.length()) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
