package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 설계 13분 구현 12분 디버깅 21분
 신경써야할 부분은 두가지
 4방향으로 블록을 합치는 것과
 합치는 과정을 신경써야함

 4방향으로 블록을 합치는 과정은
 방향에 따라 합치는 로직을 각각 구현하지 않고
 합치는 로직은 한번만 구현하고
 배열을 회전해가며 블록을 합침
 위쪽으로 합치는 로직 하나만 구현하고
 배열을 시계방향으로 회전시켰음

 위쪽으로 블록 합치는 과정을 살펴보면
 각 열은 독립적으로 다른 열에 영향을 주지 않음
 한 열씩 탐색하면서
 행에 있는 숫자를 큐에 넣고
 큐에 넣을 숫자와 똑같은 숫자가 큐에 있으면
 큐에 이미 있는 숫자를 빼주고
 큐에 넣을 숫자를 두배로 해줌 <- 이것이 바로 합치는 과정
 마지막에는 새로운 배열에 0행부터
 큐에 있는 숫자를 차례대로 넣어줌

 틀림
 자료구조를 잘못 선택함
 블록을 합칠 때
 서로 같은 숫자인지 판별하려면
 큐가 아니라 스택을 사용했어야 되네

 틀림
 블록을 합칠 때
 이미 한번 합쳐서 탄생한 블록은
 다시 한번 더 합치면 안되는 것을 간과함
 해당 블록이 합쳐서 나온 블록인지 아닌지 구분할 필요가 있네
 새로운 클래스를 만들어서 합친 여부를 같이 저장하기에는 비효율 적이어서
 -부호를 이용해서 이미 한번 합친 블록이라고 표시 해줌

 틀림
 자료구조..
 블록을 합치고
 합친 결과를 새로운 배열에 담을 때
 스택에서 하나씩 뽑게 되면
 순서가 반대로 배열에 들어가넹
 결국 합칠 때는 스택이 필요하고
 결과를 생성할 때는 큐가 필요하다
 디큐 사용해서 문제 해결

 근데 뭔가 불편하다
 이미 동일한 블록 상태에 대해 중복 연산이 발생할 수가 있다
 중복 연산을 방지하기 위해
 이차원 배열의 블록 상태를 String형으로 변환하여
 set에 저장하고
 set에 이미 해당 블록 상태가 있는 경우 중복 연산을 수행하지 않도록 했는데
 안되네
 이차원 배열에 있는 숫자가 최대 10000까지 커질 수 있고
 단순히 String으로 변환하게 되면
 중복이 아닌데 중복으로 처리될 수 있는 경우가 발생할 수 있겠구나
 이차원 배열의 상태를 저장하기 위해서는
 각 원소를 바이트로 변환해서 하나의 String으로 이어 붙여야 하는데
 1024를 바이트로 변환하려면 2바이트나 필요할텐데
 그러면 그 과정과 연산이 더 복잡해지겠넹..

 이찬원 배열의 상태 저장은
 이차원 배열의 원소가 1,0밖에 없을 때처럼
 저장할 수 있는 값이 작을 때 유용할 듯
 실제 이렇게 큰 원소를 담고 있는 이차원 배열을
 해시로 관리하는 경우가 없넹
 */

public class Main12100 {

    static int N;
    static int max;

    // 배열을 시계방향을 회전
    static int[][] rotate(int[][] arr) {
        int[][] brr = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                brr[i][j] = arr[j][N - 1 - i];
            }
        }

        return brr;
    }

    // 최대 블록 찾기
    static void findMax(int[][] arr) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, arr[i][j]);
            }
        }
    }

    // 블록 위쪽으로 움직이기
    static int[][] move(int[][] arr) {
        int[][] brr = new int[N][N];

        Deque<Integer> deque;
        // 각 열 돌면서
        for (int j = 0; j < N; j++) {
            deque = new ArrayDeque();

            // 숫자 블록 찾아서
            for (int i = 0; i < N; i++) {
                int n = arr[i][j];
                if (n == 0) continue;

                // 합칠 수 있는 블록은 합치기
                if (!deque.isEmpty()) {
                    if (deque.peekLast() == n) {
                        deque.pollLast();
                        n *= -2;
                    }
                }
                deque.addLast(n);
            }

            // 블록 위쪽으로 이동하는 효과
            int i = 0;
            while (!deque.isEmpty()) {
                int n = deque.pollFirst();
                if (n < 0) n *= -1;
                brr[i][j] = n;
                i++;
            }
        }

        // 이동한 결과 리턴
        return brr;
    }

    // 5번 반복
    static void solve(int k, int[][] arr) {
        if (k == 5) {
            findMax(arr);
            return;
        }

        // 배열 돌려가며 블록 움직이기
        for (int i = 0; i < 4; i++) {
            int[][] result = move(arr);
            solve(k + 1, result);
            arr = rotate(arr);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/12100.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        max = 0;
        int[][] arr = new int[N][N];

        // 입력 받기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                arr[i][j] = n;
            }
        }

        solve(0, arr);

        System.out.println(max);
    }
}
