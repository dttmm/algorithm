#include <iostream>

using namespace std;

#define MAX_N 100

int N;
int M;
bool arr[MAX_N];

int main() {

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	int ans = 0;		// 와이파이 설치 개수
	bool flag = false;	// 와이파이 설치 못한 집을 만났는지 플래그
	int d = 0;			// 와이파이 설치 못한 집으로부터 거리
	for (int i = 0; i < N; i++) {
		// 집을 만난 경우
		if (arr[i]) {
			flag = true;
		}

		if (!flag) continue;

		// 설치 못한 집과 M만큼 거리가 벌어진 경우 -> 와이파이 설치
		if (d == M) {
			ans++;
			d = 0;
			flag = false;
			i += M;
		}

		if (flag) d++;
	}

	// 아직 설치 못한 집이 있는 경우
	if (d != 0) ans++;

	cout << ans;
}