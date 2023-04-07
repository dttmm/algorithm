package baekjoon.대회2023.IamCoder_Qualification_Test_Open;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 그리리로 풀음
 각각의 옷걸이의 개수를 count 배열에 저장해주고
 상의 부터 1번 옷걸이에 걸고 남는 상의를 3번 옷걸이에 걸음
 상의 다 걸면 3번 옷걸이에 나머지 하의 걸음

 옷보다 해당 옷걸이가 많은 경우는 무조건 옷을 다 걸 수 없는 경우이므로 예외처리 해줌
 */
public class MainA {

    static int N;
    static int[] arr;
    static int[] count;
    static int[] cloth;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/IamCoder_Qualification_Test_Open/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        count = new int[3];
        cloth = new int[2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
            count[n - 1]++;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2; i++) {
            int n = Integer.parseInt(st.nextToken());
            cloth[i] = n;
        }

        // 옷을 다 걸 수 없는 경우
        if (cloth[0] - count[0] < 0 || cloth[1] - count[1] < 0) {
            System.out.println("NO");
            return;
        }

        // 남는 상의 3번 옷걸이에 걸음 <- 사실 이부분은 필요 없넹
        count[2] -= cloth[0] - count[0];
        cloth[0] -= count[0];
        StringBuilder sb = new StringBuilder("YES\n");
        for (int i = 0; i < N; i++) {
            int n = arr[i];

            if (n == 1) sb.append('U');
            else if (n == 2) sb.append('D');
            else {
                // 남는 상의 3번 옷걸이에 걸음
                if (cloth[0] > 0) {
                    sb.append('U');
                    cloth[0]--;
                } else sb.append('D');
            }
        }

        System.out.println(sb);
    }
}
