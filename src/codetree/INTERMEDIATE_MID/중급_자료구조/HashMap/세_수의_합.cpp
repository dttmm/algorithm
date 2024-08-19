#include <iostream>
#include <unordered_map>
using namespace std;

#define MAX_N 1000
int N;
int K;
int arr[MAX_N];
unordered_map<int, int> map;

int main() {
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		arr[i] = n;
		map[n]++;
	}

	int total = 0;

	// 앞에서부터 숫자 하나 뽑음
	for (int i = 0; i < N; i++) {
		int n = arr[i];
		map[n]--;
		int newK = K - n;

		// 뽑은 숫자보다 뒤에 있는 숫자 뽑음
		for (int j = i + 1; j < N; j++) {
			int m = arr[j];
			map[m]--;

			int target = newK - m;
			total += map[target];
			map[m]++;
		}
	}

	cout << total / 2;
}