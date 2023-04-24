package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 병합정렬 공부하고 풀어봄
 sorted 배열은 정렬된 상태를 저장하기 위해 사용하고
 sorted배열에 정렬된 상태를 이용하여 원본 배열을 정렬하는 군하
 결과적으로는 원본 배열도 정렬된 상태가 되는 군하
 원본 배열의 길이가 1이면
 아예 병합정렬이 수행이 안되는 군하
 sorted의 상태 말고 원본 배열의 상태를 출력해야 되는 군하
 */
public class Main11931 {

    static int N;
    static int[] arr;
    static int[] sorted;

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
            if (arr[i] >= arr[j]) {
                sorted[k] = arr[i];
                i++;
            } else {
                sorted[k] = arr[j];
                j++;
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
        System.setIn(new FileInputStream("res/baekjoon/11931.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        sorted = new int[N];

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            arr[i] = n;
        }

        mergeSort(0, N - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(arr[i] + "\n");
        }

        System.out.println(sb);
    }
}
