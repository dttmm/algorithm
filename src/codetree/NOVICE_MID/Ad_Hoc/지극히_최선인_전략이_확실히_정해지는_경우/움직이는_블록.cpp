#include <iostream>
#include <algorithm>
#include <cstdlib>

using namespace std;

#define MAX_N 10000

int N;
int arr[MAX_N];

int main() {

	cin >> N;

	int sum = 0;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
		sum += arr[i];
	}

	// 평균이 되려면 얼마가 필요한지 총합 계산
	int avg = sum / N;
	int diff = 0;
	for (int i = 0; i < N; i++) {
		diff += abs(arr[i] - avg);
	}

	cout << diff / 2;
}