package 대회.YEAR_2023.쇼미더코드_3회차;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 첫 문제치고는
 생각보다 아이디어가 발리 안떠올랐음
 연속적으로 딱 한번만 칠할 수 있는지 모르고
 처음에 그냥 순차탐색하면서 왼쪽과 오른쪽 차이의 최대값의 abs를 구했음
 딱 한번만 칠할 수 있다는 것을 깨닫고
 정답이 되는 구간을 어떻게 찾아야 될까 고민함
 모든 서브 구간마다 연산을 하는 것은(십만 * 십만 = 10억)
 무조건 시간초과가 나기 떄문에 dp인가 고민했음
 근데, 단순 계산 문제였음

 예를 들어,
 입력이
 2 1 1 2 1 1인 경우
 누적된 왼쪽, 오른쪽의 개수를 보면
 왼쪽
 0 1 2 2 3 4
 오른쪽
 1 1 1 2 2 2
 각 구간 마다의 왼쪽 - 오른쪽 차이를 구하면
 -1 0 1 0 1 2로
 이 차이가 최대인 지점과 최소인 지점을 골라서
 둘을 뺐음
 위 경우는 최대는 2, 최소는 -1이므로
 0번 부터 5번 인덱스까지 다 칠할 경우 최대 구간이 됨
 */
public class MainA {

    static int N;
    static int left;
    static int right;
    static int min;
    static int max;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/대회/YEAR_2023/쇼미더코드_3회차/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        min = 0;
        max = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (n == 1) left++;
            else right++;
            int diff = left - right;
            max = Math.max(max, diff);
            min = Math.min(min, diff);
        }

        System.out.println(max - min);
    }
}
