package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 투포인터를 이용하여 해결
 i와 j 인덱스를 시작으로
 j를 하나씩 늘려가며
 합이 S 이상인지 확인
 S 이상일 경우 i를 뺐을 때도 S 이상인지 확인해 가는식으로 확인
 합이 S 미만일 경우
 j를 더해줌

 합이 S 이상일 때마다
 i와 j 인덱스를 이용하여 길이 계산 후 최소값 구함

 재귀부분 찝찝해서 찾아보니까
 재귀를 더 간단히 할 수 있었구나..
 */
public class Main1806 {

    static int N;
    static int S;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1806.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        int i = 0;
        int j = 0;
        int sum = arr[i];
        int min = Integer.MAX_VALUE;

        while (j < N) {

            // 합이 S 이상인 경우
            if (sum >= S) {
                int length = j - i + 1;
                min = Math.min(min, length);

                // i 인덱스를 뺐을 때도 합이 S 이상인 경우
                if (sum - arr[i] >= S) {
                    sum -= arr[i];
                    i++;
                } else {
                    j++;
                    if (j >= N) break;
                    sum += arr[j];
                }
            }
            // 합이 S 미만인 경우
            else {
                j++;
                if (j >= N) break;
                sum += arr[j];
            }
        }

        if (min == Integer.MAX_VALUE) min = 0;
        System.out.println(min);
    }
}
