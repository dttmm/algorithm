package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 처음에는 간단한게
 자신보다 오른쪽에 큰 블록이 있는 경우를 찾아가며
 이중 포문으로 풀었는데
 입력이 3 2 0 3인 경우
 3-3구간과 2-3구간을 중복 계산해버리는 문제 발생
 또한 입력이 3 0 0 1인 경우도 계산을 못해 버린느 문제 발생

 결국 모든 칸에 대해서 완탐을 해야된다고 판단
 즉, 2차원 배열을 이용하여 3중 포문을 돌림
 한층 한층 검사하면서
 블록이 있는 지점 두 곳을 찾고
 두 곳의 인덱스 차이만큼 정답에 더해줌

 3중 포문이라 H*W*W = 1억이 초과하는데
 중간에 정답 계산을 해주고 인덱스를 갱신해주면
 연산량이 H*W 정도로 감소함
 */
public class Main14719 {

    static int H;
    static int W;
    static int[][] arr; // 1: 블록 있는 곳

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/14719.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        arr = new int[H][W];

        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < W; j++) {
            int h = Integer.parseInt(st.nextToken());
            for (int i = 0; i < h; i++) {
                arr[i][j] = 1;
            }
        }

        int answer = 0;
        // 한층 한층 검사
        for (int i = 0; i < H; i++) {
            // 투포인터를 이용하여 검사
            for (int j = 0; j < W; j++) {
                if (arr[i][j] == 0) continue;
                for (int k = j + 1; k < W; k++) {
                    if (arr[i][k] == 0) continue;

                    // 두 포인터가 모두 블록을 찾았으면
                    // 두 포인터의 인덱스 차이만큼 물이 고임
                    answer += k - j - 1;

                    // 인덱스 갱신
                    j = k;
                }
            }
        }

        System.out.println(answer);
    }
}
