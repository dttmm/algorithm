package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 우선 N이 300,000 이므로
 N^2으로 풀 수 는 없고
 logN을 사용하기에는 쓸만한 알고리즘이 없다고 판단하여
 N만에 풀어야 된다고 생각함

 주의할 점을 찾아보니까
 무조건 결합 가능한 BC를 다 결합해주고
 나머지 중에서 AB를 결합해주어야 함

 일단 한번 쭈욱 탐색해 가면서
 B와 결합 가능한 A가 있으면 AB 결합해주고
 C와 결합 가능한 B가 있으면 BC 결합해 주는데
 결합 가능합 B가 없는 경우
 이미 결합되어 있는 AB가 있는지 확인 후
 둘을 쪼개서 B를 얻어 BC를 결합해주었음
 */
public class Main25381 {

    static char[] cArr;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/25381.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        cArr = s.toCharArray();

        int remainA = 0;    // B랑 결합 가능한 A 개수
        int countAB = 0;    // 결합한 AB 개수
        int remainB = 0;    // C랑 결합 가능합 B 개수
        int countBC = 0;    // 결합한 BC 개수
        for (int i = 0; i < cArr.length; i++) {
            char c = cArr[i];

            if (c == 'A') {
                remainA++;
            } else if (c == 'B') {
                // 결합 가능한 A 있으면 결합
                if (remainA > 0) {
                    remainA--;
                    countAB++;
                } else {
                    remainB++;
                }
            } else {
                // 결합 가능합 B 있으면 결합
                if (remainB > 0) {
                    remainB--;
                    countBC++;
                }
                // 이미 결합한 AB 뻇을 수 있으면 뺏어서 결합
                else {
                    if (countAB > 0) {
                        countAB--;
                        remainA++;
                        countBC++;
                    }
                }
            }
        }

        System.out.println(countAB + countBC);
    }
}
