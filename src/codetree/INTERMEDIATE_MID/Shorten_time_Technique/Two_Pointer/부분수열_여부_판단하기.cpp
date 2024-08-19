#include <iostream>

using namespace std;

#define MAX_N 100000

int N;
int M;
int arr[MAX_N];
int brr[MAX_N];

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}
	for (int i = 0; i < M; i++) {
		cin >> brr[i];
	}

	int a = 0;
	int b = 0;
	while (a < N && b < M) {
		// 두 원소가 일치하는 경우 -> 다음 원소 탐색
		if (arr[a] == brr[b]) {
			a++;
			b++;
		}
		// 두 원소가 일치하지 않는 경우
		// b원소는 고정하고 다음 a원소 탐색
		else {
			a++;
		}
	}

	if (b == M) cout << "Yes";
	else cout << "No";
}