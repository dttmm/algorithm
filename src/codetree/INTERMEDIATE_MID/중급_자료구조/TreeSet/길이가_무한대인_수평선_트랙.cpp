#include <iostream>
#include <map>
#include <climits>
using namespace std;

int N;
int T;
map<int, long long> Map;	// key: 처음 위치, value: 최종 위치

int main() {

	// 입력
	cin >> N >> T;
	for (int i = 0; i < N; i++) {
		long long p, v;
		cin >> p >> v;

		Map[-1 * p] = p + v * T;
	}

	int ans = 0;
	long long min = LLONG_MAX;	// 최종 위치 중에서 최소값
	// 처음 위치가 높은 사람부터 최종위치까지 갈때
	for (auto item : Map) {
		long long value = item.second;	// 최종위치

		// 최종위치 최소값 갱신
		// -> 앞으로 최소값보다 큰 값들은 최소값과 같은 그룹으로 다녀야함
		// 최소값보다 작은 값들은 새로운 그룹으로 다임
		if (value < min) {
			min = value;
			ans++;	// 그룹 수 갱신
		}
	}

	cout << ans;
}