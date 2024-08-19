#include <iostream>
#include<unordered_map>

using namespace std;

#define MAX_N 100000

int N;
int K;
int arr[MAX_N + 1];
int sum[MAX_N + 1];

int main() {

	cin >> N >> K;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
		sum[i] = sum[i - 1] + arr[i];
	}

	int maxVal = -100000000;
	for (int i = K; i <= N; i++) {
		int total = sum[i] - sum[i - K];
		maxVal = total > maxVal ? total : maxVal;
	}

	cout << maxVal;
}