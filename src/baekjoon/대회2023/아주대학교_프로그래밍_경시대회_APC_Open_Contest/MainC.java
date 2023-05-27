package baekjoon.대회2023.아주대학교_프로그래밍_경시대회_APC_Open_Contest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 4분 구현 4분
 수를 배열에 넣고
 첫번째 수를 마지막 원소로 추가하고
 배열을 돌면서 이전 값보다 더 작거나 같은값 나온 경우 원탁쓰 실행

 같은 수가 연속되는 것도 정렬된 수인건지에 대한 정보가 없넹
 문제 설명에 디테일이 없어서 한번 틀렸네
 */
public class MainC {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/아주대학교_프로그래밍_경시대회_APC_Open_Contest/C.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }
        // 마지막 원소에 첫번째 값 넣음
        arr[N] = arr[0];

        // 정렬된 수인지 확인
        int answer = 0;
        int prev = arr[0];
        for (int i = 1; i <= N; i++) {
            if (arr[i] <= prev) answer++;

            prev = arr[i];
        }

        System.out.println(answer);
    }
}
