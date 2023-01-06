package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 두번째 방법
 직접 이진트리를 만들어서 풀지 않고
 입력을 배열에 저장하고
 배열 안에서 재귀를 이용하여 풀었음

 기준점을 start로 두고
 순차탐색 하면서
 start보다 커지는 index를 찾아서
 start+1 ~ <index 구간은 start보다 작은 값들이고
 index ~ <end 구간은 start보다 큰 값 들이므로
 해당 두 구간을 재귀로 돌렸음

 후위순회로 출력해야 되니까
 작은 구간먼저 돌리고
 큰 구간 돌리고
 자기 자신 출력해주었음

 직접 트리를 만들고 트리를 후위순회했던
 첫번째 방법보다 시간 절반으로 단축할 수 있었음
 */
public class Main5639_2 {

    static StringBuilder sb;
    static int[] arr;

    static void solve(int start, int end) {
        if (start >= end) return;

        int index = start + 1;
        while (index < end) {
            if (arr[start] < arr[index]) break;

            index++;
        }

        solve(start + 1, index);
        solve(index, end);
        sb.append(arr[start] + "\n");
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/5639.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        arr = new int[100001];

        int index = 0;
        while (true) {
            String s = br.readLine();
            if (s == null) break;

            int n = Integer.parseInt(s);
            arr[index++] = n;
        }

        solve(0, index);

        System.out.println(sb);
    }
}
