package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main4344 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/4344.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            double sum = 0;
            double[] scores = new double[M];
            for (int j = 0; j < M; j++) {
                double score = Double.parseDouble(st.nextToken());
                scores[j] = score;
                sum += score;
            }
            double avg = sum / M;
            int count = 0;
            for (int k = 0; k < M; k++) {
                if (scores[k] > avg) {
                    count++;
                }
            }
            System.out.printf("%.3f%%\n", (double) count / M * 100);
        }

    }
}
