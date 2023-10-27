#include <iostream>
#include <fstream>
#include <algorithm>

/*
* 설계 22분 구현 12분 디버깅 1분
* dp
* 편의상 T대신 N사용함
* 문제의 관건은 각각의 n초일 때의 최대값을 알아내서
* dp를 이용하여 값을 갱신시켜야함
* 다만, n까지 왔을 때
* 1. 이전 위치에서 그대로 있었는지 or 다른 나무에서 왔는지
* 2. 지금까지 움직인 횟수
* 위 두가지 경우에 따라 뒤로 갈수록 상황이 달라지므로
* 위 두가지 경우를 함께 저장하면서
* 현재 n까지 왔을 때, 현재 상태로
* 다음 n+1로 갈때 일어날 수 있는 가능한 상황을 모두 찾아본 뒤
* n+1에서 어느나무에 몇번의 움직임으로 최대값을 얻을 수 있는지 갱신시켜나감
*/

using namespace std;

#define MAX_N 1000
#define MAX_W 30

int N;
int W;
int arr[MAX_N + 1];	// n초에 자두가 떨어질 나무 위치ㅣ
int d[MAX_N + 1][3][MAX_W + 1];	// n초에 나무tree에 w만큼의 움직임으로 왔을 때의 최대값

// d배열 초기화
void initD() {
	// 아직 모든 상황이 일어나지 않았다고 표시함
	for (int n = 1; n <= N; n++) {
		for (int tree = 1; tree <= 2; tree++) {
			for (int w = 0; w <= MAX_W; w++) {
				d[n][tree][w] = -1;
			}
		}
	}
}

// dp
void solve() {
	// 첫 시작 초기화
	// 처음에는 나무 1에서 시작하므로 나무 1에 있으면 움직인 횟수는 0임
	// 1초에 1번째나무에 0번의 움직임으로 얻을 수 있는 최대값은
	// 1초에 떨어지는 자두의 나무 위치가 1이면 1, 아니면 0임
	d[1][1][0] = (1 == arr[1]);
	// 처음에는 나무 1에서 시작하므로 나무 2에 있으면 움직인 횟수는 1임
	// 1초에 2번째나무에 1번의 움직임으로 얻을 수 있는 최대값은
	// 1초에 떨어지는 자두의 나무 위치가 2이면 1, 아니면 0임
	d[1][2][1] = (2 == arr[1]);

	// n초일 때 다음 n+1초에 가능한 상황을 찾아서 최대값을 업데이트함
	for (int n = 1; n < N; n++) {
		for (int tree = 1; tree <= 2; tree++) {
			for (int w = 0; w <= W; w++) {
				// 일어나지 않은 상황이면 이후 상황을 따질 수 없으니 패쓰
				if (d[n][tree][w] == -1) continue;

				// 현재 위치에 그대로 있을 때
				// 다음에 현재 있는 나무에 자두가 떨어진다면 현재값에 +1해줌
				d[n + 1][tree][w] = max(d[n + 1][tree][w], d[n][tree][w] + (tree == arr[n + 1]));

				// 다른 나무로 위치를 옮길 때
				// 다음에 옮긴 위치에 있는 나무에 자두가 떨어진다면 현재값에 +1해줌
				if (w + 1 <= W) {
					d[n + 1][3 - tree][w + 1] = max(d[n + 1][3 - tree][w + 1], d[n][tree][w] + ((3 - tree) == arr[n + 1]));
				}
			}
		}
	}
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	freopen("res/baekjoon/2240.txt", "r", stdin);

	// 입력
	cin >> N >> W;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}

	// d 초기화
	initD();

	// dp
	solve();

	// 마지막 N초일때 가능한 모든 상황에서 최대값 찾음
	int maxVal = 0;
	for (int tree = 1; tree <= 2; tree++) {
		for (int w = 0; w <= W; w++) {
			maxVal = max(maxVal, d[N][tree][w]);
		}
	}

	cout << maxVal;
}