package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 String을 char배열로 바꾼다음
 char배열을 정렬(오름차순)해주고
 SringBuilder로 해당 문자열을 reverse해주면 내림차순이 된다
 */
public class Main1427 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1427.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s = br.readLine();

        char[] cArr = s.toCharArray();
        Arrays.sort(cArr);
        sb.append(String.valueOf(cArr));
        sb.reverse();

        System.out.println(sb);
    }
}
