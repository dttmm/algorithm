#include <iostream>
#include <unordered_map>

using namespace std;

/*
* 설계 3분 구현 4분
*/

#define MAX(a, b) ((a) > (b) ? (a) : (b))

int N;
int K;
unordered_map<int, int> Map;	// 최근에 숫자 n이 나온 인덱스i 저장

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N >> K;

	int ans = -1;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		// 이전에 n이 나왔다면
		if (Map.find(n) != Map.end()) {
			// 이전에 나왔던 n과의 거리가 K이하라면 정답 갱신
			if (i - Map[n] <= K) ans = MAX(ans, n);
		}

		Map[n] = i;
	}

	cout << ans;
}