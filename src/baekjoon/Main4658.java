package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 완탐을 두번 돌림
 먼저 6개의 삼각형을 순열로 6개를 뽑은 뒤 (solve)
 6개의 삼각형 순서대로 오른쪽 면과 왼쪽면이 일치하는지 확인해가며 완탐 돌림(solve2)

 처음에는 뭔가 아이디어를 떠올리려고 했지만 실패하여
 그냥 완탐 돌려야 겠다고 생각함
 6개 순열 돌라가 * 각 삼각형 오른쪽면 뽑기 * 테케 100 =
 6! * 3^6 * 100 = 900만밖에 안됨
 */
public class Main4658 {

    static int[][] arr;
    static int[] tr;
    static boolean[] visited;
    static int max;

    // 6개의 삼각형의 오른쪽 면과 왼쪽면 일치하는지 확인
    public static void solve2(int k, int preLeft, int initRight, int sum) {
        if (k == 6) {
            // 마지막 삼각형의 왼쪽면과 첫번째 삼각형의 오른쪽면을 확인
            if (preLeft != initRight) return;
            max = Math.max(max, sum);
        } else {
            for (int i = 0; i < 3; i++) {
                int right = arr[tr[k]][i];
                int left = arr[tr[k]][(i + 1) % 3];

                // preLeft: -1이면 첫번째 삼각형
                if (preLeft == -1 || preLeft == right) {
                    int mid = arr[tr[k]][(i + 2) % 3];  // 점수가 될 면

                    if (k == 0) initRight = right;
                    solve2(k + 1, left, initRight, sum + mid);
                }
            }
        }
    }

    // 삼각형 6개 순열로 뽑기
    public static void solve(int k) {
        if (k == 6) {
            solve2(0, -1, 0, 0);
        } else {
            for (int i = 0; i < 6; i++) {
                if (visited[i]) continue;
                tr[k] = i;
                visited[i] = true;
                solve(k + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/4658.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        String s = "*";

        while (s.equals("*")) {
            arr = new int[6][3];
            tr = new int[6];
            visited = new boolean[6];
            max = 0;

            for (int i = 0; i < 6; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
                arr[i][2] = Integer.parseInt(st.nextToken());
            }

            solve(0);

            if (max == 0) sb.append("none\n");
            else sb.append(max + "\n");

            s = br.readLine();
        }

        System.out.println(sb);
    }
}
