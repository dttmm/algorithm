package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 뭔가 해시를 사용해야 될 것 같은 느낌적인 느낌이 들어서
 해시를 어떻게 사용할까 머리를 굴림

 우선 원하는 구간 start~end가 교집합 없이 통째로 한 구간이랑 일치하는지 확인하고
 그 다음으로는 조건을 따져가며 구했음

 예를들어 예제처럼 구간이 0~10, 2~6, 4~8이 있는 경우
 0 1 2 3 4 5 6 7 8 9 10
 ----------------------
     ---------
         ---------
 4~6구간이 교집합인지 확인하기 위해서는
 먼저 (startSet에서) 4로 시작하는 구간이 있는지 확인
 그 중에서 6으로 끝나는 것이 있는지 확인 -> 1개 구간
 그 중에서 끝나는 구간이 6보다 큰 것이 있는지 확인
 (endSet에서) 6로 끝나는 구간이 있는지 확인
 그 중 시작 구간이 4보다 작은게 있는지 확인 -> 2개 구간

 이런 프로세스로 검사했음
 시작 구간을 기준으로 하였을 때와 끝나는 구간을 기준으로 하였을 때가 필요하기에
 set을 두 개 사용함
 그리고 나보다 크고 작은 놈이 있는지 확인하기 위해 treeSet사용함
 */
public class Main25393 {

    static int N;
    static int Q;
    static TreeSet<Integer>[] startSet;
    static TreeSet<Integer>[] endSet;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/25393.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        startSet = new TreeSet[1000001];
        endSet = new TreeSet[1000001];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if (startSet[start] == null) startSet[start] = new TreeSet();
            startSet[start].add(end);

            if (endSet[end] == null) endSet[end] = new TreeSet();
            endSet[end].add(start);
        }

        Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            // start로 시작하는게 있는지 확인
            if (startSet[start] == null) {
                sb.append("-1\n");
                continue;
            }

            // 그 중 end로 끝나는게 있는지 검사
            if (startSet[start].contains(end)) {
                sb.append("1\n");
                continue;
            }

            // 그 중 end보다 큰 end가 있는지 검사
            if (startSet[start].higher(end) == null) {
                sb.append("-1\n");
                continue;
            }

            // end가 6인 놈들이 있는지 확인
            if (endSet[end] == null) {
                sb.append("-1\n");
                continue;
            }

            // 그 중 start보다 작은 start가 있는지 검사
            if (endSet[end].lower(start) == null) {
                sb.append("-1\n");
                continue;
            }

            sb.append("2\n");
        }

        System.out.println(sb);
    }
}
