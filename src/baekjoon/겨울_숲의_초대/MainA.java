package baekjoon.겨울_숲의_초대;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 가장 큰 숫자(A)를 뽑고
 그 다음 큰 숫자(B)를 뽑아서
 둘의 차이 만큼 A에서 빼주고
 B만큼 카운트를 해준다

 그 다음 큰 숫자(B)가 없을 경우
 A만큼 카운트를 해준다
 */
public class MainA {

    static int N;
    static PriorityQueue<Integer> pq;
    static int answer;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/겨울_숲의_초대/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue();
        answer = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            pq.add(n * -1);
        }

        while (!pq.isEmpty()) {
            int A = pq.poll() * -1;

            if (pq.isEmpty()) answer += A;
            else {
                int B = pq.poll() * -1;
                A -= B;
                if (A != 0) pq.add(A * -1);
                answer += B;
                if (answer > 1440) break;
            }
        }

        if (answer > 1440) answer = -1;
        System.out.println(answer);
    }
}
