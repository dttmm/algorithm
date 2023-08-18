package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 설계 4분 구현 6분 디버깅 43분
 투포인터
 두 배열을 정렬하고
 각각의 배열에서 가장 높은 값부터 포인터(indexA, indexB)로 가리키면서
 둘 중 더 높은 점수가 있으면
 해당 점수로 상대 팀에서 몇 명을 이길 수 있는지
 상태팀 포인터를 이용하여 승리 횟수 계산해줌

 무승부인 경우
 해당 점수에서 각각
 A팀에서 이길 수 있는 경우(answerA += indexB)를 구해주고
 B팀에서 이길 수 있는 경우(answerB += indexA)를 구해주고
 무승부 횟수를 1증가(answerC++)시키고
 포인터를 1씩 왼쪽으로 이동시킴

 틀림
 무승부가 났을 때
 동일한 점수가 각 팀에 여러개 있을 경우
 위의 방법은 통하지가 않음

 중복이 아닌 수가 나올 때까지 몇번 이동했는지를 나타내는 변수를 추가(countA, countB)하고
 해당 변수와 현재 상대팀의 포인터를 이용하여
 A, B 각팀에서 이길 수 있는 경우를 구해주고
 무승부 횟수는 countA, countB중 최대값으로 정함

 틀림
 무승부 횟수 구하는 식이 틀림
 내가 왜 무승부 횟수를 countA, countB중 최대값으로 정했는지 잘 모르겠음
 일단 점수가 같으면 먼저 무승부 횟수를 1먼저 올려줌
 그러면 하나의 무승부 경기는 이미 처리한 거니까
 countA, countB에서 각각 1을 빼주고 서로 곱한 수를 무승부 횟수에 더함

 시간 초과
 무승부가날 때마다 포인터를 1씩 이동시켜주면 안되겠다
 포인터를 1씩 이동시켜주면서 매번 countA, countB를 구하니까 연산이 오래 걸린다
 포인터를 중복이 아닌 수로 한번에 이동 시켜주어야 한다
 그러기 위해서는
 해당 구간을 한번에 이동시키면서
 그 동안의 A팀이 이긴 횟수, B팀이 이긴 횟수, 무승부 횟수를 한꺼번에 계산해주어야 한다
 A팀이 이긴 횟수는 무승부가 난 점수로 B 팀을 이길 수 있는 경우이므로
 A팀의 무승부 난 점수의 개수에 B팀의 무승부 보다 작은 점수들의 개수를 계산해주면 됨
 B팀이 이긴 횟수도 그 반대로 구할 수 있고
 무승부가 난 횟수는 countA, countB을 곱해주면
 해당 점수로 무승부 날 수 있는 경우의 수를 한번에 구해줄 수 있음

 투포인터로 풀면 될꺼라는 생각은 떠올랐지만
 세세한 로직을 신경쓰지 못해뜨아
 */
public class Main28449 {

    static int N;
    static int M;
    static int[] arr;   // HI팀 점수 (A팀)
    static int[] brr;   // ARC팀 점수 (B팀)

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/28449.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        brr = new int[M];

        // 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        // 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int n = Integer.parseInt(st.nextToken());
            brr[i] = n;
        }

        // 점수순으로 정렬
        Arrays.sort(arr);
        Arrays.sort(brr);

        int indexA = N - 1; // A팀 포인터
        int indexB = M - 1; // B팀 포인터
        long answerA = 0;   // A팀이 이기는 횟수
        long answerB = 0;   // B팀이 이기는 횟수
        long answerC = 0;   // 무승부난 횟수
        while (indexA >= 0 && indexB >= 0) {
            int a = arr[indexA];
            int b = brr[indexB];

            // A팀이 점수가 높은 경우
            if (a > b) {
                answerA += indexB + 1;
                indexA--;
            }
            // B 팀이 점수가 높은 경우
            else if (b > a) {
                answerB += indexA + 1;
                indexB--;
            }
            // 무승부난 경우
            else {
                int countA = 0; // A팀에서 현재 무승부난 점수의 개수
                int countB = 0; // B팀에서 현재 무승부난 점수의 개수

                // 각팀에 해당 점수가 몇 개 있는지 개수 카운트
                while (indexA - countA >= 0 && arr[indexA - countA] == a) countA++;
                while (indexB - countB >= 0 && brr[indexB - countB] == b) countB++;

                answerA += (long) countA * (indexB - countB + 1);
                answerB += (long) countB * (indexA - countA + 1);
                answerC += (long) countA * countB;

                indexA = indexA - countA;
                indexB = indexB - countB;
            }
        }

        System.out.println(answerA + " " + answerB + " " + answerC);
    }
}
