#include<iostream>

using namespace std;

#define MAX_N 100000

int N;
int M;
int arr[MAX_N];

int solve(int x) {
	int s = 0;
	int e = N - 1;

	while (s <= e) {
		int mid = (s + e) / 2;

		if (arr[mid] == x) {
			return mid + 1;
		}
		else if (arr[mid] < x) {
			s = mid + 1;
		}
		else {
			e = mid - 1;
		}
	}

	return -1;
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	for (int i = 0; i < M; i++) {
		int x;
		cin >> x;

		int ret = solve(x);
		cout << ret << "\n";
	}
}