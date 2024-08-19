#include <iostream>
#include <queue>
#include <tuple>
#include <string>

using namespace std;

/*
* in 5
*/

#define MAX_N 100

int N;
int arr[MAX_N][MAX_N];
bool visited[MAX_N][MAX_N];
int di[] = { -1,0,0,1 };
int dj[] = { 0,1,-1,0 };

// �ʱ�ȭ
void init() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			visited[i][j] = false;
		}
	}
}

// ���� üũ
bool isIn(int i, int j) {
	return i >= 0 && i < N&& j >= 0 && j < N;
}

int solve() {
	priority_queue<tuple<int, int, int> > pq;
	pq.push(make_tuple(0, 0, 0));
	visited[0][0] = true;

	while (!pq.empty()) {
		auto it = pq.top();
		pq.pop();

		// ���� �������� ���µ� �ʿ��� cost �ּҰ�
		int i, j, cost;
		tie(cost, i, j) = it;
		cost *= -1;

		visited[i][j] = true;

		// ������ ����
		if (i == N - 1 && j == N - 1) return cost;

		// 4���� Ž��
		for (int dir = 0; dir < 4; dir++) {
			int newI = i + di[dir];
			int newJ = j + dj[dir];

			// ������ ��� ��� �о�
			if (!isIn(newI, newJ))continue;
			// �̹� �湮�� ��� �о�
			if (visited[newI][newJ]) continue;

			// �ش� ��ǥ�� �̵��ϱ����� �� cost ���
			pq.push(make_tuple(-1 * (cost + arr[newI][newJ]), newI, newJ));
		}
	}

	return -1;
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	int T;
	cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		cin >> N;

		for (int i = 0; i < N; i++) {
			string s;
			cin >> s;

			for (int j = 0; j < N; j++) {
				char c = s[j];
				arr[i][j] = c - '0';
			}
		}

		// �ʱ�ȭ
		init();

		int ret = solve();

		cout << "#" << tc << " " << ret << "\n";
	}
}