#include <iostream>
#include <algorithm>
#include <climits>
#include <cmath>

using namespace std;

#define MAX_N 100
#define MAX_A 1000

int N;
int cntArr[MAX_A + 1];
int startArr[MAX_N];
int endArr[MAX_N];
int ans = 0;

// ����ǰ� �ִ� �ð� ����
int getCnt() {
	int cnt = 0;
	for (int i = 0; i <= MAX_A; i++) {
		if (cntArr[i] > 0) cnt++;
	}
	return cnt;
}

// 1�� �ذ��ϱ�
void solve() {
	for (int i = 0; i < N; i++) {
		int start = startArr[i];
		int end = endArr[i];

		// �ذ��� ��ŭ �ð� ���̳ʽ�
		for (int j = start; j < end; j++) {
			cntArr[j]--;
		}

		// ����ǰ� �ִ� �ð� ����
		int ret = getCnt();
		ans = max(ans, ret);

		// �ٽ� �ð� ����
		for (int j = start; j < end; j++) {
			cntArr[j]++;
		}
	}
}

int main() {

	// �Է� �ޱ�
	cin >> N;
	for (int i = 0; i < N; i++) {
		int start;
		int end;
		cin >> start >> end;

		startArr[i] = start;
		endArr[i] = end;
		for (int j = start; j < end; j++) {
			cntArr[j]++;
		}
	}

	// 1�� �̱�
	solve();

	cout << ans;
}