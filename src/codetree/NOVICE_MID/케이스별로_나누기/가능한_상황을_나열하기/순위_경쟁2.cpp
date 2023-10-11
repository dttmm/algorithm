#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

int N;
int ans;

int main() {

	cin >> N;

	int scoreA = 0;	// A 점수
	int scoreB = 0;	// B 점수
	int rank = 0;
	for (int i = 0; i < N; i++) {
		char c;
		int n;
		cin >> c >> n;

		if (c == 'A')scoreA += n;
		else scoreB += n;

		// A가 점수 더 높은 경우
		if (scoreA > scoreB) {
			// 순위 바뀐 경우
			if (rank != 1) {
				rank = 1;
				ans++;
			}
		}
		// B가 점수 더 높은 경우
		else if (scoreB > scoreA) {
			// 순위 바뀐 경우
			if (rank != 2) {
				rank = 2;
				ans++;
			}
		}
		// 점수 같은 경우
		else {
			// 순위 바뀐 경우
			if (rank != 0) {
				rank = 0;
				ans++;
			}
		}
	}

	cout << ans;
}