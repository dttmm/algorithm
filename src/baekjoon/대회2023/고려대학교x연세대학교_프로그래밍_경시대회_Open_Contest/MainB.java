package baekjoon.대회2023.고려대학교x연세대학교_프로그래밍_경시대회_Open_Contest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 두 수의 차가 짝수인 경우와 홀수인 경우를 나눠서 생각함
 두 수의 차가 짝수가 되려면 짝수끼리 빼거나 홀수끼리 빼야하고
 두 수의 차가 홀수가 되려면 짝수와 홀수끼리 뺴야함

 짝수끼리 빼는 경우 + 수끼리 빼는 경우에서
 두 수의 차가 짝수인 최소값 찾음
 먼저 짝수를 담은 리스트과 홀수를 담을 리스트를 정렬하고
 인접한 두 원소끼리 차이 비교하여 최소값 찾음

 짝수와 홀수끼리 뺴는 경우에서
 두 수의 차가 홀수인 최소값 찾음
 짝수를 담은 리스트와 홀수를 담을 리스트의 인덱스를 이용하여 최소값을 찾음
 둘 중 숫자가 더 작은쪽의 리스트의 인덱스를 ++해가며 최소값 찾음
 */
public class MainB {

    static int N;
    static List<Integer> even;  // 짝수 담을 리스트
    static List<Integer> odd;   // 홀수 담을 리스트

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/고려대학교x연세대학교_프로그래밍_경시대회_Open_Contest/B.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        even = new ArrayList();
        odd = new ArrayList();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (n % 2 == 0) even.add(n);
            else odd.add(n);
        }

        // 정렬
        Collections.sort(even);
        Collections.sort(odd);

        // 짝수끼리 빼는 경우에서 두 수의 차가 짝수인 최소값 찾기
        int min_even = Integer.MAX_VALUE;   // 두 수의 차가 짝수 중 최소값
        for (int i = 1; i < even.size(); i++) {
            int diff = even.get(i) - even.get(i - 1);
            min_even = Math.min(min_even, diff);
        }

        // 홀수끼리 빼는 경우에서 두 수의 차가 짝수인 최소값 찾기
        for (int i = 1; i < odd.size(); i++) {
            int diff = odd.get(i) - odd.get(i - 1);
            min_even = Math.min(min_even, diff);
        }

        // 짝수와 홀수끼리 뺴는 경우에서 두 수의 차가 홀수인 최소값 찾기
        int min_odd = Integer.MAX_VALUE;    // 두 수의 차가 홀수 중 최소값
        int i = 0;
        int j = 0;
        while (i < even.size() && j < odd.size()) {
            int n1 = even.get(i);
            int n2 = odd.get(j);

            int diff = Math.abs(n1 - n2);

            min_odd = Math.min(min_odd, diff);

            if (n1 < n2) i++;
            else j++;
        }

        if (min_even == Integer.MAX_VALUE) min_even = -1;
        if (min_odd == Integer.MAX_VALUE) min_odd = -1;
        System.out.println(min_even + " " + min_odd);
    }
}
