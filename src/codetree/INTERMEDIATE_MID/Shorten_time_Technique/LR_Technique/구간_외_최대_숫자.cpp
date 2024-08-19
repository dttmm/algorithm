#include <iostream>

using namespace std;

#define MAX_N 100000
#define MAX(a, b) ((a) > (b) ? (a) : (b))

int arr[MAX_N + 2];
int L[MAX_N + 2];   // 1~i 구간의 최대값
int R[MAX_N + 2];	// i~N 구간의 최대값
int N;
int Q;

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> N >> Q;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}

	// L 세팅
	for (int i = 1; i <= N; i++) {
		L[i] = MAX(L[i - 1], arr[i]);
	}

	// R 세팅
	for (int i = N; i >= 1; i--) {
		R[i] = MAX(R[i + 1], arr[i]);
	}

	for (int q = 0; q < Q; q++) {
		int a, b;
		cin >> a >> b;

		// a~b를 제외한 구간에서의 최대값
		cout << MAX(L[a - 1], R[b + 1]) << "\n";
	}
}