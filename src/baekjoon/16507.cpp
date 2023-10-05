#include <fstream>
#include <iostream>

/*
* 설계 8분 구현 14분
* 누적합
* 쿼리가 많으므로 일일이 쿼리마다 밝기 평균을 계산하기 보다는
* 제일 상단인 (1, 1)부터 특정 지점 (i, j)까지의 밝기 총합을 미리 구해 놓으면
* 쿼리마다 일일이 밝기를 계산할 필요없어짐
* 그림을 그려서 누적합을 어떻게 갱신시킬지 생각하면 됨
*/

using namespace std;

#define MAX_N 1000

int N;
int M;
int Q;
int arr[MAX_N + 1][MAX_N + 1];
int sum[MAX_N + 1][MAX_N + 1];

// (1, 1)부터 (i, j)까지의 누적합 구하기
void setSum() {
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + arr[i][j];
		}
	}
}

// (i1, j1) 부터 (i2, j2)까지의 평균 구하기
int query(int i1, int j1, int i2, int j2) {
	int total = sum[i2][j2] - sum[i2][j1 - 1] - sum[i1 - 1][j2] + sum[i1 - 1][j1 - 1];
	int cnt = (i2 * j2) - (i2 * (j1 - 1)) - ((i1 - 1) * j2) + ((i1 - 1) * (j1 - 1));
	return (total / cnt);
}


int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/16507.txt", "r", stdin);

	// 입력
	cin >> N >> M >> Q;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			cin >> arr[i][j];
		}
	}

	// 누적합 계산
	setSum();

	// 쿼리 수행
	for (int i = 0; i < Q; i++) {
		int i1, j1, i2, j2;
		cin >> i1 >> j1 >> i2 >> j2;

		int ret = query(i1, j1, i2, j2);
		cout << ret << "\n";
	}
}