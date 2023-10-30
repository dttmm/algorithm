#include <fstream>
#include <iostream>

/*
* 설계 16분 구현 12분
* dp
* 기존의 피보나치 수를 사용하여 -2를 구하려면
* f(-2) = f(-3) + f(-4)으로
* -2를 구하기 위해서는 -3과 -4를 알아야됨
* 그러면 -3과 -4를 알기 위해서는
* -5, -6, -7 ... 끝까지 알아야 되는 상황 발생
* 수식을 좀만 바꿔봤음
* 기존 수식
* f(n) = f(n-1) + f(n-2) 에서 f(n-1)을 넘기면
* f(n-2) = f(n) - f(n-1)이 되고 각 항에 2를 더하면
* f(n) = f(n-2) - f(n-1)이 됨
* 그럼 이제 -2를 구하기 위해서는
* 음수 부호를 생량하면
* 음수f(2) = f(0) - 음수f(1)이 됨
*
* 이제 양수일 때와 음수일 때의
* 피보나치 수와 dp배열을 따로 관리해주면 됨
*
* 재귀로 하려고 했는데 시간초과가 나서
* tabluation으로 dp 구함
*/

using namespace std;

#define MAX_N 1000000
#define MOD 1000000000

int N;
int d_plus[MAX_N + 1];	// 양수전용 dp
int d_minus[MAX_N + 1];	// 음수전용 dp

// 양수전용 dp 세팅
void setDPlus() {
	d_plus[0] = 0;
	d_plus[1] = 1;
	for (int i = 2; i <= MAX_N; i++) {
		d_plus[i] = (d_plus[i - 1] + d_plus[i - 2]) % MOD;
	}
}

// 음수전용 dp 세팅
void setDMinus() {
	d_minus[0] = 0;
	d_minus[1] = 1;
	for (int i = 2; i <= MAX_N; i++) {
		d_minus[i] = (d_minus[i - 2] - d_minus[i - 1]) % MOD;
	}
}


int main() {

	freopen("res/baekjoon/1788.txt", "r", stdin);

	// 입력
	cin >> N;

	// 양수 음수 구분
	int ans;
	if (N > 0) {
		setDPlus();
		ans = d_plus[N];
	}
	else {
		setDMinus();
		ans = d_minus[-1 * N];	// 음수 제거
	}

	// 양수 음수 구분 출력
	if (ans > 0)
		cout << 1 << "\n" << ans;
	else if (ans < 0)
		cout << -1 << "\n" << -1 * ans;
	else cout << 0 << "\n" << 0;
}