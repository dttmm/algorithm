package algorithm.day13string;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 12분 10초
public class Solution1221 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day13_1221.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            HashMap<String, Integer> map = new HashMap<>();
            map.put("ZRO", 0);
            map.put("ONE", 1);
            map.put("TWO", 2);
            map.put("THR", 3);
            map.put("FOR", 4);
            map.put("FIV", 5);
            map.put("SIX", 6);
            map.put("SVN", 7);
            map.put("EGT", 8);
            map.put("NIN", 9);
            String[] sArr = {"ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"};

            StringTokenizer st = new StringTokenizer(br.readLine());
            String t = st.nextToken();
            int N = Integer.parseInt(st.nextToken());

            int[] count = new int[10];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                count[map.get(st.nextToken())]++;
            }
            System.out.println(t);
            for (int i = 0; i < 10; i++) {
                int k = count[i];
                while (k != 0) {
                    System.out.print(sArr[i] + " ");
                    k--;
                }
            }
            System.out.println();
        }
    }
}