#include <iostream>
#include <fstream>

/*
* in 2338 out 
* dp
* 상자마다 남길 공 r,g,b중 하나를 골라가며
* 순열을 이용해서 모든 경우의 수를 구한다면
* 3^50가지의 경우의 수 발생
* 중간에 가지치기를 하더라도 저격 가능한 테케가 많음
* 
* 바로 dp로 접근하려 생각함
* 상자를 하나씩 선택해가면서
* 상자에서 r,g,b를 남겼을 때의 최소값을 저장해나감
* 마지막 상자까지 골랐을 때는 상자들 중에서 적어도 r,g,b가 하나씩은 남겨져 있어야
* r,g,b를 마음껏 다른 상자로 옮길 수 있으므로
* r,g,b가 적어도 하나씩 선택되어있는지 상태를 하나더 추가해서 3차원 dp로 풀음
*/

using namespace std;

#define MAX_N 50
#define MIN(a, b) ((a) < (b) ? (a) : (b));
#define INF 2000000000

int N;
int arr[MAX_N + 1][3];	// i번째 박스의 j공 개수 j -> 0:r, 1: g, 2: b
int d[MAX_N + 1][3][8];	// i번째 박스에서 j공만 남겼을때의 최소값, k는 지금까지 어떤 공을 남겼는지 상태 표시함
// j -> 0:r, 1: g, 2: b
// k -> 1: r공 선택, 2: g공 선택, 3: r,g공 선택, 4: b공 선택... 7: r,g,b공 선택

// dp배열 초기화
void init() {
	for (int i = 0; i <= N; i++) {
		for (int j = 0; j < 3; j++) {
			for (int k = 0; k < 8; k++) {
				d[i][j][k] = INF;
			}
		}
	}
}

int solve() {
	d[0][0][0] = 0;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < 3; j++) {
			for (int k = 0; k < 8; k++) {
				// 현재까지 일어날 수 없는 상황은 패쓰
				if (d[i][j][k] == INF) continue;

				// 지금 상자에서 r만 남겼을 경우
				d[i + 1][0][k | (1 << 0)] = MIN(d[i + 1][0][k | (1 << 0)], d[i][j][k] + arr[i + 1][1] + arr[i + 1][2]);

				// 지금 상자에서 g만 남겼을 경우
				d[i + 1][1][k | (1 << 1)] = MIN(d[i + 1][1][k | (1 << 1)], d[i][j][k] + arr[i + 1][2] + arr[i + 1][0]);

				// 지금 상자에서 b만 남겼을 경우
				d[i + 1][2][k | (1 << 2)] = MIN(d[i + 1][2][k | (1 << 2)], d[i][j][k] + arr[i + 1][0] + arr[i + 1][1]);
			}
		}
	}

	// 마지막 상자(i=N)에서 r,g,b를 모두 남긴 경우(k=7)의 최소값 반환
	int ret = INF;
	for (int j = 0; j < 3; j++) {
		ret = MIN(ret, d[N][j][7]);
	}

	return ret;
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("src/swea/input.txt", "r", stdin);

	int T;
	cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		cin >> N;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < 3; j++) {
				cin >> arr[i][j];
			}
		}

		// 초기화
		init();

		int ret = solve();

		if (ret == INF) cout << "#" << tc << " -1\n";
		else cout << "#" << tc << " " << ret << "\n";
	}
}