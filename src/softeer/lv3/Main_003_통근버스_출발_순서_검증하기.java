package softeer.lv3;

import java.util.*;
import java.io.*;

/**
 어이없다
 문제 이해하는데 30분 걸리고
 단순 반복하기에는 시간 초과 날것 같아서
 dp인가 고민함

 그냥 간단하게 조합으로 3개 뽑아서 i, j, k 비교하면 될 것 같았는데 시간초과...
 dp적 방법을 생각하다가
 그냥 이중for문 해볼까 했는데 되네?!?!!

 5000*5000 = 25,000,000
 단순 이중 for문만 돌렸을때
 계산 상으로는 시간초과 안나지만
 ide상으로 시간 초과 나길래
 제출하면 시간 초과될 줄 알았는데..
 */
public class Main_003_통근버스_출발_순서_검증하기 {

    public class Main {

        static int N;
        static int[] arr;

        public static void main(String args[]) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            long answer = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int n = Integer.parseInt(st.nextToken());
                arr[i] = n;
            }

            for (int i = 0; i < N; i++) {
                int total = 0;
                int n = arr[i];
                for (int j = i + 1; j < N; j++) {
                    if (arr[j] > n) total++;
                    else answer += total;
                }
            }

            System.out.println(answer);
        }
    }
}
