#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_X 100

int N;
int arr[MAX_X + 1];	// x좌표에 어떤 알파벳이 있는지 1:G, 2:H

int main() {

	// 입력 받기
	cin >> N;
	for (int i = 0; i < N; i++) {
		int x;
		char c;
		cin >> x >> c;

		if (c == 'G') arr[x] = 1;
		else arr[x] = 2;
	}

	// G와 H 각각 몇개 나왔는지 카운트 1:G, 2:H
	int cnt[3] = {};
	int ans = 0;
	// 시작 지점 i에서부터
	for (int i = 0; i <= MAX_X; i++) {
		int n = arr[i];
		if (n == 0) continue;

		fill(cnt, cnt + 3, 0);
		cnt[n]++;

		// j지점까지 G와 H개수 cnt로 세어줌
		for (int j = i + 1; j <= MAX_X; j++) {
			int m = arr[j];
			if (m == 0) continue;;

			cnt[m]++;

			int diff = j - i;
			// G와 H의 수가 같은 경우
			if (cnt[1] == cnt[2]) ans = max(ans, diff);
			// H만 나온 경우
			else if (cnt[1] == 0 && cnt[2] != 0)ans = max(ans, diff);
			// G만 나온 경우
			else if (cnt[1] != 0 && cnt[2] == 0)ans = max(ans, diff);
		}
	}

	cout << ans;
}