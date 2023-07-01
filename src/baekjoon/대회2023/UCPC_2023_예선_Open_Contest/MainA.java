package baekjoon.대회2023.UCPC_2023_예선_Open_Contest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 설계 0분 구현 1분
 입력 받은 방향 지시를 모두 합쳐서
 하나의 방향으로 구함
 */
public class MainA {

    static String[] arr = {"N", "E", "S", "W"};

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/UCPC_2023_예선_Open_Contest/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int total = 0;
        for (int i = 0; i < 10; i++) {
            int n = Integer.parseInt(br.readLine());
            total += n;
        }

        total %= 4;
        System.out.println(arr[total]);
    }
}
