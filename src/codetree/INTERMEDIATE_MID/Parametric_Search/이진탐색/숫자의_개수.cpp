#include<iostream>

using namespace std;

#define MAX_N 100000
#define MIN(a, b) ((a) < (b) ? (a) : (b));

int N;
int M;
int arr[MAX_N];

int upper_bound(int x) {
	int s = 0;
	int e = N - 1;

	int index = N;
	while (s <= e) {
		int mid = (s + e) / 2;

		if (arr[mid] > x) {
			e = mid - 1;
			index = MIN(index, mid);
		}
		else {
			s = mid + 1;
		}
	}

	return index;
}

int lower_bound(int x) {
	int s = 0;
	int e = N - 1;

	int index = N;
	while (s <= e) {
		int mid = (s + e) / 2;

		if (arr[mid] >= x) {
			e = mid - 1;
			index = MIN(index, mid);
		}
		else {
			s = mid + 1;
		}
	}

	return index;
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

		int upper_index = upper_bound(x);
		int lower_index = lower_bound(x);

		cout << upper_index - lower_index << "\n";
	}
}