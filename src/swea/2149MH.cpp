#include <iostream>
#include <string>

using namespace std;

/*
* in 2149 out 13240
*/

#define MAX_N 1000
#define MAX(a, b) ((a) > (b) ? (a) : (b))

int N;
int H;
int W;
int arr[MAX_N];
int maxVal;

void init() {
	maxVal = 0;
}

void solve(int s, int e) {
	if (s > e) return;
	int mid = (s + e) / 2;

	int limitW = W / mid;
	int limitH = H / mid;

	int w = 0;
	int h = 1;

	for (int i = 0; i < N; i++) {
		int len = arr[i];

		// 제한된 w안에서 해당 단어를 넣을 수 없는 경우
		if (len > limitW) {
			solve(s, mid - 1);
			return;
		}

		if (w == 0) w = len;
		else w += (len + 1);

		// 새로 추가한 단어가 제한된 w를 넘을 경우
		// 새로운 줄에 해당 단어 할당해줌
		if (w > limitW) {
			w = len;
			h++;
		}

		// 제한된 h안에서 단어들을 넣을 수 없는 경우
		if (h > limitH) {
			solve(s, mid - 1);
			return;
		}
	}

	maxVal = MAX(maxVal, mid);
	solve(mid + 1, e);
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	int T;
	cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		cin >> H >> W >> N;

		init();
		for (int i = 0; i < N; i++) {
			string s;
			cin >> s;

			arr[i] = s.length();
		}

		solve(1, 2000);

		cout << "#" << tc << " " << maxVal << "\n";
	}
}