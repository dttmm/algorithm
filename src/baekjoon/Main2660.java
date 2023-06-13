package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 설계 5분 구현 11분
 각 회원의 점수를 구해야 하는데
 각 회원마다 모든 회원사이의 점수를 구한 뒤,
 점수들 중에서 최대값이 바로 해당 회원의 점수가 된다?
 -> 모든 노드 사이의 거리를 구할 필요가 있다
 -> 플로이드 와샬
 모든 노드사이듸 거리를 구한 뒤
 각 노드에서 다른 노드까지의 거리중 최대 거리(max)를 구하고
 최대 거리들 중에서 최소값(wholeMin)을 찾으면
 바로 최소값을 보유하고 있는 노드가 회장 후보가 됨

 최대 거리가 n인 노드를 maxList[]에 담고
 maxList[]에서 최소값에 해당하는 노드를 찾으면
 그것이 바로 회장 후보가 들어있는 회원들 리스트임
 */
public class Main2660 {

    static int INF = 50;    // 최대 가능 거리는 49, 그 이상보다 큰 50을 무한으로 설정
    static int N;
    static int[][] d;   // 회원 사이의 거리
    static List<Integer>[] maxList; // 최대 거리(n)를 가진 회원들 리스트

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2660.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        N = Integer.parseInt(br.readLine());
        d = new int[N + 1][N + 1];

        // 회원 사이의 관계 초기화
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                d[i][j] = INF;
            }
        }

        // 회원들 연결하기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        while (n1 != -1) {
            d[n1][n2] = 1;
            d[n2][n1] = 1;

            st = new StringTokenizer(br.readLine());
            n1 = Integer.parseInt(st.nextToken());
            n2 = Integer.parseInt(st.nextToken());
        }

        // 플로이드 와샬
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    int newD = d[i][k] + d[k][j];
                    if (newD < d[i][j]) d[i][j] = newD;
                }
            }
        }

        // 리스트 배열 초기화
        maxList = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            maxList[i] = new ArrayList();
        }

        // 각 회원마다 점수(max) 구하고
        // 회장 후보가 될 수 있는 점수(wholeMin) 구하기
        int wholeMin = INF;
        for (int i = 1; i <= N; i++) {
            int max = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                max = Math.max(max, d[i][j]);
            }
            maxList[max].add(i);
            wholeMin = Math.min(wholeMin, max);
        }

        // 회장 후보 출력
        Collections.sort(maxList[wholeMin]);
        StringBuilder sb = new StringBuilder(wholeMin + " " + maxList[wholeMin].size() + "\n");
        for (int i : maxList[wholeMin]) {
            sb.append(i + " ");
        }

        System.out.println(sb);
    }
}
