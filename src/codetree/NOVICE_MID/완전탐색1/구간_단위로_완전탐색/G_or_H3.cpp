#include <iostream>
#include <cmath>

using namespace std;

#define MAX_X 10000

int N;
int K;
int arr[MAX_X + 1];

int main() {

	// 입력 받기
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		int x;
		char c;
		cin >> x >> c;
		if (c == 'G') arr[x] = 1;
		else if (c == 'H') arr[x] = 2;
	}

	int ans = 0;
	int sum = 0;
	// 구간 순회
	for (int i = 1; i <= MAX_X; i++) {
		// K+1개를 뽑을 때 까지는
		// 지금까지 뽑은 것들 합계 구함
		if (i <= K + 1) {	// 양쪽 구간의 차가 K이니까 이말은 i에서부터 총 K+1개를 뽑았다는 의미임
			sum += arr[i];
			ans = max(ans, sum);
			continue;
		}

		// 이후로는 전진하면서 현재 것은 더하고
		sum += arr[i];
		// K+1전꺼는 뺌
		sum -= arr[i - (K + 1)];
		ans = max(ans, sum);
	}

	cout << ans;
}