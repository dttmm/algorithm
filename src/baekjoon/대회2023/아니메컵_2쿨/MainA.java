package baekjoon.대회2023.아니메컵_2쿨;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 완탐문제
 일일이 좌표를 하나씩 선택해가며
 조건을 만족하는지 확인
 N이 50이기 때문에
 N*N = 2500 개를 하나씩 골라가며
 N+N = 100 개에 대해 일일히 확인해봐도
 250,000으로 시간안에 충분히 해결
 */
public class MainA {

    static int N;
    static int M;
    static List<Node> nodeList;

    private static class Node {
        int i;
        int j;
        int d;

        public boolean isSameD(int oI, int oJ) {
            int newD = Math.abs(this.i - oI) + Math.abs(this.j - oJ);
            return this.d == newD;
        }

        public Node(int i, int j, int d) {
            this.i = i;
            this.j = j;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/아니메컵_2쿨/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nodeList = new ArrayList();

        for (int i = 0; i < N; i++) {

            if (i == N - 1) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int n = Integer.parseInt(st.nextToken());
                    nodeList.add(new Node(i, j, n));
                }
                continue;
            }

            int n = Integer.parseInt(br.readLine());
            nodeList.add(new Node(i, 0, n));
        }

        int answerI = 0;
        int answerJ = 0;
        boolean flag = true;    // i, j 좌표가 주어진 거리를 전부 만족하는지 확인 플래그 true: 해당 좌표가 고라니가 있는 자리
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                flag = true;
                for (Node node : nodeList) {
                    // 리스트 중에서 하나라도 거리가 맞지 않는다면 해당 좌표는 정답이 아님
                    if (!node.isSameD(i, j)) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    answerI = i + 1;
                    answerJ = j + 1;
                    break;
                }
            }
            if (flag) break;
        }

        System.out.println(answerI + " " + answerJ);
    }
}
