#include <iostream>
#include <fstream>
#include <algorithm>

/*
* 설계 7분 구현 9분 디버깅 2분
* 슬라이딩 윈도우
* 현재 지점에서 오른쪽으로 K만큼 떨어진 거리까지
* 잡을 수 있는 얼을의 개수와
* 왼쪽으로 K만큼 떨어진 거리까지
* 잡을 수 있는 얼을의 개수를 각각 구해서
* 오른쪽으로 잡을 수 있는 얼음 + 왼쪽으로 잡을 수 있는 얼음의 최대값을 구함
* 
* 오른쪽을 예로 들면
* 현재 위치를 포함해서 오른쪽으로 K만큼 떨어진 거리까지 얼음의 개수는
* 배열에서 현재위치포함 K+1개 만큼 연속적으로 선택한 것이므로
* 배열의 각 위치에서 오른쪽으로 K+1개 만큼 선택했을 때의 얼을의 개수를 totalR에 저장함
* 왼쪽은 totalL에 저장함
* 
* totalR, totalL모두 자신 위치의 얼음 개수가 중복으로 들어갔으므로
* 전체 좌표를 순회하면서
* totalR + totalL - 자신 위치의 얼음 개수 중에서 최대값을 고르면 됨
* 
* 틀림
* OutOfBounds남
* arr의 크기를 전체 좌표 범위의 MAX_X로 해줘야 되는데
* 입력 수만큼 MAX_N해주어서 틀림
*/

using namespace std;

#define MAX_N 100000
#define MAX_X 1000000

int N;
int K;
int arr[MAX_X + 1];
long long totalR[MAX_X + 1];
long long totalL[MAX_X + 1];

void solve() {
	long long total = 0;
	for (int x = 0; x <= MAX_X; x++) {
		int n = arr[x];

		if (x < K + 1) {
			total += n;
		}
		else {
			total += n;
			total -= arr[x - (K + 1)];
		}

		totalR[x] = total;
	}

	total = 0;
	for (int x = 0; x <= MAX_X; x++) {
		int n = arr[MAX_X - x];

		if (x < K + 1) {
			total += n;
		}
		else {
			total += n;
			total -= arr[MAX_X - x + (K + 1)];
		}

		totalL[MAX_X - x] = total;
	}
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	freopen("res/baekjoon/10025.txt", "r", stdin);

	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		int g;
		int x;
		cin >> g >> x;

		arr[x] = g;
	}

	solve();

	long long maxVal = 0;
	for (int x = 0; x <= MAX_X; x++) {
		maxVal = max(maxVal, totalR[x] + totalL[x] - arr[x]);
	}

	cout << maxVal;
}