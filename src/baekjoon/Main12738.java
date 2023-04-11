package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 스택을 배열로 구현하고 자바에서 제공하는 이분탐색 이용함
 숫자(n) 입력을 받으면서
 해당 숫자(n)가 스택에 들어갈 자리(index)를 이분탐색으로 구해줌
 그리고 해당 자리(index)에 해당 숫자(n)를 넣어주고
 top의 최대값 갱신해감

 binarySearch 실험하는데 시간 많이 썼다
 범위를 넣을 때는 시작인덱스 ~ 마지막인덱스+1 해줘야 되는 군하
 */
public class Main12738 {

    final static int limit = 1000001;
    static int N;
    static int[] stack;
    static int top;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/12738.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stack = new int[limit];
        top = -1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());

            // 스택이 빈 경우
            if (top == -1) stack[++top] = n;
            else {
                int index = Arrays.binarySearch(stack, 0, top + 1, n);

                // n이 스택에 없는 경우 -> n이 들어갈 자리 구하기
                if (index < 0) index = (index + 1) * -1;

                stack[index] = n;
                top = Math.max(top, index);
            }
        }

        System.out.println(top + 1);
    }
}
