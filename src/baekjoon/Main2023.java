package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 처음에는 에라토스테네스의 체로 모든 수에 대해서
 소수인지 판별하고 시작을 했는데
 메모리 제한이 4MB이고 배열의 최대 크기는 1억이라 메모리 초과됨

 결국 숫자 하나씩 소수인지 판별을 해야 된다는 소리인데..
 다행히 규칙이 보임
 이전 자리수의 소수들 뒤에 0~9의 숫자를 붙여보고
 해당 수가 소수인지 아닌지 판별하면 됨
 0~9 중에서도 짝수는 제외하고 0과 5도 제외해서
 1, 3, 5, 7만 뒤에 붙여서 소수 판별함

 근데 숫자 하나를 소수 판별하는 방법이 생각이 안나서 좀 헤멤
 루트.. 잊고있었다..
 */
public class Main2023 {

    static int N;
    static List<Integer>[] lists;   // i번째 자리수의 소수들 저장

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2023.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        lists = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            lists[i] = new ArrayList<>();
        }

        // 1자리 수의 소수 넣어줌
        lists[1].add(2);
        lists[1].add(3);
        lists[1].add(5);
        lists[1].add(7);

        // i자리 수의 소수 구하기
        for (int i = 2; i <= N; i++) {
            // i-1자리 수의 소수에서
            for (int num : lists[i - 1]) {
                // 뒤에 숫자 붙인 수가 소수인지 판별
                for (int j = 1; j < 10; j++) {
                    if (j % 2 == 0 || j == 5) continue;

                    int newNum = num * 10 + j;
                    int limit = (int) Math.sqrt(newNum);
                    boolean flag = true;    // true: newNum이 소수라는 뜻

                    for (int x = 2; x <= limit; x++) {
                        if (newNum % x != 0) continue;

                        flag = false;
                        break;
                    }

                    if (flag) lists[i].add(newNum);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int num : lists[N]) {
            sb.append(num + "\n");
        }
        System.out.println(sb);
    }
}
