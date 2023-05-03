package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 병합정렬로도 풀어서
 퀵정렬과 속도 비교해봄
 */
public class Main11004_2 {

    static int N;
    static int K;
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
            if (arr[i] < arr[j]) {
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
        System.setIn(new FileInputStream("res/baekjoon/11004.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        sorted = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        mergeSort(0, N - 1);

        System.out.println(arr[K - 1]);
    }
}
