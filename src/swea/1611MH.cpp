#include <iostream>
#include <queue>
#include <map>

using namespace std;

/*
* in 1611 out
*/

#define MAX_N 200

queue<pair<int, int> > q;
map<int, int> Map;
int arr[MAX_N][MAX_N];
int visited[MAX_N][MAX_N];
int di[6] = { 0, 1, 1, 0, -1, -1 };
int dj[6] = { 1, 1, 0, -1, -1, 0 };

void preprocessing() {
	int num = 1;
	for (int i = 0; num <= 10000; i++) {
		for (int j = 0; j <= i & num <= 10000; j++) {
			arr[i][j] = num++;
		}
	}

	int total = 1;
	Map[1] = 0;
	for (int i = 1; total <= 10000; i++) {
		total += i;
		Map[total] = i;
	}
}

void init() {
	q = queue<pair<int, int> >();

	for (int i = 0; i < MAX_N; i++) {
		for (int j = 0; j < MAX_N; j++) {
			visited[i][j] = 0;
		}
	}
}

pair<int, int> getPos(int n) {
	auto it = Map.upper_bound(n);
	it--;

	int i = (*it).second;
	int j = n - (*it).first;

	return make_pair(i, j);
}

bool isIn(int i, int j) {
	return i >= 0 && j >= 0;
}

int solve(pair<int, int> start, pair<int, int> target) {
	q.push(start);
	visited[start.first][start.second] = 1;

	while (!q.empty()) {
		auto it = q.front();
		q.pop();

		int i = it.first;
		int j = it.second;

		if (i == target.first && j == target.second) return visited[i][j] - 1;

		for (int dir = 0; dir < 6; dir++) {
			int newI = i + di[dir];
			int newJ = j + dj[dir];

			if (!isIn(newI, newJ)) continue;
			if (arr[newI][newJ] == 0) continue;
			if (visited[newI][newJ] != 0) continue;

			visited[newI][newJ] = visited[i][j] + 1;
			q.push(make_pair(newI, newJ));
		}

	}
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	preprocessing();

	int T;
	cin >> T;

	for (int tc = 1; tc <= T; tc++) {
		int a, b;
		cin >> a >> b;

		init();

		int ret = solve(getPos(a), getPos(b));

		cout << ret << "\n";
	}

}