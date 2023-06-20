package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 설계 3분 구현 28분 디버깅 12+2분
 데이터의 형태를 보니
 비트를 이용하면 간단하게 풀 수 있어보여서
 비트로 접근
 각 입력을 비트로 생각하여 비트에 해당하는 숫자를 카운트하고
 5자리 비트 중에서 2개를 골라서
 고른 비트를 포함하는 숫자들의 개수를 이용하여 답을 찾음
 마지막에 예외케이스 처리를 안해줘서 틀렸음

 구현하는데 비트 은근 헷갈린다아ㅏ
 */
public class Main28238 {

    static int N;
    static int[] count;
    static int[] tr;
    static int maxIndex;    // 비트로 나타낸 특강 날짜
    static int maxCount;    // 그 때의 학생수 최대값

    // 5개 비트중에서 2개 고름
    static void solve(int k, int start) {
        if (k == 2) {
            int sum = 0;
            // 해당 비트(날짜)를 포함하고 있는 숫자(학생)들의 개수 구함
            for (int n = 0; n < 1 << 5; n++) {
                if (((n & (1 << tr[0])) > 0) && ((n & (1 << tr[1])) > 0)) {
                    sum += count[n];
                }
            }
            if (sum > maxCount) {
                maxCount = sum;
                maxIndex = (1 << tr[0]) + (1 << tr[1]);
            }
        } else {
            for (int i = start; i < 5; i++) {
                tr[k] = i;
                solve(k + 1, i + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/28238.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        count = new int[33];
        tr = new int[2];
        maxIndex = 32;
        maxCount = 0;

        // 입력 받기
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            char[] cArr = new char[5];

            int index = 0;
            for (int j = 0; j < s.length(); j++) {
                if (j % 2 == 1) continue;
                char c = s.charAt(j);
                cArr[index] = c;
                index++;
            }

            // 비트에 해당하는 숫자로 변환
            s = String.valueOf(cArr);
            int n = Integer.parseInt(s, 2);
            count[n]++;
        }

        solve(0, 0);

        StringBuilder sb = new StringBuilder();
        sb.append(maxCount + "\n");

        // 특강을 들을 수 있는 학생이 없는 경우 -> 아무 날짜로 정답 설정
        if (maxIndex == 32) maxIndex = 3;

        // 숫자 비트로 바꾸기
        String s = Integer.toString(maxIndex, 2);

        // 앞에 공백만큼 0 추가
        for (int i = s.length(); i < 5; i++) {
            sb.append(0 + " ");
        }
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i) + " ");
        }
        System.out.println(sb);
    }
}
