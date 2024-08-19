#include <iostream>
#include <set>
#include <map>
#include <vector>

using namespace std;

#define MAX_N 1000

int N;
int Q;
vector<pair<int, int> > inputs;
set<int> Set;
map<int, int> Map;
int cnt = 1;
int arr[2 * MAX_N + 1][2 * MAX_N + 1];
int sum[2 * MAX_N + 1][2 * MAX_N + 1];

void initSum() {
	for (int i = 1; i < cnt; i++) {
		for (int j = 1; j < cnt; j++) {
			sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + arr[i][j];
		}
	}
}

int getSum(int a, int b, int c, int d) {
	auto it = Map.lower_bound(a);
	if (it == Map.end())return 0;
	a = (*it).second;

	it = Map.lower_bound(b);
	if (it == Map.end())return 0;
	b = (*it).second;

	it = Map.upper_bound(c);
	if (it != Map.begin())it--;
	else it = Map.end();
	if (it == Map.end())return 0;
	c = (*it).second;

	it = Map.upper_bound(d);
	if (it != Map.begin())it--;
	else it = Map.end();
	if (it == Map.end())return 0;
	d = (*it).second;

	return sum[c][d] - sum[a - 1][d] - sum[c][b - 1] + sum[a - 1][b - 1];
}

int main() {

	cin >> N >> Q;

	// 입력 저장
	for (int i = 0; i < N; i++) {
		int x, y;
		cin >> x >> y;

		inputs.push_back(make_pair(x, y));
	}

	// 좌표 압축
	for (auto it : inputs) {
		Set.insert(it.first);
		Set.insert(it.second);
	}

	for (int it : Set) {
		Map[it] = cnt++;
	}

	// 압축한 좌표에 점 표시
	for (auto it : inputs) {
		int x = Map[it.first];
		int y = Map[it.second];

		arr[x][y]++;
	}

	// 점 누적 개수 구하기
	initSum();

	for (int q = 0; q < Q; q++) {
		int a, b, c, d;
		cin >> a >> b >> c >> d;

		int ret = getSum(a, b, c, d);
		cout << ret << "\n";
	}
}