#include <fstream>
#include <iostream>
#include <algorithm>

/*
* 설계 2분 구현 3분
* dp
* 하나의 아이템을 여러번 사용할 수 있으므로
* 이전 dp배열에서 각 아이템을 추가로 사용해보면서
* 그 중에서 최대값을 찾음
*/

using namespace std;

#define MAX_N 1000

int N;
int p[MAX_N + 1];	// Pi값 저장
int d[MAX_N + 1];	// i장을 구매했을 때 최대값

// dp열 세팅(바텀업)
void setD() {
	for (int i = 1; i <= N; i++) {
		int maxVal = 0;

		// (이전 카드들을 구매했을 때의 최대 금액 + 더 구매해야 하는 금액)의 최대값 구함
		for (int j = i - 1; j >=0; j--) {
			maxVal = max(maxVal, d[j] + p[i - j]);
		}

		d[i] = maxVal;
	}
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/11052.txt", "r", stdin);

	// 입력
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> p[i];
	}

	setD();

	cout << d[N];
}