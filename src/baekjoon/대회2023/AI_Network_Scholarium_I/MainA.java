package baekjoon.대회2023.AI_Network_Scholarium_I;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 어렵게 보이도록 포장한 문제

 처음에는 bfs로 풀어야 할까
 그러면 경로 저장은 어떻게 할까
 고민 했었는데..
 사실 그냥 입력을 반대로 수행하면 다시 원래자리로 돌아가넴..
 */
public class MainA {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/AI_Network_Scholarium_I/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        br.readLine();
        String s = br.readLine();
        StringBuilder sb = new StringBuilder(s);

        // 입력 반대로 해줌
        sb.reverse();

        System.out.println(sb);
    }
}
