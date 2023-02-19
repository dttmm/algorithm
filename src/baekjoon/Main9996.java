package baekjoon;

import java.io.*;

/**
 일단 문제 제목에 어그로 끌려서 풀어봄
 문제를 너무 단순하게 접근했음
 처음에는 그냥 양끝 char만 비교했는데
 생각해보니 꼭 * 양 옆에 단어가 하나씩만 있을 필요는 없었음
 ab*cd이런 형태도 신경 써줘야돼서
 양끝에서부터 문자열을 비교해주었음

 근데 또 예외가 발생
 aa*aa인 경우
 aa도 정답으로 처리해버려졌음
 최소 aaaa부터 정답으로 처리하기 위해
 앞에서부터 문자열 탐색했을 때랑
 뒤에서부터 문자열 탐색했을 때랑
 마지막 탐색 인덱스 비교해서
 앞에서부터 문자열 탐색했을 때의 인덱스가 뒤에서부터 문자열 탐색했을 때의 인덱스보다 크거나 같으면 땡 처리함

 아 split에서 *로 나누는거 좀 헤멨음
 */
public class Main9996 {

    static int N;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/9996.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        String[] pattern = br.readLine().split("\\*");


        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            boolean flag = true;
            int index = 0;  // 앞에서부터 문자열 탐색했을 때의 마지막 인덱스

            // 앞 패턴과 파일 이름 앞에서 부터 검사
            for (int j = 0; j < pattern[0].length(); j++) {
                char pattern_c = pattern[0].charAt(j);
                char name_c = name.charAt(j);

                // 하나라도 일치하지 않는 경우
                if (pattern_c != name_c) {
                    flag = false;
                    break;
                }

                index = j;
            }

            // 뒤 패턴과 파일 이름 뒤에서 부터 검사
            for (int j = 0; j < pattern[1].length(); j++) {
                // 앞에서 이미 일치하지 않았다면 패쓰
                if (!flag) break;

                char pattern_c = pattern[1].charAt(pattern[1].length() - 1 - j);
                char name_c = name.charAt(name.length() - 1 - j);

                // 하나라도 일치하지 않는 경우
                if (pattern_c != name_c) {
                    flag = false;
                    break;
                }

                if (j != pattern[1].length() - 1) continue;
                // 앞에서부터 문자열 탐색했을 때의 인덱스가 뒤에서부터 문자열 탐색했을 때의 인덱스 비교
                if (index >= name.length() - 1 - j) flag = false;
            }

            if (flag) bw.write("DA\n");
            else bw.write("NE\n");
        }

        bw.flush();
        bw.close();
    }
}
