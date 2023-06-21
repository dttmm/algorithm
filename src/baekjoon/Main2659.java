package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 설계 27분 구현 10분 디버깅 13분
 처음에는 어떠한 규칙이 있지 않을까
 특정 숫자에 대한 답을 바로 찾는 방법이 없을까 패턴을 찾아보려고 했지만 실패
 그냥 모든 가능한 시계수를 만들고
 입력 받은 수가 몇 번째인지 찾는 방법을 선택

 중복 조합을 통해 4개의 수를 고르고
 4개의 수로 만들 수 있는 최소값을 set에 저장함
 틀림

 알고보니 시계수는 4개의 수로 만들 수 있는 최소값이 아니라
 4개의 수를 시계방향으로 읽었을 때 만들 수 있는 최소값이었음
 그래서 중복순열로 수 4개롤 고르고
 고른 수로 만들 수 있는 시계수를 구한다음 set에 저장함
 */
public class Main2659 {

    static int[] tr;
    static Set<Integer> set;

    // 시계수 구하기
    static int convertClockNum(int[] arr) {
        // 4자리 수 먼저 만들고
        int n = 0;
        for (int i = 3; i >= 0; i--) {
            n += arr[i] * Math.pow(10, 3 - i);
        }

        // 시계수 중 최소값 찾기
        int min = 10000;
        for (int i = 0; i < 4; i++) {
            min = Math.min(min, n);

            int q = n / 1000;
            int r = n % 1000;
            n = r * 10 + q;
        }
        return min;
    }

    // 중복순열
    static void solve(int k) {
        if (k == 4) {
            int[] arr = Arrays.copyOf(tr, tr.length);

            int n = convertClockNum(arr);
            set.add(n);
        } else {
            for (int i = 1; i < 10; i++) {
                tr[k] = i;
                solve(k + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2659.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tr = new int[4];
        set = new TreeSet();

        solve(0);

        // 입력 받고
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int n = convertClockNum(arr);

        // 몇 번째인지 세기
        int count = 1;
        for (int i : set) {
            if (n == i) break;
            count++;
        }

        System.out.println(count);
    }
}
