package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 첫번째와 두번째의 dx(dx1), dy(dy1)
 첫번째와 세번째의 dx(dx2), dy(dy2)를 구해준 뒤
 서로의 dx, dy값을 곱해주면
 어느 선분의 기울기가 더 높고 낮은지 알 수 있음
 n1이 크면 첫번째와 두번째를 이은 선분의 기울기가 높다는 것임 -> 반시계 방향
 n1와 n2의 크기를 조건 분기해서 정답 판별함

 입력이
 0 0
 1 2
 -1 -1
 첫번째와 두번째를 이은 선분의 기울기가 더 높아도
 두번째와 세번째 x의 부호가 다르면
 방향이 시계방향이 되야되서
 두 x의 부호 일치 여부에 따라 조건 분기를 해주려고 했는데

 어차피 n1, n2를 구하는 과정에서
 x의 부호에 따라 결과가 n1, n2에 반영돼서
 x의 부호 따로 고려할 필요 없음

 +
 CCW가 뭔지 찾아봤는데
 세 점의 방향성 판별한느 알고리즘이네
 벡터 외적에서 아이디어 따온 알고리즘이었넹
 */
public class Main11758 {

    static int[] X;
    static int[] Y;

    static int solve() {
        int dx1 = X[1] - X[0];
        int dy1 = Y[1] - Y[0];

        int dx2 = X[2] - X[0];
        int dy2 = Y[2] - Y[0];

        int n1 = dx2 * dy1;
        int n2 = dx1 * dy2;

        if (n2 > n1) return 1;
        else if (n2 < n1) return -1;
        else return 0;

    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11758.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        X = new int[3];
        Y = new int[3];

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            X[i] = x;
            Y[i] = y;
        }

        int result = solve();
        System.out.println(result);
    }
}
