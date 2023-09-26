#include <fstream>
#include <iostream>
#include <algorithm>

/*
* 설계 21분 구현 11분 디버깅헛발질 31분
* dp
* 약 1000개의 구간을 1번 잘라보고 2번 잘라보고... 1000번 잘라보고
* 경우의 수가 말이 안된다
* 특정 구간d[i][j]에서의 최대값은
* d[i][k]구간의 최대값 + d[k+1][j]구간의 최대값 (k=i~j-1)으로 구할 수 있음
* 
* 다만 단 하나의 구간 d[i][i]에서의 최대값은 0이고 d[i+1][i+1]에서의 최대값도 0이 되므로
* d[i][i+1]의 최대값은 0+0이 되어버림
* 그래서 쪼갠 구간들의 최대값을 구하고
* 쪼개지 않은 전체 구간인
* i ~ i+1까지 원소간 최대 차이를 마지막에 비교해줌
* 특정 구간 [i][j]에서의 원소간 최대 차이를 구하기 위해
* 해당 구간에서의 최대값, 최소값 원소를 maxArr, minArr 배열에 각각 담아줌
* 
* 근데 정답이 안나옴
* 구간을 쪼개서 돌릴 때 k를 j-1까지 돌려야 되는데
* j까지 돌리는 바람에 무한재귀가 일어났음
* 근데 k값을 고쳐도 답이 안나와서 뭐가 문제인지 삽질했는데
* 알고보니 내가 테케 정답을 착각하고 있었음
* 정답 잘 나오는데 틀린줄 알았음
* 이런.. 시간 날렸네
*/

using namespace std;

#define MAX_N 1000

int N;
int arr[MAX_N];
int d[MAX_N][MAX_N];		// i~j구간에서의 조가 잘 짜여진 정도의 최대값
int maxArr[MAX_N][MAX_N];	// i~j구간에서의 최대값 원소
int minArr[MAX_N][MAX_N];	// i~j구간에서의 최소값 원소

// dp
int solve(int i, int j) {
	// 이미 계산한 경우
	if (d[i][j] != -1) return d[i][j];
	// 자기 자신인 경우
	if (i == j) return d[i][j] = 0;

	// 구간을 자르면서 최대값 찾기
	int maxValue = 0;
	for (int k = i; k < j; k++) {
		maxValue = max(maxValue, solve(i, k) + solve(k + 1, j));
	}

	// 구간의 최대값과 양 끝 구간의 최대값 비교
	maxValue = max(maxValue, maxArr[i][j] - minArr[i][j]);
	return d[i][j] = maxValue;
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/2229.txt", "r", stdin);

	// 입력 받기
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// 초기화
	for (int i = 0; i < MAX_N; i++) {
		fill(d[i], d[i] + MAX_N, -1);
	}

	// i~j구간에서의 최대값, 최소값 구하기
	for (int i = 0; i < N; i++) {
		int maxValue = 0;
		int minValue = 10000;
		for (int j = i; j < N; j++) {
			maxValue = max(maxValue, arr[j]);
			minValue = min(minValue, arr[j]);

			maxArr[i][j] = maxValue;
			minArr[i][j] = minValue;
		}
	}

	// dp
	solve(0, N - 1);

	cout << d[0][N - 1];
}