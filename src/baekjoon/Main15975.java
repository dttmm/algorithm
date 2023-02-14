package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 각 색깔별로 위치 정보를 리스트에 담을까 트리셋에 담을까 고민함
 트리셋에 담을 경우 내 앞뒤에 있는 위치 구하기는 쉽지만
 앞뒤 위치 탐색하는 시간이 그리 효율적이지 못함
 리스트의 경우 바로 앞뒤 인덱스에 접근하면 되지만
 구현하고 조건을 따져야할 것이 많아 까다로움
 효율 생각해서 리스트로 구현함

 각 색깔에 해당하는 위치를 리스트에 넣고
 리스트를 돌면서
 해당 위치의 앞뒤 위치를 구해서
 앞뒤 위치와의 차이의 최소값을 구해주었음
 단, 따질 조건이 몇 개 있음
 먼저 해당 위치의 인덱스가 0이고 리스트의 크기가 1인 경우는
 비교할 다른 위치가 없으므로 패쓰함
 그 다음, 해당 위치의 인덱스가 0인 경우는 비교할 위치가 오른쪽에 있는 녀석 밖에 없음
 그 다음, 해당 위치의 인덱스가 마지막인 경우는 비교할 위치가 왼쪽에 있는 녀석 밖에 없음
 나머지는 양옆에 있는 녀석들이랑 비교해서 최소값 찾음
 */
public class Main15975 {

    static int N;
    static Map<Integer, List<Integer>> map;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/15975.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new HashMap();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int position = Integer.parseInt(st.nextToken());
            int color = Integer.parseInt(st.nextToken());

            // 각 숫자에 해당하는 위치 정보 넣어줌
            if (map.containsKey(color)) {
                map.get(color).add(position);
            } else {
                map.put(color, new ArrayList());
                map.get(color).add(position);
            }
        }

        long answer = 0;

        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            // 각 숫자의 위치정보 리스트를 꺼내서
            List<Integer> list = entry.getValue();
            // 정렬해주고
            Collections.sort(list);

            // 리스트 돌면서
            for (int position : list) {
                // 최소값 찾음
                int min = Integer.MAX_VALUE;
                int index = list.indexOf(position);

                // 당 위치의 인덱스가 0이고 리스트의 크기가 1인 경우는 비교할 다른 위치가 없음
                if (index == 0 && list.size() == 1) continue;

                // 해당 위치의 인덱스가 0인 경우는 비교할 위치가 오른쪽에 있는 녀석 밖에 없음
                if (index == 0) min = Math.min(min, list.get(index + 1) - position);

                // 해당 위치의 인덱스가 마지막인 경우는 비교할 위치가 왼쪽에 있는 녀석 밖에 없음
                else if (index == list.size() - 1) min = Math.min(min, position - list.get(index - 1));

                // 나머지는 양옆에 있는 녀석들이랑 비교해서 최소값 찾음
                else {
                    min = Math.min(min, list.get(index + 1) - position);
                    min = Math.min(min, position - list.get(index - 1));
                }

                answer += min;
            }
        }
        System.out.println(answer);
    }
}
