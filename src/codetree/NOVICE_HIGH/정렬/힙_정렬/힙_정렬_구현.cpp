#include <iostream>

using namespace std;

#define MAX_N 100000

int N;
int arr[MAX_N + 1];

// 스왑
void swap(int& a, int& b) {
	int temp = a;
	a = b;
	b = temp;
}

// 히피파이 수행
void heapify(int n, int index) {
	int min = index;	// 부모를 기준
	int left = index * 2;
	int right = index * 2 + 1;

	// 왼쪽 자식이 부모보다 작은지 검사
	if (left <= n && arr[left] < arr[min]) min = left;
	// 오른쪽 자식이 부모보다 작은지 검사
	if (right <= n && arr[right] < arr[min]) min = right;

	// 자식중에 최소값이 있을 꼉우
	if (min != index) {
		swap(arr[min], arr[index]);
		heapify(n, min);
	}
}

// 힙 정렬 수행
void heapSort(int n) {
	// 리프 노드를 제외한 부모 노드에서 수행
	for (int i = n / 2; i >= 1; i--) {
		heapify(n, i);
	}
}

int main() {

	// 입력
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}

	// 정렬
	heapSort(N);

	// 오름차순 출력
	for (int n = N; n >= 1; n--) {
		int min = arr[1];

		swap(arr[1], arr[n]);
		heapify(n - 1, 1);

		cout << min << " ";
	}
}