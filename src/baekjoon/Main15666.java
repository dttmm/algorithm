package baekjoon;

import java.io.*;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 단순 중복조합 문제
 숫자가 중복되어 있어도 하나로 간주하기 위해
 처음에 입력 받을 때
 set에 담아서 중복을 제거해 주었음
 */
public class Main15666 {

    static int N;
    static int R;
    static int[] arr;
    static int[] tr;
    static Set<Integer> set;
    static BufferedWriter bw;

    static void cc(int k, int start) throws Exception {
        if (k == R) {
            for (int i = 0; i < tr.length; i++) {
                bw.write(tr[i] + " ");
            }
            bw.newLine();
        } else {
            for (int i = start; i < arr.length; i++) {
                tr[k] = arr[i];
                cc(k + 1, i);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/15666.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        set = new TreeSet();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            set.add(n);
        }

        arr = new int[set.size()];
        tr = new int[R];
        int index = 0;
        for (int n : set) {
            arr[index++] = n;
        }

        cc(0, 0);

        bw.flush();
        bw.close();
    }
}
