package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 구현? 아이디어? 그리디?
 뭔 문제인지 정의하기 힘들다
 재미있어 보여서 풀어봄

 입력값 N, M이 커서 일일이 하차 시간을 따져가며 구하는 것을 불가능이라 판단
 하차 시칸을 한번에 딱 계산하는 방법을 생각함

 먼저, 승차하기 위해서 버스가 몇 번 돌아야 하는지 계산함
 버스를 기다리기 시작한 시간(startTime)과 해당 승차 지점까지 오는데 걸리는 시간(d[i])를 비교해서
 버스가 몇 바퀴 돌고와야 한다면 한바퀴 도는데 걸리는 시간(roundTime)을 활용하여 해당 바퀴 횟수(count)를 구해줌
 버스가 돈 횟수와 승차지점까지 오는데 걸리는 시간과 버스에 타있는 시간을 더해주면 하차시간 완성
 */
public class Main27278 {

    static int N;
    static int M;
    static int[] arr;       // i에서 i+1까지 가는데 걸리는 시간
    static int[] d;         // i까지 오는데 걸리는 총 시간
    static int roundTime;   // 한 바퀴 도는데 걸리는 시간

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/27278.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        d = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        for (int i = 2; i <= N; i++) {
            d[i] = d[i - 1] + arr[i - 1];
        }
        roundTime = d[N] + arr[N];

        int max = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int startTime = Integer.parseInt(st.nextToken());

            // 탑승하기 위해서 버스가 몇 바퀴를 돌아야 하는지 계산
            int count = 0;
            int rideTime = startTime - d[from];
            if (rideTime > 0) count = ((rideTime - 1) / roundTime) + 1;

            // 버스에 타 있는 시간
            int driveTime = d[to] - d[from];
            if (driveTime < 0) driveTime += roundTime;

            // 버스가 몇 바퀴 돌았는지 + 버스가 승차 지점까지 오는 시간 + 버스에 타 있는 시간 -> 하차 시간
            int total = roundTime * count + d[from] + driveTime;

            max = Math.max(max, total);
        }

        System.out.println(max);
    }
}
