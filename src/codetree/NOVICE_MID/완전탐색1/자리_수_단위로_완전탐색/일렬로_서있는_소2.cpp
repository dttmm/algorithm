#include <iostream>
#include <string>

using namespace std;

#define MAX_N 100
#define R 3

int N;
int tr[R];
int arr[MAX_N];
int cnt;

void solve(int k, int start) {
	if (k == R) {
		if (tr[1] >= tr[0] && tr[2] >= tr[1]) cnt++;
	}
	else {
		for (int i = start; i < N; i++) {
			int n = arr[i];
			tr[k] = n;
			solve(k + 1, i + 1);
		}
	}
}

int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	solve(0, 0);

	cout << cnt;
}