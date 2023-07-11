package 공부.정렬_Sorting;

public class MergeSort {
    // 병합정렬은 별도의 저장공간이 필요하기 때문에
    // 공간이 없다면 퀵소트 필요

    static int[] arr;       // 원본 배열
    static int[] sorted;    // 정렬된 상태를 저장할 배열
    static int swap;

    // 재귀적으로 함수를 호출하고
    // 작은 조각들부터 정렬해서 합침
    // 한번 호출당 검색해야될 데이터가 반씩 줄어듦
    static void mergeSort(int start, int end) {
        // 시작부분이 끝 부분보다 작을 때만 실행 (크기가 1이면 패쓰)
        if (start >= end) return;

        // 배열을 자름
        int mid = (start + end) / 2;

        // 배열의 앞 부분
        mergeSort(start, mid);
        // 배열의 뒷 부분
        mergeSort(mid + 1, end);

        // 두 배열을 병함함
        merge(start, mid, end);
    }

    static void merge(int start, int mid, int end) {

        int i = start;      // 앞 배열의 포인터
        int j = mid + 1;    // 뒷 배열의 포인터
        int k = start;      // 결과를 저장할 배열의 포인터

        // 투포인터를 이용하여 병합
        // 작은 값을 결과 배열에 저장
        // 두 배열 중 하나의 포인터가 끝까지 갈 때까지 반복
        while (i <= mid && j <= end) {

            // 앞 배열이 작으면 결과 저장
            if (arr[i] <= arr[j]) {
                sorted[k] = arr[i];
                i++;

                // 응용 - 정렬하면서 몇 개의 데이터를 뛰어 넘었는가(몇 번의 swap이 일어났는가)
                // 데이터가       24 32 42 60 | 5 15 45 90 두 그룹으로 있을 때
                // 5의 경우 맨 처음으로 뽑히므로 24 32 42 60까지 총 4번을 거쳐서 처음으로 오게됨
                // 15의 경우에도 나머지 24 32 42 60까지 총 4번을 거쳐서 그 다음으로 오게됨
                // 정리해보면
                // 각각의 데이터는 0  0  0  0    4 4  1  0 번의 swap이 발생함

                // 뒷 배열의 원소가 앞 배열 원소 몇 개 뛰어 넘는지 (swap)
                // swap의 총 횟수 구할 수 있음
                swap += mid - i + 1;
            }
            // 뒷 배열이 작으면 결과 저장
            else {
                sorted[k] = arr[j];
                j++;
            }
            k++;
        }

        // 남아있는 데이터 정리
        // 한쪽 그룹의 데이터를 다 사용했다면
        // 나머지 그룹 데이터 처리
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

        // 정렬된 결과를 원본 배열에 저장
        for (int x = start; x <= end; x++) {
            arr[x] = sorted[x];
        }
    }
}
