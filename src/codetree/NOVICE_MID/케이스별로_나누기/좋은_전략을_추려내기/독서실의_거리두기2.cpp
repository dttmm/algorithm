#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

#define MAX_N 1000

int N;
bool arr[MAX_N];
int ans;

// �� ��� �Ÿ� �� �ּҰ� ���ϱ�
int solve() {
	int minVal = MAX_N;
	for (int i = 0; i < N; i++) {
		if (!arr[i]) continue;
		for (int j = i + 1; j < N; j++) {
			if (!arr[j]) continue;

			int diff = j - i;
			minVal = min(minVal, diff);
			break;
		}
	}

	return minVal;
}

int main() {

	string s;
	cin >> N >> s;
	for (int i = 0; i < N; i++) {
		if (s[i] == '1') arr[i] = true;
	}

	// ���� �� �� ������� �Ÿ� ���ϱ�
	int maxVal = 0;
	int maxI, maxJ;
	for (int i = 0; i < N; i++) {
		if (!arr[i]) continue;
		for (int j = i + 1; j < N; j++) {
			if (!arr[j]) continue;

			int diff = j - i;
			if (diff > maxVal) {
				maxVal = diff;
				maxI = i;
				maxJ = j;
			}
			break;
		}
	}

	// ���� �Ÿ��� �� �� ���� ���̿� ���� �Ÿ� ���ϱ�
	if (maxVal != 0) {
		int mid = (maxI + maxJ) / 2;
		arr[mid] = true;

		int ret = solve();
		arr[mid] = false;

		ans = max(ans, ret);
	}

	// ù��° ��ġ�� ���� �Ÿ� ���ϱ�
	if (!arr[0]) {
		arr[0] = true;
		int ret = solve();
		ans = max(ans, ret);
		arr[0] = false;
	}
	// ������ ��ġ�� ���� �Ÿ� ���ϱ�
	if (!arr[N - 1]) {
		arr[N - 1] = true;
		int ret = solve();
		ans = max(ans, ret);
		arr[N - 1] = false;
	}

	cout << ans;
}