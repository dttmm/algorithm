package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.TreeMap;

/**
 설계 2분 구현 7분
 처음에는 그냥 단순하게 완전 이진 트리의 높이를 구하면 되는 줄 알았음
 근데 10개의 노드를 입력으로 넣으니까
 출력이 30으로 엄청 작게 나옴

 다시 설계 21분 구현 13분
 dp + 누적합 + treemap
 뭐지? 하고
 직접 하나씩 노드 추가해보면서
 그림을 그려보았는데
 호곡!
 완전 이진트리가 아니더라도 트리의 높이를 키울 수 있다는 것을 발견
 모든 노드에 대해서 양쪽 서브트리의 높이 차이가 1이하가 되도록 설계만 하면 되는 거였음
 그래서 노드를 20개 정도만 직접 추가해보면서
 규칙을 찾으려고 노력한 결과
 피보나치 형태가 보이는 거임

 그래서 피보나치를 dp로 저장하고
 저장된 dp를 이용해서
 누적합을 구하고
 누적합 배열을 순회하면서
 입력으로 들어온 N이 속한 구간을 찾을 수 있지만

 N이 어느 누적합 구간에 해당하는지 빠르게 찾기 위해
 treemap을 이용하여 N보다 작거나 같은 수중에 최대(ceil)를 찾아서
 정답을 빠르게 찾음
 */
public class Main22968 {

    static int[] d;
    static int[] sum;
    static TreeMap<Integer, Integer> map;

    static void getF() {
        for (int i = 2; i <= 42; i++) {
            d[i] = d[i - 1] + d[i - 2];
        }
    }

    static void getSum() {
        for (int i = 2; i <= 42; i++) {
            sum[i] = sum[i - 1] + d[i];
        }
    }

    static void initMap() {
        for (int i = 1; i <= 42; i++) {
            map.put(sum[i], i);
        }
    }

    static int solve(int n) {
        int key = map.ceilingKey(n);
        return map.get(key);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/22968.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        d = new int[43];
        sum = new int[43];
        map = new TreeMap();
        d[0] = 1;
        d[1] = 1;
        sum[1] = 1;

        getF();

        getSum();

        initMap();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());

            int result = solve(N);

            sb.append(result + "\n");
        }

        System.out.println(sb);
    }
}
