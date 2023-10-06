#include <iostream>
#include <fstream>
#include <algorithm>
#include <climits>

/*
* 설계 7분 구현 9분
* 그리디
* N과 거리가 크므로
* 안테나를 설치할 때마다
* 해당 위치에서 모든 집들까지의 거리를 계속 구해주는 것은 불가능함
* 1위치부터 시작하여 모든 지점에 안테나를 설치한다면
* i위치에서 i+1위치에 안테나를 설치했을 때
* 거리 합이 어떻게 변하는지 분석하여 패턴을 찾아냄
* 
* i위치에 안테나가 있었을 때, i+1위치에 안테나를 설치한다면
* 안테나 왼쪽에 있는 집들의 수(left) 만큼 전체 거리 합이 늘어나고
* 안테나 오른쪽에 있는 집들의 수(right) 만큼 전체 거리가 줄어드는 것을 발견
* 안테나를 오른쪽으로 옮겨 설치해가면서
* 안테나가 있는 위치에 도달하면
* 해당 위치에 있는 집 개수만큼 left, right를 조절해감
* 마지막에 최소값이 있는 인덱스를 찾으면 바로 정답으로 반환함
* 
* 오잉
* 근데 안테나는 집이 있는 위치에만 설치할 수 있는데
* 나는 그걸 고려 안했는데 정답이네..?
* -> 어차피 집이 있는 위치에 안테나가 있어야지
* 안테나와 해당 집과의 거리는 0이되어
* 최소값을 만족하게 되는구나
*/

using namespace std;

#define MAX_N 200000
#define MAX_X 100000

int N;
int arr[MAX_X];			// i위치에 집이 몇개 있는지
long long d[MAX_N + 1];	// 안테나가 i위치에 있을 때, 안테나와 모든 집들의 거리 합
int total;

int solve() {
	// 안테나를 0위치에 두고 값을 초기화하고 시작
	int left = 0;	// 현재 안테나 왼쪽에 있는 집의 개수
	int right = N;	// 현재 안테나 오른쪽에 있는 집의 개수
	d[0] = total;	// 안테나가 0위치에 있을 때, 안테나와 모든 집들의 거리 합

	long long minVal = LLONG_MAX;
	for (int i = 1; i <= MAX_X; i++) {
		d[i] = d[i - 1] + (left - right);

		if (arr[i] != 0) {
			left += arr[i];
			right -= arr[i];
		}

		minVal = min(minVal, d[i]);
	}

	// 최소값 만족하는 위치 찾기
	for (int i = 1; i <= MAX_X; i++) {
		if (d[i] == minVal) return i;
	}

	return -1;
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	freopen("res/baekjoon/18310.txt", "r", stdin);

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		arr[n]++;
		total += n;
	}

	int ret = solve();

	cout << ret;
}