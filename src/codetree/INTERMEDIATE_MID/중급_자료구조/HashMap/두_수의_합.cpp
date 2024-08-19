#include <iostream>
#include <unordered_map>
using namespace std;

#define MAX_N 100000

int N, K;
int arr[MAX_N];
unordered_map<long long, long long> map;

int main() {

	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		long long n;
		cin >> n;

		arr[i] = n;
		map[n]++;
	}

	int total = 0;
	for (int i = 0; i < N; i++) {
		int n = arr[i];
		long long nn = (long long)K - n;

		total += map[nn];
		if (n == nn) total--;
	}

	cout << total / 2;
}