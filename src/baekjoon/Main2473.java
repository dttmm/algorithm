package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 설계 33분 구현 17+25분 디버깅 10분
 3개를 골라야하는데 어떻게 하면 3개를 효율적으로 고를 수 있을까
 먼저 2개를 고르고 나머지 하나를 효율적으로 고르는 방법을 생각해봄
 문제의 특징이 숫자가 중복되지 않고 양수와 음수가 존재하니
 양수와 음수를 따로 저장하고
 양수에서 2개뽑고 움수에서 나머지 뽑고
 음수에서 2개뽑고 양수에서 나머지 뽑고
 하려고 했는데
 양수 음수 섞여있어도
 양수만 뽑거나 음수만 뽑았을 때 정답이 될 수 있는 경우가 있넹
 ex) -3 -2 -1 100

 다시 다른 방법 생각해냄
 조금 나이브하게
 숫자 정렬해서
 먼저 두개를 조합으로 뽑고
 뽑은 두개 사이의 숫자를 이분탐색으로 하나 뽑으면
 효율적으로 뽑을 수 있을 것으로 생각
 먼저 두개를 조합으로 뽑으면 5000*5000/2 = 1250만
 둘 사이에서 이분탐색으로 숫자 하나 뽑으면 log(5000) ~= 12
 1250 * 12는 1억이 넘어가지만
 처음에 두개를 어떻게 선택하느냐에 따라 이분탐색 경우의 수가 0이 될 수도 있기에
 대략 반띵한 1250 * 12 / 2 ~= 6천만 정도로 예상하고 풀음

 틀림
 선택한 3개의 수가 모두 크면
 int의 범위를 벗어날 수 있으므로
 long타입 신경써서 다시 풀음

 틀림
 이분 탐색을 할 때
 언제 왼쪽 구간을 탐색하고
 언제 오른쪽 구간을 탐색할지
 조건을 잘 못 세워서 틀림
 은근 헷갈린다
 */
public class Main2473 {

    static int N;
    static long minDiff;    // 0에 가까운 최소값
    static List<Integer> list;
    static List<Integer> answerList;    // 정답 담을 리스트
    static int[] tr;

    // 최소값 업데이트
    static void update(long diff, int n1, int n2, int n3) {
        if (diff < minDiff) {
            minDiff = diff;
            answerList = new ArrayList();
            answerList.add(n1);
            answerList.add(n2);
            answerList.add(n3);
        }
    }

    // 이분 탐색
    static void find(int start, int end, int sum, int n1, int n2) {
        if (start > end) return;

        int mid = (start + end) / 2;
        int midNum = list.get(mid);

        long total = (long) midNum + sum;

        // 지금 뽑은 3개로 최소값 만족하는지 검사함
        update(Math.abs(total), n1, n2, midNum);

        // 0을 만들기 위해서는 -1 * sum을 뽑아야 하는데
        // 뽑아야 하는 숫자보다 뽑은 숫자가 작은 경우 -> 오른쪽(수 더 큰 쪽) 탐색
        if (midNum < -1 * sum) {
            find(mid + 1, end, sum, n1, n2);
        }
        // 뽑아야 하는 숫자보다 뽑은 숫자가 큰 경우 -> 왼쪽(수 더 작은 쪽) 탐색
        else if (midNum > -1 * sum) {
            find(start, mid - 1, sum, n1, n2);
        }
    }

    // 조합으로 2개 고르기
    static void solve(int k, int start) {
        if (k == 2) {
            int sum = list.get(tr[0]) + list.get(tr[1]);
            // 고른 인덱스 기준으로 이분 탐색
            find(tr[0] + 1, tr[1] - 1, sum, list.get(tr[0]), list.get(tr[1]));
        } else {
            for (int i = start; i < list.size(); i++) {
                tr[k] = i;
                solve(k + 1, i + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2473.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        minDiff = Long.MAX_VALUE;
        list = new ArrayList();
        answerList = new ArrayList();
        tr = new int[2];

        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            list.add(n);
        }

        // 정렬
        Collections.sort(list);

        // 답 구하기
        solve(0, 0);

        // 정답 정렬
        Collections.sort(answerList);

        System.out.println(answerList.get(0) + " " + answerList.get(1) + " " + answerList.get(2));
    }
}
