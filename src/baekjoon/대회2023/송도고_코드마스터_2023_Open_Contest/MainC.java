package baekjoon.대회2023.송도고_코드마스터_2023_Open_Contest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainC {

    static int N;
    static int[] count;
    static int[] tr;
    static int maxIndex;
    static int maxCount;

    static void solve(int k, int start) {
        if (k == 2) {
            int sum = 0;
            for (int n = 0; n < 1 << 5; n++) {
                if (((n & (1 << tr[0])) > 0) && ((n & (1 << tr[1])) > 0)) {
                    sum += count[n];
                }
            }
            if (sum > maxCount) {
                maxCount = sum;
                maxIndex = (1 << tr[0]) + (1 << tr[1]);
            }
        } else {
            for (int i = start; i < 5; i++) {
                tr[k] = i;
                solve(k + 1, i + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/송도고_코드마스터_2023_Open_Contest/C.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        count = new int[33];
        tr = new int[2];
        maxIndex = 32;
        maxCount = 0;

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            char[] cArr = new char[5];

            int index = 0;
            for (int j = 0; j < s.length(); j++) {
                if (j % 2 == 1) continue;
                char c = s.charAt(j);
                cArr[index] = c;
                index++;
            }

            s = String.valueOf(cArr);
            int n = Integer.parseInt(s, 2);
            count[n]++;
        }

        solve(0, 0);

        StringBuilder sb = new StringBuilder();
        sb.append(maxCount + "\n");

        if (maxIndex == 32) maxIndex = 3;

        String s = Integer.toString(maxIndex, 2);

        for (int i = s.length(); i < 5; i++) {
            sb.append(0 + " ");
        }
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i) + " ");
        }
        System.out.println(sb);
    }
}
