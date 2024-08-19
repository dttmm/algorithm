#include <iostream>
#include <unordered_map>

using namespace std;

#define MAX(a, b) ((a) > (b) ? (a) : (b))

int N;
unordered_map<int, int> Map;
int ans;

int main() {

	cin >> N;

	int sum = 0;
	Map[0] = 0;	// 아무것도 선택하지 않았을때 나머지 0처리 해줘야됨
	for (int i = 1; i <= N; i++) {
		int n;
		cin >> n;

		// 누적합
		sum += n;

		// 나머지
		int r = sum % 7;
		sum = r;

		// 이전에 동일한 나머지를 가지는 누적합이 있는 경우
		if (Map.find(r) != Map.end()) {
			// 현재 누적합에서 해당 누적합 빼면
			// 나머지 0이므로 해당하는 구간에 포함되는 원소의 합은 7의 배수 됨
			int diff = i - Map[r];
			ans = MAX(ans, diff);
		}
		else {
			Map[r] = i;
		}
	}
	cout << ans;
}