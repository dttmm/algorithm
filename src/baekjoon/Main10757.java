package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main10757 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10757.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String a = st.nextToken();
        String b = st.nextToken();
        int[] arr = new int[a.length()];
        int[] brr = new int[b.length()];
        for (int i = a.length() - 1; i >= 0; i--) {
            arr[a.length() - 1 - i] = a.charAt(i) - '0';
        }
        for (int i = b.length() - 1; i >= 0; i--) {
            brr[b.length() - 1 - i] = b.charAt(i) - '0';
        }
        int max = Math.max(a.length(), b.length());
        int flag = 0;
        String total = "";
        for (int i = 0; i < max; i++) {
            int sum = 0;
            if (flag == 1) {
                sum += 1;
                flag = 0;
            }
            if (i < a.length()) {
                sum += arr[i];
            }
            if (i < b.length()) {
                sum += brr[i];
            }
            if (sum > 9) {
                flag = 1;
                sum -= 10;
            }
            total += sum;
            if (i == max - 1) {
                if (flag == 1) {
                    total += 1;
                }
            }
        }

        for (int i = total.length() - 1; i >= 0; i--) {
            System.out.print(total.charAt(i));
        }
    }
}
