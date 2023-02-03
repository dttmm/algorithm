package softeer.lv2;

import java.util.*;
import java.io.*;

/**
 간단한 구현 문제
 첫 입력이 1인지 8인지 구분을 하고
 그에따라 ascending인지 descending인지 판별함
 */
public class Main_003_8단_변속기 {

    static int[] arr;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[8];
        for (int i = 0; i < 8; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        String type = "";
        int pre = -1;
        for (int i = 0; i < 8; i++) {
            int n = arr[i];
            if (i == 0) {
                if (n == 1) type = "ascending";
                else if (n == 8) type = "descending";
                else {
                    type = "mixed";
                    break;
                }

                pre = n;
                continue;
            }

            if (type.equals("ascending")) {
                if (n - pre != 1) {
                    type = "mixed";
                    break;
                }
            } else if (type.equals("descending")) {
                if (pre - n != 1) {
                    type = "mixed";
                    break;
                }
            }

            pre = n;
        }

        System.out.println(type);
    }
}
