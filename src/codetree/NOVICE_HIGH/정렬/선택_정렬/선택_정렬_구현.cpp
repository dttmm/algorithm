#include <iostream>

using namespace std;

#define MAX_N 100

int N;
int arr[MAX_N];

// 스왑
void swap(int& a, int& b) {
	int temp = a;
	a = b;
	b = temp;
}

// 선택 정렬 수행
void solve() {
	for (int i = 0; i < N; i++) {

		// 전체 값중에 제일 작은(큰) 원소 찾음
		int minIndex = i;
		for (int j = i + 1; j < N; j++) {
			if (arr[j] < arr[minIndex]) minIndex = j;
		}

		// 가장 작은(큰) 원소를 앞쪽에서부터 차례대로 배치
		swap(arr[i], arr[minIndex]);
	}
}

int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	solve();

	for (int i = 0; i < N; i++) {
		cout << arr[i] << " ";
	}
}