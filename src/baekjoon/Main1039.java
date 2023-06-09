package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 설계 35분 구현 28분
 최대값을 만들기 위한 어떤 규칙이 있지 않을까
 규칙을 찾으려고함
 제일 큰 수를 먼저 골라서 자리를 스왑한다던가..
 근데 중복된 숫자가 있으면 규칙이 복잡해지면서
 분기해야할 상황이 너무 많아짐

 완탐으로 할 수 있지 않을까
 최대 6자리이고 최대 10번 연산을 할 경우
 6개 중에서 2개를 골라서 스왑하면
 6개중 2개 고르는 경우 ^ 10
 15^10 ~= 5천억
 근데 백트래킹을 통해 중복을 제거한다면 충분히 가능성 있어 보임
 어떤 수에서 한번 연산을 했을 때 최대값 구하고
 거기서 또 한번 연산을 했을 때 최대값 구하고..
 이전 연산에서의 최대값을 활용하면서 최대값을 구했는데 예외가 발생함
 이전 최대값이 아니라 다른 수에서 스왑을 해야 최대값이 나오는 경우가 발견됨
 ex) 421888
    한번 연산했을 때 최대값은 821884
    두번 연산했을 때 최대값은 881842가 나와야 되는데
    이전 최대값인 821884에서는 한번 스왑으로 다음 최대값인 881842을 만들 수가 없음

 각 연산횟수에 따른 최대값은 구하는 것이 의미가 없는 것이고
 마지막 K연산일 때의 최대값만을 구해야만 함

 질문게시판에서 반례 찾다가
 bfs얘기가 나오길래
 bfs로 풀 수 있는 방법을 떠올림

 어떤 숫자에 대해 연산을 한번씩 수행하면서
 해당 숫자에 대해 k번째 연산은 이미 했다는 방문표시를 통해 bfs돌리고 다시 풀음
 다시 구현 35분
 */
public class Main1039 {

    static String N;
    static int M;
    static int K;
    static int max;                 // 찾을 최대값
    static int[] tr;                // 조합에서 뽑을 인덱스
    static Queue<Integer> queue;    // 검사할 숫자
    static Queue<Integer> queue_k;  // 검사할 숫자가 몇 번째 연산인지
    static Set<Integer>[] set;      // k번째 연산할 때, 이미 해당 숫자 검사했는지 중복 체크

    // 배열을 숫자로 변환
    static int arrayToInt(int[] brr) {
        int n = 0;
        int pow = 0;
        for (int i = brr.length - 1; i >= 0; i--) {
            n += Math.pow(10, pow) * brr[i];
            pow++;
        }
        return n;
    }

    // 숫자를 배열로 변환
    static int[] intToArray(int target) {
        String s = String.valueOf(target);
        int[] brr = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int n = c - '0';
            brr[i] = n;
        }
        return brr;
    }

    // 배열 스왑
    static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // 숫자에서 자릿수 2개 선택
    // depth: 몇 개 선택했는지, k: 몇 번째 연산인지
    static void pick(int depth, int start, int[] arr, int k) {
        // 2개 뽑았을 경우
        if (depth == 2) {
            // 스왑
            swap(arr, tr[0], tr[1]);

            // 0이 맨 앞인 경우 -> 다시 스왑 후 탈출
            if (arr[0] == 0) {
                swap(arr, tr[0], tr[1]);
                return;
            }

            // 자릿수 스왑한 숫자
            int n = arrayToInt(arr);

            // 이미 k번째 연산에서 해당 숫자 탐색했을 경우 -> 다시 스왑 후 탈출
            if (set[k].contains(n)) {
                swap(arr, tr[0], tr[1]);
                return;
            }

            set[k].add(n);
            queue.add(n);
            queue_k.add(k + 1);

            swap(arr, tr[0], tr[1]);
        } else {
            for (int i = start; i < M; i++) {
                tr[depth] = i;
                pick(depth + 1, i + 1, arr, k);
            }
        }
    }

    static void solve(int start) {
        queue.add(start);
        queue_k.add(0);

        while (!queue.isEmpty()) {
            int n = queue.poll();
            int k = queue_k.poll();

            // k번 연산한 경우
            if (k == K) {
                max = Math.max(max, n);
                continue;
            }

            // 해당 숫자에서 자릿수 2개 골라 스왑하기
            pick(0, 0, intToArray(n), k);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1039.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = st.nextToken();
        M = N.length();
        K = Integer.parseInt(st.nextToken());
        max = -1;
        tr = new int[2];
        queue = new LinkedList();
        queue_k = new LinkedList();
        set = new Set[K];

        for (int i = 0; i < K; i++) {
            set[i] = new HashSet();
        }

        solve(Integer.parseInt(N));

        System.out.println(max);
    }
}
