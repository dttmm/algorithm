package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 어제 병합정렬 공부하다가 얻은 아이디어로 풀어봄
 병합 정렬에서 정렬을 수행할 때
 뒷 배열에 있는 원소가 선택된다면
 앞 배열에 남아있는 원소들의 수(mid - i + 1)만큼 swap이 일어남
 최대 swap 횟수는 50만 * 50만이어서 long으로 선언
 */
public class Main1517 {

    static int N;
    static int[] arr;
    static int[] sorted;
    static long answer;

    // 병합정렬
    static void mergeSort(int start, int end) {
        if (start >= end) return;

        int mid = (start + end) / 2;

        mergeSort(start, mid);
        mergeSort(mid + 1, end);

        merge(start, mid, end);
    }

    static void merge(int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int k = start;

        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                sorted[k] = arr[i];
                i++;
            } else {
                sorted[k] = arr[j];
                j++;
                answer += mid - i + 1;  // 뒷 배열의 원소가 앞 배열 원소 몇 개 뛰어 넘는지
            }
            k++;
        }

        while (i <= mid) {
            sorted[k] = arr[i];
            i++;
            k++;
        }

        while (j <= end) {
            sorted[k] = arr[j];
            j++;
            k++;
        }

        for (int x = start; x <= end; x++) {
            arr[x] = sorted[x];
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1517.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        sorted = new int[N];
        answer = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        mergeSort(0, N - 1);

        System.out.println(answer);
    }
}
