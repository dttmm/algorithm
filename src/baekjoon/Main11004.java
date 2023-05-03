package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 퀵정렬 직접 구현해서 풀어봄
 여기저기서 자료 참고해서 로직 제대로 구현했는데
 자꾸 시간초과 남
 원인 모르겠음

 처음에는 피벗을 맨 앞에있는 숫자로 골랐었는데
 피벗 최적화가 필요할 수 있다는 글을 보고
 피벗을 가운데 있는 숫자로 골라서
 피벗 최적화를 해봄
 그랬더니 바로 통과쓰..
 신기허네..

 그냥 퀵정렬 했을 때는 1,644ms
 K값 찾기에 불필요한 범위 줄여가며 퀵정렬 했을 때는 1,188ms
 병합정렬 했을 때는 1,904ms 나옴
 K값을 이용해서 최적화를 할 수 있다는 점에서 퀵소트가 월씬 유리하고
 그냥해도 유리하넹

 Arrays.sort로 하면 시간 초과나네..?
 이것도 퀵정렬인데
 직접 구현한 퀵정렬보다 느리네..?
 */
public class Main11004 {

    static int N;
    static int K;
    static int[] arr;

    static void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    static int partition(int start, int end) {
        // 중앙에 있는 값을 피벗으로 설정
        int mid = (start + end) / 2;
        swap(start, mid);

        int pivot = arr[start];

        int i = start + 1;
        int j = end;

        while (i <= j) {
            while (i <= end && arr[i] < pivot) i++;
            while (j > start && arr[j] > pivot) j--;

            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }

        swap(start, j);
        return j;
    }

    static void quickSort(int start, int end) {
        if (start >= end) return;
        int pivotIndex = partition(start, end);

        // K값 찾기  최적화
        if (pivotIndex == K - 1) return;
        else if (pivotIndex > K - 1) {
            quickSort(start, pivotIndex - 1);
        } else {
            quickSort(pivotIndex + 1, end);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11004.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        quickSort(0, N - 1);

        System.out.println(arr[K - 1]);
    }
}
