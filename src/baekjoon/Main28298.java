package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 18분 구현 12분 디버깅 3분
 관건은 2가지
 답을 만족하는 타일 색상을 구하는 것과
 정답 타일을 출력하는 것

 먼저 각각의 타일로 쪼갠다음
 타일마다 특정 인덱스에 어떤 색이 있는지를 counts[인덱스][색]에 저장하고
 counts를 돌면서 인덱스 마다 최고로 많이 있는 색(maxC)을 찾음
 그러면 각 인덱스의 색을 maxC색으로 칠해준다면 최소로 칠하는 개수를 구할 수 있음
 색을 칠해야 되는 개수(answerTotal)는
 각 인덱스에서 maxC가 아닌 색들을 maxC로 칠한 개수 (tileTotal - max)를 더해주면 됨

 정답을 출력하기 위해서는 생각을 좀 많이 했음
 우선 M/K번 반복하면서 answerTile의 행을 일렬로 출력해야되고
 위의 것을 answerTile의 행만큼 반복하면서 출력을 해줘야되고
 위의 것을 N/K번 반복해줘야함
 4중 for문 짜는 것이 좀 힘들었다 휴

 중간에 answerTile 크기 설계 잘못해서 수정함
 answerTile의 크기는 K*K이구먼
 */
public class Main28298 {

    static int N;
    static int M;
    static int K;
    static int tileTotal;   // 전체 타일의 개수
    static char[][] arr;
    static int[][] counts;  // 타일의 index번째에 있는 알파벳 개수
    static int answerTotal; // 다시 칠해야 하는 타일의 최소 개수
    static char[][] answerTile; // 다시 칠해야 하는 타일 색

    // 타일에서 각 인덱스에 있는 색들 카운트
    static void setCount() {
        for (int i = 0; i < N; i += K) {
            for (int j = 0; j < M; j += K) {

                int index = 0;
                for (int ii = i; ii < i + K; ii++) {
                    for (int jj = j; jj < j + K; jj++) {
                        char c = arr[ii][jj];
                        counts[index][c - 'A']++;
                        index++;
                    }
                }
            }
        }
    }

    // 타일에서 각 인덱스에 어떤 색이 제일 많은지 찾음
    static void findMax() {
        for (int index = 0; index < K * K; index++) {
            int max = 0;
            char maxC = '\0';
            for (int x = 0; x < 26; x++) {
                if (counts[index][x] > max) {
                    max = counts[index][x];
                    maxC = (char) ('A' + x);
                }
            }

            int i = index / K;
            int j = index % K;
            answerTile[i][j] = maxC;
            answerTotal += tileTotal - max;
        }
    }

    // 정답 출력
    static String print() {
        StringBuilder sb = new StringBuilder();
        sb.append(answerTotal + "\n");

        for (int i = 0; i < N / K; i++) {
            for (int i_k = 0; i_k < K; i_k++) {

                for (int j = 0; j < M / K; j++) {
                    for (int j_k = 0; j_k < K; j_k++) {
                        sb.append(answerTile[i_k][j_k]);
                    }
                }
                sb.append("\n");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/28298.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        tileTotal = N * M / (K * K);
        arr = new char[N][M];
        counts = new int[K * K][26];
        answerTotal = 0;
        answerTile = new char[K][K];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                arr[i][j] = c;
            }
        }

        setCount();

        findMax();

        String s = print();
        System.out.println(s);
    }
}
