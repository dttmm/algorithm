package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 한 점에서 다른 두 점들을 이어볼까
 중심점에서 다른 두 점들을 이어볼까
 어떻게 하면 면적을 구할 수 있을까 생각하다
 아이디어 안떠올라서
 다각형의 면적 구하는 아이디어 검색

 다각형 점을 순서대로 돌면서
 (y의 차이 * x의 평균)으로 구간의 넓이를 구해주고
 이전 점보다 y가 큰 경우 넓이를 +해주고
 y가 작은 경우 넓이를 -해주면
 다각형의 넓이만 만게 되는 구나

 점의 위치가 시계방향으로 주어질 수도 있고
 반시계방향으로 주어질 수도 있고
 방향에 따라 부호가 달라지니 마지막에 abs해주먄 끝
 */
public class Main2166 {

    static int N;
    static int[] xArr;
    static int[] yArr;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2166.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        xArr = new int[N];
        yArr = new int[N];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) + 100000;
            int y = Integer.parseInt(st.nextToken());

            xArr[i] = x;
            yArr[i] = y;
        }

        // 첫번째 점의 마지막 점하고 비교해야됨
        int preX = xArr[N - 1];
        int preY = yArr[N - 1];
        double area = 0.0;

        // 점 탐색하면서 이전 점과 비교
        for (int i = 0; i < N; i++) {
            int x = xArr[i];
            int y = yArr[i];

            double avgX = ((double) (x + preX) / 2) - 100000;
            int diffY = y - preY;

            area += diffY * avgX;

            preX = x;
            preY = y;
        }

        area = Math.abs(area);

        System.out.printf("%.1f", area);
    }
}
