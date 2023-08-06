package baekjoon.대회2023.solvedac_Grand_Arena_1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 설계 6분 구현 5분
 구현
 ?의 위치를 파악하고
 ?의 왼쪽 단어의 첫번째 글자(left)와
 ?의 오른쪽 단어의 마지막 글자(right)를 찾음

 ?가 맨앞 글자라면 오른쪽 단어만 찾아주고
 ?가 맨뒤 글자라면 왼쪽 단어만 찾아줌

 후보로 입력받은 단어들 중에
 해당 단어의 첫번째 글자와 마지막 글자가
 각각 left, right와 일치하는지 검사하고
 해시로 이미 등장한 단어인지도 검사해줌

 틀림
 right를 설정하는 인덱스 범위 틀림
 N-1까지 해야되는데 N까지로 검사함
 */
public class MainB {

    static int N;
    static int M;
    static String[] words;  // 단어들
    static int targetIndex; // words에서 ?의 위치
    static char right;      // ?의 오른쪽 단어의 첫번째 글자
    static char left;       // ?의 왼쪽 단어의 마지막 글자
    static Set<String> set; // 이미 나온 단어 중복 검사

    // ? 앞 뒤로 오는 글자 찾기
    static void solve() {
        // 오른쪽 단어가 있는 경우
        if (targetIndex < N - 1) {
            String rightWord = words[targetIndex + 1];
            right = rightWord.charAt(0);
        }
        // 왼쪽 단어가 있는 경우
        if (targetIndex > 0) {
            String leftWord = words[targetIndex - 1];
            left = leftWord.charAt(leftWord.length() - 1);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/solvedac_Grand_Arena_1/B.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        words = new String[N];
        targetIndex = 0;
        right = '\0';
        left = '\0';
        set = new HashSet();

        // 입력 받기
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            words[i] = word;
            set.add(word);

            // ?가 있는 위치 찾기
            if (word.equals("?")) targetIndex = i;
        }

        // ? 앞 뒤로 오는 글자 찾기
        solve();

        M = Integer.parseInt(br.readLine());
        String answer = "";
        for (int i = 0; i < M; i++) {
            String word = br.readLine();

            // 이미 나온 단어라면 패쓰
            if (set.contains(word)) continue;

            // ?의 왼쪽에 오는 글자 확인
            if (left != '\0') {
                if (word.charAt(0) != left) continue;
            }

            // ?의 오른쪽에 오는 글자 확인
            if (right != '\0') {
                if (word.charAt(word.length() - 1) != right) continue;
            }

            answer = word;
            break;
        }

        System.out.println(answer);
    }
}
