#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_X 10000
#define INF 100000000

int X;
int d[MAX_X][MAX_X + 1];	// i미터 jms일 때 걸린 시간 최소값

int solve(int i, int j) {
	if (i < 0) return INF;	//	범위를 벗어나는 경우
	if (j == 0) return INF;	// 0ms인 경우

	if (d[i][j] != 0) return d[i][j];

	// 이전에 속도를 줄인경우, 속도를 유지한 경우, 속도를 높인 경우중 최소값 구하고
	int minVal = min(solve(i - (j - 1), j - 1), solve(i - j, j));
	minVal = min(minVal, solve(i - (j + 1), j + 1));

	// 최소값에 +1초 해줌
	return d[i][j] = minVal + 1;
}

int main() {

	// 입력
	cin >> X;

	// 첫 시작점 세팅
	d[0][1] = 1;

	int ret = solve(X, 1);

	cout << ret;
}