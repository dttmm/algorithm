#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_A 1000

int A;
int B;
int C;
int ans;
bool visited[MAX_A + 1];

void solve(int sum) {
	// C를 넘는 경우
	if (sum > C) return;

	// 이미 확인한 숫자인 경우
	if (visited[sum]) return;
	visited[sum] = true;

	ans = max(ans, sum);

	// A 사용
	solve(sum + A);
	// B 사용
	solve(sum + B);
}

int main() {

	// 입력
	cin >> A >> B >> C;

	solve(0);

	cout << ans;
}