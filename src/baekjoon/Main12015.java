package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 O(n^2)으로는 풀 수가 없으니
 O(logn)으로 풀 수 있는 방법을 찾으려고 함
 O(logn)이면 분할정복이나 이분탐색이 있을 건데
 도저히 아이디어가 안 떠오름

 그래서 그냥 우선순위를 제어해서
 나보다 작은 숫자들 중에
 현재까지 길이가 제일 큰 놈을 고르려고 했지만
 동시에 우선순위 지정할 수 가 없더라

 결국 풀이 봤는데
 그냥 풀이를 알고 있어야 풀 수 있넹..

 처음에는 직접 이진탐색 구현해서 풀었다가
 자바에서 제공하는 binarySearch 사용해서 풀었다가
 배열 말고 Stack 자료구조 사용해서 풀어봄

 배열을 사용했을 때가 480ms
 스택 자료구조 사용했을 때가 972ms로
 배열이 두배 가량 더 빠름
 */
public class Main12015 {

    static int N;
    static int[] arr;
    static int top;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/12015.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[1000001];
        top = -1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());

            if (top == -1) {
                arr[++top] = n;
                continue;
            }

            if (n > arr[top]) arr[++top] = n;
            else if (n < arr[top]) {

                // 나보다 큰 놈들중에 최소값의 인덱스 반환
                int index = Arrays.binarySearch(arr, 0, top + 1, n);
                if (index < 0) index = (index + 1) * -1;
                arr[index] = n;
            }
        }

        System.out.println(top + 1);
    }
}
