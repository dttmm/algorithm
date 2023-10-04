#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_X 1000000
#define R 4
#define N 6

int arr[N];
int tr[R];
bool visited[N];
int ans = MAX_X * 10;
int total;	// 개발자 능력 총합

// 순열로 4개 뽑기
// 조합으로 4개 뽑으려고 했는데 뒤에서부터 뽑아버리면 앞쪽를 뽑을 수가 없어 경우의 수 다 돌 수 없음
void solve(int k) {
	if (k == R) {
		int sum1 = tr[0] + tr[1];	 // 앞 두팀 합
		int sum2 = tr[2] + tr[3];	// 뒤 두팀 합
		int sum3 = total - sum1 - sum2;	// 나머지 팀 합

		int maxVal = max(sum1, sum2);
		maxVal = max(maxVal, sum3);

		int minVal = min(sum1, sum2);
		minVal = min(minVal, sum3);

		int diff = maxVal - minVal;
		ans = min(ans, diff);
	}
	else {
		for (int i = 0; i < N; i++) {
			if (visited[i]) continue;

			visited[i] = true;
			int n = arr[i];
			tr[k] = n;
			solve(k + 1);
			visited[i] = false;
		}
	}
}

int main() {

	// 입력 받기
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
		total += arr[i];
	}

	// 순열
	solve(0);

	cout << ans;
}