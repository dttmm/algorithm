package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 설계 5분 구현 16분 디버깅 6분
 약수
 나이브하게 생각하면
 for문 두번 돌려서 모든 수끼리 매칭을 시킬 수는 있음
 하지만 결국
 자신의 약수나 배수를 찾는 게임이므로
 어떤 수에서 1000000이하의 배수를 찾는 것보다는
 약수를 찾는 것이 더 빠르다고 판단하여
 주어진 입력을 순회하면서
 자신의 약수를 구한뒤
 해당 약수가 입력에 포하되어 있는지 확인한 후
 매칭 결과를 업데이트 해줌

 틀림
 약수를 구할 때
 1부터 시작해야되는데
 2부터 시작해버렸네
 가능한 숫자가 2이상인줄 알았는데
 알고보니 N이 2이상이었네
 머쓱타드

 띠용
 배수를 구하는 것이 약수를 구하는 것보다 5배정도 빠르네
 만약 100만 이하의 배수를 일일히 구하면
 1일때는 100만
 2일때는 50만
 3일때는 33만
 ...
 100만 일때는 1로
 숫자가 조금 변하면 연산횟수가 급격하게 줄어들어
 총 연산 횟수는 1400만 정도가 나온다고 하네

 반면 약수를 구하는 것은
 100만일때는 1000
 99만 9999일때는 999
 99만 9998일때는 999
 ...
 1일때는 1로
 각 숫자당 평균적인 연산횟수는 500으로
 최대 10만개의 숫자의 약수를 계산한다 치면
 연산횟수는 약 5천만번이 나오넹
 */
public class Main27172 {

    static int N;
    static int[] arr;
    static Set<Integer> set;
    static Map<Integer, Integer> map;

    static void solve() {
        for (int i = 0; i < N; i++) {
            int n = arr[i];
            int limit = (int) Math.sqrt(n);
            for (int x = 1; x <= limit; x++) {
                if (n % x != 0) continue;

                if (x * x != n) {
                    int y = n / x;
                    if (set.contains(y)) {
                        if (map.get(y) == null) map.put(y, 0);
                        map.put(y, map.get(y) + 1);
                        map.put(n, map.get(n) - 1);
                    }
                }

                if (set.contains(x)) {
                    if (map.get(x) == null) map.put(x, 0);
                    map.put(x, map.get(x) + 1);
                    map.put(n, map.get(n) - 1);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/27172.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        set = new HashSet();
        map = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
            set.add(n);
            map.put(n, 0);
        }

        solve();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int n = arr[i];
            sb.append(map.get(n) + " ");
        }

        System.out.println(sb);
    }
}
