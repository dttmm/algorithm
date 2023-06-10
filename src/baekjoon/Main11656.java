package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 설계 1분 구현 3분
 간단하게 문자열 뒤에서 부터 시작해서
 앞에 글자 하나씩 추가해주면서 접미사 만들면 될 줄 알았는데
 +=로 추가하면 앞에 있는 글자가 뒤로가서 추가가 되구나
 그냥 편하고 빠르게 StringBuilder이용해서
 StringBuilder에서 한 글자씩 없애가면서 접미사 만들었음
 */
public class Main11656 {

    static String s;
    static String[] arr;    //  접미사 담을 배열

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11656.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        arr = new String[s.length()];

        // 접미사 만들기
        StringBuilder word = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            arr[i] = word.toString();
            word.deleteCharAt(0);
        }

        // 사전순으로 정렬
        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i] + "\n");
        }

        System.out.println(sb);
    }
}
