package softeer.lv2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 더러운 구현 문제
 각 숫자들의 전구 정보를 arr배열에 저장하고

 숫자 A가 가지고 있는 전광판의 정보를 d1에 저장하고
 B가 가지고 있는 정보는 d2에 저장하고
 마지막에 d1, d2를 비교하면서 차이나는 전구 개수를 세었음
 */
public class Main_008_전광판 {

    static int[][] arr;
    static int[][] d1;  // 숫자 A가 가지고 있는 전광판 정보 []: 몇번째 자리인지 [][]: 전관판 정보
    static int[][] d2;  // 숫자 B가 가지고 있는 전광판 정보

    // 두 숫자의 전구 차이 리턴
    static int compare(int[] arr1, int[] arr2) {
        int sum = 0;
        for (int i = 0; i < 7; i++) {
            if (arr1[i] != arr2[i]) sum++;
        }
        return sum;
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 각 숫자의 전구 정보 초기화
        arr = new int[10][7];
        arr[0] = new int[]{1, 1, 1, 0, 1, 1, 1};
        arr[1] = new int[]{0, 0, 1, 0, 0, 1, 0};
        arr[2] = new int[]{1, 0, 1, 1, 1, 0, 1};
        arr[3] = new int[]{1, 0, 1, 1, 0, 1, 1};
        arr[4] = new int[]{0, 1, 1, 1, 0, 1, 0};
        arr[5] = new int[]{1, 1, 0, 1, 0, 1, 1};
        arr[6] = new int[]{1, 1, 0, 1, 1, 1, 1};
        arr[7] = new int[]{1, 1, 1, 0, 0, 1, 0};
        arr[8] = new int[]{1, 1, 1, 1, 1, 1, 1};
        arr[9] = new int[]{1, 1, 1, 1, 0, 1, 1};

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            d1 = new int[5][7];
            d2 = new int[5][7];
            int sum = 0;

            st = new StringTokenizer(br.readLine());
            String n1 = st.nextToken();
            String n2 = st.nextToken();

            for (int i = 0; i < n1.length(); i++) {
                char c = n1.charAt(i);
                int n = c - '0';
                d1[5 - n1.length() + i] = arr[n];
            }

            for (int i = 0; i < n2.length(); i++) {
                char c = n2.charAt(i);
                int n = c - '0';
                d2[5 - n2.length() + i] = arr[n];
            }

            // 두 숫자의 전구 차이 계산
            for (int i = 0; i < 5; i++) {
                sum += compare(d1[i], d2[i]);
            }

            sb.append(sum + "\n");
        }
        System.out.println(sb);
    }
}
