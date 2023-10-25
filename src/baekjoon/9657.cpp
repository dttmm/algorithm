#include <fstream>
#include <iostream>

/*
* 설계 2분 구현 5분
* dp
* 이틀전에 dp 공부한 거 적용해봄
* 승부를 가리는 게임에도 적용할 수 있다는 게 놀랍다
* 현재 차례에 선수가 이기는지 판단하기 위해서는
* 현재 차례에 선수인 사람은 이전 차례에서는 후자가 되니까
* 이전 차례에 후자가 한번이라도 이기는지 확인해야됨
* 완벽하게 게임을 하니까 하나라도 이기는 경우가 있으면 이기는 쪽을 선택하게됨
*/

using namespace std;

#define MAX_N 1000

int N;
int d[MAX_N + 1];	// i개 돌이 있을 때 이기는 사람. 1: 선수, 0: 후자

// dp 배열 세팅
void solve() {
	// 돌이 1, 3, 4개밖에 없다면 먼저하는 사람이 이김
	d[1] = 1;
	d[3] = 1;
	d[4] = 1;

	for (int i = 5; i <= N; i++) {
		// 이전 게임에서 후자가 이기는 경우가 하나라도 있으면 됨
		if (d[i - 1] == 0 || d[i - 3] == 0 || d[i - 4] == 0) d[i] = 1;
	}
}

int main() {

	freopen("res/baekjoon/9657.txt", "r", stdin);

	// 입력
	cin >> N;

	solve();

	if (d[N] == 1) cout << "SK";
	else cout << "CY";
}