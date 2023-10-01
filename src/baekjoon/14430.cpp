#include <iostream>
#include <fstream>
#include <algorithm>

/*
* 설계 1분 구현 2분
* dp
* 2차원 배열을 순회하면서
* 현재 지점까지 오는데 수집할 수 있는 광석의 최대개수를 저장함
* 갈 수 있는 방향이 오른쪽, 아래쪽뿐이므로
* 현재 위치에 오기위해 위쪽에서 왔을 때, 왼쪽에서 왔을 때
* 둘 중에 최대값을 골라가며 값을 갱신하고
* 현재 위치에 광석이 있는지 여부에 따라 추가적인 값을 갱신함
*/

using namespace std;

#define MAX_N 300

int N;
int M;
int arr[MAX_N + 1][MAX_N + 1];
int d[MAX_N + 1][MAX_N + 1];	// dp배열

// dp순회
void solve() {
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			// 위쪽, 왼쪽 중 최대값 + 현재 위치 광석 여부
			d[i][j] = max(d[i - 1][j], d[i][j - 1]) + arr[i][j];
		}
	}
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	freopen("res/baekjoon/14430.txt", "r", stdin);

	// 입력 받기
	cin >> N >> M;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			cin >> arr[i][j];
		}
	}

	// dp
	solve();

	cout << d[N][M];
}