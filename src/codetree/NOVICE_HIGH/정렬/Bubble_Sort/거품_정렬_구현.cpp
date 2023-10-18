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

// 버블 정렬 수행
void solve() {
	bool sorted = false;	// 이미 정렬되어있는지 체크 플래그

	while (!sorted) {	// 이미 정렬된 상태라면 탈출
		sorted = true;

		for (int i = 0; i < N - 1; i++) {
			if (arr[i] > arr[i + 1]) {	// 두 값을 비교하여 정렬 수행
				swap(arr[i], arr[i + 1]);
				sorted = false;
			}
		}
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