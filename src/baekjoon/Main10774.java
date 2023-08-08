package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 설계 4분 구현 5분 디버깅 1분
 그리디
 먼저 가지고 있는 저지에 대한 정보를 저장함
 i번 저지의 사이즈를 sizeArr배열에 저장하고
 i번 저지를 누가 가져갔는지는 completed배열에 저장함

 학생들을 순회하면서
 조건에 맞는 저지가 있을 경우
 해당 학생에게 저지를 주었음

 틀림
 solve함수 인자 순서 반배로 전달해줌..
 */
public class Main10774 {

    static int J;
    static int A;
    static int[] sizeArr;           // i번 저지의 사이즈
    static boolean[] completed;     // 이미 i번 저지를 나눠주었는지 여부
    static Map<String, Integer> sizeMap;    // 사이즈 숫자로 변환 S:1 , M:2, L:3

    // 저지 할당
    static boolean solve(int num, int size) {
        // 이미 저지를 누가 가져간 경우
        if (completed[num]) return false;
        // 사이즈가 작은 경우
        if (size > sizeArr[num]) return false;

        completed[num] = true;
        return true;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10774.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        J = Integer.parseInt(br.readLine());
        A = Integer.parseInt(br.readLine());
        sizeArr = new int[J + 1];
        completed = new boolean[J + 1];

        // 초기화
        sizeMap = new HashMap();
        sizeMap.put("S", 1);
        sizeMap.put("M", 2);
        sizeMap.put("L", 3);

        // 저지 정보 입력 받기
        for (int i = 1; i <= J; i++) {
            String s = br.readLine();
            sizeArr[i] = sizeMap.get(s);
        }

        int count = 0;
        StringTokenizer st;
        // 학생 정보 입력 받기
        for (int i = 0; i < A; i++) {
            st = new StringTokenizer(br.readLine());
            String size = st.nextToken();
            int num = Integer.parseInt(st.nextToken());

            // 저지 할당
            boolean result = solve(num, sizeMap.get(size));
            if (result) count++;
        }

        System.out.println(count);
    }
}
