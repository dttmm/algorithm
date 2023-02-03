package softeer.lv2;

import java.util.*;
import java.io.*;

/**
 간단한 규칙 문제
 주언진 입력 N만큼 한 면의 점의 개수를 2n-1씩 늘려가면 됨
 마지막에 한 면의 점의 개수를 제곱하여 출력
 */
public class Main_005_지도_자동_구축 {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int n = 2;
        for (int i = 0; i < N; i++) {
            n = 2 * n - 1;
        }

        System.out.println(n * n);
    }
}
