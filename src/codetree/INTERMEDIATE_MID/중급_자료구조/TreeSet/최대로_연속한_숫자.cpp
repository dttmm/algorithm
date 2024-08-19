#include <iostream>
#include <set>
#include <map>

using namespace std;

int N;
int M;
set<int> Set;	// 쪼갠 위치 표시
map<int, int> Map; // 가능한 범위 저장


int main() {

	// 입력
	cin >> N >> M;

	// 최대 범위 설정
	int s = -1;
	int e = N + 1;

	Map[e - s - 1]++;

	for (int i = 0; i < M; i++) {
		int n;
		cin >> n;

		// 나보다 큰놈 구하기
		int right;
		auto it = Set.upper_bound(n);
		if (it == Set.end()) right = e;
		else right = *it;

		// 나보다 작은놈 구하기
		int left;
		it = Set.lower_bound(n);
		if (it != Set.begin()) it--;
		else it = Set.end();

		if (it == Set.end()) left = s;
		else left = *it;

		// 기존 범위 둘로 쪼갬
		int total = right - left - 1;
		Map[total]--;
		if (Map[total] == 0)Map.erase(total);

		Map[right - n - 1]++;
		Map[n - left - 1]++;

		// 쪼갠 지점 표시
		Set.insert(n);

		int max = Map.rbegin()->first;
		cout << max << "\n";
	}

}