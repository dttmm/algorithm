#include <iostream>

using namespace std;

#define MAX_X 200000
#define MAX(a, b) ((a) > (b) ? (a) : (b))

int N;
int arr[MAX_X + 1];

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> N;
	for (int i = 0; i < N; i++) {
		int a, b;
		cin >> a >> b;

		arr[a]++;
		arr[b]--;
	}

	// 누적합 계산
	for (int i = 1; i <= MAX_X; i++) {
		arr[i] = arr[i - 1] + arr[i];
	}

	int max = 0;
	for (int i = 1; i <= MAX_X; i++) {
		max = MAX(max, arr[i]);
	}

	cout << max;
}