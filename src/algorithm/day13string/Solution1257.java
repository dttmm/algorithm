package algorithm.day13string;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

//42분
public class Solution1257 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day13_1257.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int K = Integer.parseInt(br.readLine());
            String s = br.readLine();
            String[] arr = new String[s.length()];
            for (int i = 0; i < s.length(); i++) {
                arr[i] = s.substring(s.length() - 1 - i, s.length());
            }
            Arrays.sort(arr);
            int[] lcp = new int[s.length()];
            for (int i = 1; i < s.length(); i++) {
                String s1 = arr[i - 1];
                String s2 = arr[i];
                for (int j = 0; j < s1.length() && j < s2.length(); j++) {
                    if (s1.charAt(j) == s2.charAt(j)) {
                        lcp[i]++;
                    } else {
                        break;
                    }
                }
            }

            String answer = "none";
            for (int i = 0; i < arr.length; i++) {
                if (K - arr[i].length() + lcp[i] < 0) {
                    int j = 1;
                    while (K > 0) {
                        answer = arr[i].substring(0, lcp[i] + j);
                        K--;
                        j++;
                    }
                    break;
                } else {
                    K = K - arr[i].length() + lcp[i];
                }
            }

            System.out.println("#" + test_case + " " + answer);
        }
    }
}