package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 설계 4분 구현 3분
 해시 정렬
 각 과목별 점수를 해시에 저장하고
 공개된 과목의 점수는 total에 저장
 나머지 과목 점수들을 정렬해서
 최소값과 최대값을
 공개되지 않는 과목의 개수(M-K)개 만큼 더함
 */
public class Main29723 {

    static int N;
    static int M;
    static int K;
    static Map<String, Integer> map;    // 점수 저장

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/29723.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new HashMap();

        // 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            int n = Integer.parseInt(st.nextToken());

            map.put(s, n);
        }

        // 공개된 점수 더하기
        int total = 0;
        for (int i = 0; i < K; i++) {
            String s = br.readLine();
            total += map.get(s);
            map.remove(s);
        }

        // 남아있는 과목들을 리스트에 저장하고
        List<Integer> list = new ArrayList();
        for (String s : map.keySet()) {
            list.add(map.get(s));
        }

        // 정렬
        Collections.sort(list);

        // 공개되지 않는 과목의 개수만큼
        // 최소값과 최대값을 구함
        int min = total;
        int max = total;
        for (int i = 0; i < M - K; i++) {
            min += list.get(i);
            max += list.get(list.size() - 1 - i);
        }

        System.out.println(min + " " + max);
    }
}
