package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 처음에는 어떠한 규칙이 있을 것 같아서 규칙을 찾아보기로 함
 일단 5의 배수가 나올 때마다 0의 개수가 하나씩 증가해서
 5로 나눈 몫이 정답이라고 생각했으나 예외가 발생함
 50!의 경우 49!보다 0의 개수가 2개나 증가함
 5와 10 때문에 0의 개수가 2개나 증가했다고 판단하여
 50으로 나누어지면 추가로 0의 개수를 하나 더 증가시킴
 그런데도 실패함

 그래서 알고리즘 분류를 보니 큰 수 연산이길래
 그냥 BigInteger로 팩토리얼 계산해서
 0의 개수를 세주었음
 정답 통과 되긴 했는데 너무 찜찜해서
 정답 찾아봄

 아..
 어차피 지금까지 곱한 숫자에서 0이아닌 제일 작은 수는 짝수이니
 5가 곱해질 때마다 0의 개수가 증가하는구나
 25의 경우 5가 두번 곱해져서 0의 개수가 2개 증가하는 구나...
 125는 3개 증가..
 수학 어렵네
 */

// 21분+
public class Main1676 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1676.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int zero = 0;
        zero += N / 5;
        zero += N / 25;
        zero += N / 125;

        System.out.println(zero);

        // 단순 계산으로 풀이
//        BigInteger x = new BigInteger("1");
//        for (int i = 1; i <= N; i++) {
//            x = x.multiply(new BigInteger(i + ""));
//        }
//
//        int zero = 0;
//        while (x.mod(new BigInteger("10")).equals(new BigInteger("0"))) {
//            zero++;
//            x = x.divide(new BigInteger("10"));
//        }
//        System.out.println(zero);

        // 열심히 규칙 찾으려는 흔적
        /**
         0 1
         1 1
         2 2
         3 6
         4 24
         5 120
         6 720
         7 40
         8 320
         9 180
         10 1800
         11 9800
         12 600
         13 1800
         14 200
         15 1000
         16 600
         17 200
         18 600
         19 400
         20 80000
         21 80000
         22 160000
         23 80000
         24 20000
         25 100000
         */
    }
}
