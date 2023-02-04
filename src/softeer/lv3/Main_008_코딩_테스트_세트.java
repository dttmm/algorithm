package softeer.lv3;

import java.util.*;
import java.io.*;

/**
 숫자의 범위가 100억이고
 해당 숫자들 사이에서 최적해를 찾아야 한다??
 dp나 이분탐색을 생각함
 애매한 문제가 어느 번호로 배정 되느냐에 따라
 수많은 경우의 수들이 생겨서 dp로도 아이디어가 떠오르지 않음
 결국 특정 값 하나하나씩 해보면서 정답이니 아닌지 찾기위해 이분탐색 뚝딱뚝딱

 이분탐색으로 mid값 구하면서
 mid값을 만족하는지 검사하는 과정을 거침

 이분 탐색 처음 시작할 때
 end값을 그냥 최대값 넣어줘도
 어차피 시간 차이 그렇게 많이 안나는구나
 */
public class Main_008_코딩_테스트_세트 {

    public class Main {
        static int N;
        static int T;
        static long[] arr;  // 정확히 평가된 문제들
        static long[] brr;  // 애매하게 평가된 문제들
        static long answer;
        static long brr_max;

        static void solve(long start, long end) {

            if (start > end) return;

            long mid = (start + end) / 2;

            long[] arr2 = Arrays.copyOf(arr, N);
            long[] brr2 = Arrays.copyOf(brr, N - 1);

            int i = 0;
            boolean flag = true;

            while (i <= N - 1) {

                // 이미 해당 문제가 mid값을 넘는 경우
                // 애매한 문제까지 끌고 올 필요 없음
                if (arr2[i] >= mid) {
                    i++;
                    continue;
                }

                // 첫 문제의 경우, 끌고 올 수 있는 문제는 1 ~ 2번 문제밖에 없음
                if (i == 0) {
                    if (arr2[i] + brr2[i] >= mid) {
                        long need = mid - arr2[i];

                        arr2[i] += need;
                        brr2[i] -= need;
                    } else {
                        flag = false;
                        break;
                    }
                }
                // 마지막 문제의 경우, 끌고 올 수 있는 문제는 N-1 ~ N번 문제밖에 없음
                else if (i == N - 1) {
                    if (arr2[i] + brr2[i - 1] >= mid) {
                        long need = mid - arr2[i];

                        arr2[i] += need;
                    } else {
                        flag = false;
                        break;
                    }
                }
                // 먼저 i-1 ~ i번 문제를 끌고 올 수 있는지 검사
                else {
                    if (arr2[i] + brr2[i - 1] >= mid) {
                        long need = mid - arr2[i];

                        arr2[i] += need;
                    }
                    // 문제를 더 끌고 와야 되는 경우
                    else {
                        // i-1 ~ i번 문제를 다 끌고 오고
                        arr2[i] += brr2[i - 1];

                        // i ~ i+1번 문제를 끌고 올 수 있는지 검사
                        if (arr2[i] + brr2[i] >= mid) {
                            long need = mid - arr2[i];

                            arr2[i] += need;
                            brr2[i] -= need;
                        } else {
                            flag = false;
                            break;
                        }
                    }

                }
                i++;
            }

            if (flag) {
                brr_max = Math.max(brr_max, mid);
                solve(mid + 1, end);
            } else {
                solve(start, mid - 1);
            }
        }

        public static void main(String args[]) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());

            for (int tc = 0; tc < T; tc++) {
                arr = new long[N];
                brr = new long[N - 1];
                answer = 0;
                brr_max = 0;

                long arr_min = Long.MAX_VALUE;
                st = new StringTokenizer(br.readLine());
                for (int i = 0; i < 2 * N - 1; i++) {
                    long n = Long.parseLong(st.nextToken());
                    if (i % 2 == 0) {
                        arr[i / 2] = n;
                        arr_min = Math.min(arr_min, n);
                    } else {
                        brr[i / 2] = n;
                    }
                }

                for (int i = 0; i < N; i++) {
                    arr[i] = arr[i] - arr_min;
                }

                solve(0, 1000000000000L * 2);

                answer += arr_min;
                answer += brr_max;


                System.out.println(answer);
            }
        }
    }
}
