package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 실패
 처음에는 연속된 부분 합인줄 알았는데
 부분 수열이구나
 네이브하게 생각하면
 각 원소를 선택하냐 안하냐로 생각할 수 있는데
 그러면 경우의 수가 2^40..

 map을 이용해서 원소를 순차 탐색하면서
 해당 원소와 합쳤을 때 S가 되는 녀석을 map에서 찾고
 해당 원소를 합했을 때
 가능한 모든 경우의 수를 map에 업데이트 시킴

 틀림
 불필요한 중복을 줄여서 시간은 단축 시킬 수 있지만
 메모리 초과뜨네

 조더히 방법을 모르겠어서
 도움!

 아니 이걸 어떻게 떠올리냐 지쨔
 입력을 반으로 쪼개서 2^20연산 두번하고
 각 경우의 수를 합쳐서 S를 만족하는 경우를 뽑는다니..
 어렵다 어려워
 */
public class Main1208 {

    static int N;
    static int S;
    static int[] arr;
    static int[] brr;
    static Map<Integer, Long> mapA;
    static Map<Integer, Long> mapB;

    static void setMap(Map<Integer, Long> map, int[] ar, int k, int R, int sum) {
        if (k == R) {
            if (map.containsKey(sum)) map.put(sum, map.get(sum) + 1);
            else map.put(sum, 1L);
        } else {
            setMap(map, ar, k + 1, R, sum + ar[k]);
            setMap(map, ar, k + 1, R, sum);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1208.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N / 2];
        brr = new int[N - N / 2];
        mapA = new HashMap();
        mapB = new HashMap();

        // 반쪽 배열 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N / 2; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        // 나머지 배열 입력 받기
        for (int i = 0; i < N - N / 2; i++) {
            int n = Integer.parseInt(st.nextToken());
            brr[i] = n;
        }

        // 각 배열에서 가능한 경우의 수 구하기
        setMap(mapA, arr, 0, N / 2, 0);
        setMap(mapB, brr, 0, N - N / 2, 0);

        // 한 map에서 다른 map이랑 가능한 경우의 수 찾기
        long answer = 0;
        for (int key : mapA.keySet()) {
            if (mapB.containsKey(S - key)) answer += mapA.get(key) * mapB.get(S - key);
        }
        // 양쪽 map에서 아무것도 안 뽑았을 때 0이므로
        // S = 0일때 정답 카운트에 포함되어버림
        if (S == 0) answer--;

        System.out.println(answer);
    }
}
