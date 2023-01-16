package softeer.lv3;

import java.util.*;
import java.io.*;

/**
 처음에는 map에서 제일 낮은 key 뽑아서
 key를 하나씩 높여가며 비용산정을 했는데
 한 컴퓨터는 두 번 이상 업그레이드 불가!!!!!!! 발견!!!!!
 이말은 즉, 그냥 업그레이드 할 성능을 정하고
 해당 성능까지 업그레이드 하는데 필요한 총 비용이 예산을 넘지 않는 선을 찾으라는 건데...
 숫자를 보니 숫자 범위가 엄청 크다??
 바로 이분탐색으로 뚝딱뚝딱 요리함

 목표 성능을 mid로 잡고
 map에서 mid보다 작은 성능들 뽑아서
 비용 계산하고
 비용이 크면 mid더 작게 하고
 비용이 아직 넘지 않으면 answer값 갱신하고 mid값 키움

 아 그냥 편하게 long형 써야겠다
 mid값 int로 하니까 오버플로우 나네
 */
public class Main_004_슈퍼컴퓨터_클러스터 {

    public class Main {

        static int N;
        static long B;
        static Map<Integer, Integer> map;
        static long answer;

        static void solve(long start, long end) {
            if (start > end) return;

            long mid = (start + end) / 2;

            long sumB = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getKey() >= mid) break;
                sumB += (long) Math.pow(mid - entry.getKey(), 2) * entry.getValue();
                if (sumB > B) break;
            }

            // 예산을 넘어갈 경우
            if (sumB > B) {
                solve(start, mid - 1);
            } else {
                answer = mid;
                solve(mid + 1, end);
            }
        }

        public static void main(String args[]) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Long.parseLong(st.nextToken());
            map = new TreeMap();
            answer = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int key = Integer.parseInt(st.nextToken());
                if (map.containsKey(key)) map.put(key, map.get(key) + 1);
                else map.put(key, 1);
            }

            solve(1, 2000000000);
            System.out.println(answer);
        }
    }
}
