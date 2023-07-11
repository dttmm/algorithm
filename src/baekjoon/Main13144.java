package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 15분 구현 4분
 투포인터로 풀음
 부분 연속을 구할 때는 확실히 투포인터가 많이 쓰이는 구먼

 재까지의 숫자(R의 위치)를 포함 했을때
 가능한 연속 범위(L~R)를 구해줌
 R을 하나씩 이동하면서 R에 해당하는 숫자의 카운트를 하나 늘려주고
 해당 숫자의 카운트가 2가되어 중복이 발생하면
 L을 중복이 사라질 때까지 이동시키고
 R과 L 범위만큼 경우의 수를 더해줌

 호카케 그는 신이다
 */
public class Main13144 {

    static int N;
    static int[] arr;
    static int[] count;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/13144.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        count = new int[100001];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        int L = 0;
        int R = 0;
        long total = 0;
        while (R < N) {
            int nR = arr[R];
            count[nR]++;

            if (count[nR] == 2) {
                while (L < R) {
                    int nL = arr[L];
                    count[nL]--;
                    L++;

                    if (count[nL] == 1) break;
                }
            }

            total += R - L + 1;
            R++;
        }

        System.out.println(total);
    }
}
