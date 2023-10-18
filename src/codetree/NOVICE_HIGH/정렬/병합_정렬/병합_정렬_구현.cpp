#include <iostream>

using namespace std;

#define MAX_N 100000

int N;
int arr[MAX_N];
int sorted[MAX_N];

// 머지하기
void merge(int left, int mid, int right) {
	// 각 리스트의 원소 위치
	int i = left;
	int j = mid + 1;

	int k = left;	// 병합시 원소를 담을 위치
	// 둘 중 작은(큰) 원소를 정렬된 배열에 저장
	while (i <= mid && j <= right) {
		if (arr[i] < arr[j])
			sorted[k++] = arr[i++];
		else
			sorted[k++] = arr[j++];
	}

	// 남은 원소들 옮겨줌
	while (i <= mid)sorted[k++] = arr[i++];
	while (j <= right)sorted[k++] = arr[j++];

	// 원본 배열에 정렬 상태 복사
	for (int x = left; x <= right; x++) {
		arr[x] = sorted[x];
	}
}

// 머지 정렬 수행
void mergeSort(int left, int right) {
	if (left >= right) return;
	int mid = (left + right) / 2;

	// 두 부분으로 나눠서 정렬 수행 후
	mergeSort(left, mid);
	mergeSort(mid + 1, right);

	// 두 부분 함치기
	merge(left, mid, right);
}

int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		arr[i] = n;
	}

	mergeSort(0, N - 1);

	for (int i = 0; i < N; i++) {
		cout << arr[i] << " ";
	}
}