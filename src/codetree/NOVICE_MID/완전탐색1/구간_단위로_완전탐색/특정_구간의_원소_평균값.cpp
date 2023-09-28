#include <iostream>

using namespace std;

#define MAX_N 100

int N;
int arr[MAX_N];

int main() {

	// 입력 받기
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	int ans = 0;
	// 구간을 먼저 정함 i~j
	for (int i = 0; i < N; i++) {
		for (int j = i; j < N; j++) {
			int sum = 0;

			// 구간안에 있는 원소들 더하기
			for (int h = i; h <= j; h++) {
				sum += arr[h];
			}

			// 평균 구하기
			double avg = sum / (double)(j - i + 1);

			// 다시 구간 돌면서 평균과 일치하는 원소 있는지 검사
			for (int h = i; h <= j; h++) {
				if (arr[h] == avg) {
					ans++;
					break;
				}
			}
		}
	}

	cout << ans;
}