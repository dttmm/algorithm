package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 해시를 이용하여
 지금까지의 합을 해시에 저장해주면
 지금까지의 합 - M이 해시에 있는지 체크
 해시에 해당 값이 있다면 M을 만들 수 있다는 뜻이므로 경우의수 +1해줌
 */
public class Main2003 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2003.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<Integer, Boolean> map = new HashMap();
        int sum = 0;
        map.put(sum, true);

        int answer = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());

            sum += n;
            if (map.containsKey(sum - M)) answer++;

            map.put(sum, true);
        }

        System.out.println(answer);
    }
}
