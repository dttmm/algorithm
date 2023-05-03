package 공부.알고리즘.퀵정렬;

public class Main {

    static int[] arr;

    // 기준값(pivot)을 선정해 해당 값보다 작은 데이터와 큰 데이터로 분류 하는 반복하여 정렬
    // 기준값 선정에 따라 시간복잡도에 영향
    // 평균 시간복잡도는 O(nlogn)
    // 최악의 경우 O(n^2) <- 피벗이 계속 가장작은 값이거나 가장 큰 값인 경우

    // 스왑
    static void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // 데이터를 정렬하고 피벗이 있는 인덱스를 반환
    static int partition(int start, int end) {
        // + 중앙에 있는 값을 피벗으로 설정할 경우
        // 그냥 start에 있는 값을 바로 피벗으로 사용할 수도 있지만
        // 그냥 사용하는 경우보다 피벗최적화를 통해 성능 올릴 수 있고
        // 피벗최적화를 하지 않을 경우 시간이 터질수도 있음
        int mid = (start + end) / 2;
        swap(start, mid);

        // 데이터를 분할하는 피벗 설정
        int pivot = arr[start];

        // 투포인터 개념으로 정렬
        int i = start + 1;
        int j = end;

        // 오름차순 기준으로 정렬할 경우
        // 피벗을 기준으로 데이터를 2개 로 분리
        while (i <= j) {
            // 피벗보다 더 큰 데이터를 찾을 때까지 이동
            while (i <= end && arr[i] < pivot) i++;

            // 피벗보다 더 작은 데이터를 찾을 때까지 이동
            while (j > start && arr[j] > pivot) j--;

            // i는 end를 포함하고 j는 start를 포함하지 않는 이유
            // 만약 i가 end를 포함하지 않는다면
            // 데이터가 2 1, 피벗이 2, i와 j가 1을 가리키고 있는 경우
            // i <= j을 만족하게 되어 다음 구문을 실행하게 되면 j--가 되어버림
            // 결국 마지막에 start와 j가 같게되므로 정렬이 이루어지지 않음
            // i와 j가 엇갈릴 수 있도록 둘 중 하나는 엣지 값을 포함하도록 범위 설정을 해야됨
            // j는 마지막에 swap되므로 start보다 커야 정렬이 이루어 지지만
            // i는 마지막에 더이상 swap을 하지 않으므로 범위 벗어나도 상관 없음

            // 서로 엇갈리지 않은 경우 -> 원하는 데잍들을 잘 찾은 경우
            // 가리키는 데이터를 swap하고 한칸씩 이동
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }

        // 마지막에 j는 피벗보다 작은 값이므로
        // 피벗이 있는 위치와 swap하게 되면
        // 피벗 기준으로 왼쪽 그룹은 항상 피벗보다 작고
        // 오른쪽 그룹은 항상 피벗보다 크다는 것을 만족함
        swap(start, j);
        return j;
    }

    // 퀵정렬
    static void quickSort(int start, int end) {
        // 분리 집합이 1개 이하가 될 때까지 반복
        if (start >= end) return;

        // 피벗을 기준으로 데이터를 정렬하고
        // 피벗이 있는 인덱스를 반환 받음
        int pivotIndex = partition(start, end);

        // 이제 피벗이 있는 위치는 정렬된 위치임
        // 피벗을 기준으로 왼쪽 오른쪽을 분할정복하며 정렬 반복
        quickSort(start, pivotIndex - 1);
        quickSort(pivotIndex + 1, end);

    }
}
