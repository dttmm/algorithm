package baekjoon.Good_Bye_BOJ_2022;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 처음에는 K개의 주머니마다 배열(brr)을 만들었음
 brr에는 K개 주머니의 평균 값을 담고
 brr을 정렬한 후
 K = N ~ 1이 될때 까지
 brr[(k+1)/2] 값들 중에서 최대값을 찾았음
 근데 N이 100000일 경우
 매번 brr을 정렬하게되면 총 배열의 길이가 100000~1인 배열을 모두 정렬해야됨
 정렬하는데 걸리는 시간을 nlogn으로 잡고 배열 길이 평균을 50000으로 잡으면
 100000*log(100000)*50000으로 83억이 나옴

 그래서 매번 정렬하는 것보다 차라리 정렬된 자료구조를 계속 갖고 있어야 된다고 판단하여
 우선순위큐 사용함
 주머니를 합칠 때마다, 이전 주머니의 평균값과 합쳐진 주머니에 들어있던 수를 우선순위큐에서 빼주고
 새로 합친 주머니의 평균값을 주머니에 넣어주었음
 그리고 우선순위에서 (k+1)/2번째 수들 중에서 최대값을 찾았음
 하지만 이 방법도 시간초과
 N이 100000일 때
 우선순위큐에서 (k+1)/2번째까지 수를 빼고
 다시 뽑았던 수들을 우선순위큐에 넣게 되면
 50000번의 nlogn연산이 두번씩이나 일어나게됨
 50000*log(50000)*2
 (k+1)/2 평균을 50000/2로 잡으면
 50000*log(50000)*2*25000으로 39억이 나옴
 */
public class MainB {

    static int N;
    static int[] arr;
    static double[] avg;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/Good_Bye_BOJ_2022/B.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        avg = new double[N];
        PriorityQueue<Double> pq = new PriorityQueue<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int sum = 0;
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
            sum += n;
            pq.add((double) n);
            avg[i] = (double) sum / (i + 1);
        }

        double max = 0;
        Queue<Double> queue = new LinkedList();
        for (int k = 0; k < N; k++) {

            if (k > 0) {
                pq.remove(avg[k - 1]);
                pq.remove(arr[k]);
                pq.add(avg[k]);
            }

            int index = (pq.size() + 1) / 2;

            double target = 0;
            while (index > 0) {
                double n = pq.poll();
                queue.add(n);
                target = n;
                index--;
            }
            while (!queue.isEmpty()) pq.add(queue.poll());

            max = Math.max(max, target);
        }

        System.out.printf("%.6f", max);
    }
}
