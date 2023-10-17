#include <fstream>
#include <iostream>

/*
* 구현14분 디버깅 1분
* 힙 정렬
* 힙 정렬 배운거 적용해서 풀어봄
* 스왑할 때, 배열의 값을 넘겨줘야 하는데
* 인덱스만 넘겨주는 실수 조심
*/

using namespace std;

#define MAX_N 500000

int N;
int K;
int arr[MAX_N + 1];
int cnt;
int ans[2];

// 스왑
void swap(int& a, int& b) {
	int temp = a;
	a = b;
	b = temp;

	cnt++;
	if (cnt == K) {
		ans[0] = a < b ? a : b;
		ans[1] = a > b ? a : b;
	}
}

// 히피파이 수행
void heapify(int* arr, int n, int index) {
	if (cnt >= K) return;

	int min = index;	// 부모를 기준으로 잡음
	int left = index * 2;
	int right = index * 2 + 1;

	// 왼쪽 자식이 자신보다 작은지 검사
	if (left <= n && arr[left] < arr[min]) min = left;
	// 오른쪽 자식이 자신보다 작은지 검사
	if (right <= n && arr[right] < arr[min]) min = right;

	if (min != index) {
		swap(arr[min], arr[index]);
		heapify(arr, n, min);
	}
}

// 힙 정렬 수행
void heapSort(int* arr, int n) {
	//  힙 구조 만들기 -> 리프토드 제외하고 히피파이 수행
	for (int index = n / 2; index >= 1; index--) {
		if (cnt >= K) return;

		heapify(arr, n, index);
	}

	// 루프노드를 배열의 마지막으로 보내고 히피파이 반복 -> 내림차순 배열 만들어짐
	for (int size = n; size > 1; size--) {
		if (cnt >= K) return;

		swap(arr[1], arr[size]);
		heapify(arr, size - 1, 1);
	}
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/24173.txt", "r", stdin);

	// 입력
	cin >> N >> K;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}


	heapSort(arr, N);

	if (cnt < K) cout << -1;
	else cout << ans[0] << " " << ans[1];
}