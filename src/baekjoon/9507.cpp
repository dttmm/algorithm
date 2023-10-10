#include <fstream>
#include <iostream>

/*
* 구현 4분
* dp
* 재귀로할까 반복문으로 할까 고민하다
* 반복문으로 그냥 d배열 미리 다 구해놓고
* 입력 들어올 때마다 바로 출력해줌
*/

using namespace std;

#define MAX_N 67

int N;
long long d[MAX_N + 1];

// dp 배열 세팅
void setD() {
	d[0] = 1;
	d[1] = 1;
	d[2] = 2;
	d[3] = 4;

	for (int i = 4; i <= MAX_N; i++) {
		// 점화식
		d[i] = d[i - 1] + d[i - 2] + d[i - 3] + d[i - 4];
	}
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/9507.txt", "r", stdin);

	setD();

	// 입력
	int T;
	cin >> T;
	for (int tc = 0; tc < T; tc++) {
		cin >> N;

		cout << d[N] << "\n";
	}
}