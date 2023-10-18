#include <iostream>

using namespace std;

#define MAX_N 100

int N;
int arr[MAX_N];

// 삽입 정렬 수행
void solve() {

	// i이전의 원소들은 이미 정렬된 상태임
	for (int i = 1; i < N; i++) {
		int j = i - 1;
		int key = arr[i];	// 키로 사용할 원소 따로 빼둠

		// 나보다 작거나 같은 원소 앞에서 멈춤
		while (j >= 0 && arr[j] > key) {
			arr[j + 1] = arr[j];
			j--;
		}

		arr[j + 1] = key;
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