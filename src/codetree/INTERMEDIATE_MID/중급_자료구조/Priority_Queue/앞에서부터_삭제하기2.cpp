#include<iostream>
#include<queue>

using namespace std;

#define MAX_N 100000

int N;
int arr[MAX_N];
priority_queue<int> pq;

int main() {

	cin >> N;
	// 입력 거꾸로 받기
	for (int i = 0; i < N; i++) {
		int index = N - 1 - i;
		cin >> arr[index];
	}

	double ans = 0;
	int sum = 0;

	// 기존 입력 순서기준 뒤에서 i번째 원소까지 선택했을 때 (앞에서부터 K객를 선택한 것과 동일)
	for (int i = 0; i < N; i++) {
		int n = arr[i];
		sum += n;
		pq.push(-1 * n);

		// 기존 입력 순서기준 맨 마지막 원소는 패쓰
		if (i == 0) continue;

		int min = pq.top() * -1;

		// 평균값 갱신
		double avg = (double)(sum - min) / i;
		ans = avg > ans ? avg : ans;
	}

	cout << fixed;
	cout.precision(2);

	cout << ans;
}