#include <iostream>
#include <string>
#include <memory.h>

using namespace std;
/*
* in 1726 out 7699
*/

#define MAX_N 20

int N;
int M;
int arr[MAX_N][MAX_N];
bool visited[MAX_N][MAX_N];
int di[4] = { -1,0,0,1 };
int dj[4] = { 0,1,-1,0 };
int ans;

void init() {
	memset(visited, false, MAX_N * MAX_N);
	ans = 0;
}

bool isIn(int i, int j) {
	return i >= 0 && i < N&& j >= 0 && j < M;
}

void solve(int i, int j, int place, int cnt) {
	ans = cnt > ans ? cnt : ans;

	for (int dir = 0; dir < 4; dir++) {
		int newI = i + di[dir];
		int newJ = j + dj[dir];

		if (!isIn(newI, newJ)) continue;
		if (visited[newI][newJ]) continue;
		if ((place & (1 << arr[newI][newJ])) > 0) continue;

		visited[newI][newJ] = true;

		solve(newI, newJ, place | (1 << arr[newI][newJ]), cnt + 1);

		visited[newI][newJ] = false;
	}
}

int main() {

	int T;
	cin >> T;

	for (int tc = 1; tc <= T; tc++) {
		init();

		cin >> N >> M;
		for (int i = 0; i < N; i++) {
			string s;
			cin >> s;

			for (int j = 0; j < M; j++) {
				arr[i][j] = s[j] - 'A';
			}
		}

		visited[0][0] = true;
		solve(0, 0, 1 << arr[0][0], 1);

		cout << "#" << tc << " " << ans << "\n";
	}
}