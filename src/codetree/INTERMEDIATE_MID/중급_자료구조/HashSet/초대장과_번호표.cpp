#include <iostream>
#include <unordered_set>
using namespace std;
#define MAX_N 100000
#define MAX_G 250000

int N;
int G;
unordered_set<int> SetN[MAX_N + 1];	// i번째 사람이 들어간 그룹 정보
unordered_set<int> SetG[MAX_G + 1];	// i번째 그룹에 있는 사람들 정보
unordered_set<int> SetAns;

// 초대장 주기
void solve(int n) {
	SetAns.insert(n);

	// n사람이 속한 그룹g 돌면서
	for (int g : SetN[n]) {
		// 해당 그룹에서 n 제거
		SetG[g].erase(n);

		// 그룹에 한명만 남아있는 경우
		if (SetG[g].size() == 1) {
			// 그 한명에게 초대장 줌
			int m = *(SetG[g].begin());
			solve(m);
		}
	}
}

int main() {

	// 입력
	cin >> N;
	cin >> G;
	for (int i = 1; i <= G; i++) {
		int k;
		cin >> k;

		for (int j = 0; j < k; j++) {
			int n;
			cin >> n;

			SetG[i].insert(n);
			SetN[n].insert(i);
		}
	}

	solve(1);

	cout << SetAns.size();
}