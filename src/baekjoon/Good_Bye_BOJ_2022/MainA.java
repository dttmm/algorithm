package baekjoon.Good_Bye_BOJ_2022;

import java.io.*;

/**
 N이 과잉수인지 아닌지 판단하는 함수(checkOver)를 통해
 먼저 N이 과잉수인지 판단을 하고
 N의 약수들도 checkOver를 돌림
 checkOver를 돌릴 때마다 해당 숫자의 과잉수 여부를 d배열에 저장하여 중복을 방지함

 근데 맞긴 맞았는데
 이거 이렇게 푸는거 맞아??
 */
public class MainA {
    static int[] d;

    // 과잉수 인지 감시
    static boolean checkOver(int N) {
        if (d[N] == 1) return true;
        else if (d[N] == -1) return false;
        else {
            int sqrt = (int) Math.sqrt(N);
            int sum = 0;
            for (int i = 2; i <= sqrt; i++) {
                if (N % i == 0) {
                    if (i == N / i) {
                        sum += i;
                    } else {
                        sum += i;
                        sum += N / i;
                    }

                }
            }
            sum += 1;
            d[N] = sum > N ? 1 : -1;
            return sum > N;
        }
    }

    // N의 약수들이 모두 과잉수가 아닌지 검사
    static boolean checkChild(int N) {
        int sqrt = (int) Math.sqrt(N);
        for (int i = 2; i <= sqrt; i++) {
            if (N % i == 0) {
                if (checkOver(i)) return false;
                if (checkOver(N / i)) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/Good_Bye_BOJ_2022/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        d = new int[5001];

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            if (checkOver(N) && checkChild(N)) bw.write("Good Bye\n");
            else bw.write("BOJ 2022\n");
        }
        bw.flush();
        bw.close();
    }
}
