package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 여러개의 길이가 있고
 그 중에서 특정 길이를 고른다?
 바로 이분탐색을 떠올렸지만 아니었네
 그냥 단순 아이디어 문제였음

 일단 입력을 pq에 받아서
 큰 숫자를 우선으로 하나씩 뽑아서
 그 다음 숫자와 차이가 1이하인 경우에만
 하나의 변으로 선택하여
 변을 2개 고른 경우에 넓이를 정답에 추가해주었음
 */
public class Main16207 {

    static int N;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/16207.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            pq.add(n * -1);
        }

        long answer = 0;
        int count = 0;  // 첫번째 변인지 두번째 변인지 인덱스로 사용
        int[] length = new int[2];  // 변의 길이 저장
        while (!pq.isEmpty()) {

            int n1 = pq.poll() * -1;

            // 비교할 다음 대상이 없으면 패쓰
            if (pq.isEmpty()) break;
            int n2 = pq.peek() * -1;

            // 두 막대의 차이가 2이상이면 패쓰
            if (n1 - n2 > 1) continue;

            pq.poll();
            length[count] = n2;
            count = (count + 1) % 2;

            // 막대를 두 개 고른 경우 넓이 계산
            if (count == 0) answer += (long) length[0] * length[1];
        }

        System.out.println(answer);
    }
}
