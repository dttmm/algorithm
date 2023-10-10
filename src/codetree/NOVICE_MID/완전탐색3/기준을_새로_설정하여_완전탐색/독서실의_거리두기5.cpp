#include <iostream>
#include <algorithm>
#include <string>
#include <cstdlib>

using namespace std;

#define MAX_N 20
#define INF 1000

int N;
char arr[MAX_N];
int ans;

// 1�� �ڸ��� ä�� �ֱ�
void solve(int k) {
	if (k == 1) {
		int left[MAX_N];	// ���ʿ��� ���� ���� ����� ��
		int right[MAX_N];	// �����ʿ��� ���� ���� ����� ��

		fill(left, left + N, INF);
		fill(right, right + N, INF);

		// ���ʿ��� ���� ���� ����� �� ���ϱ�
		for (int i = 1; i < N; i++) {
			left[i] = left[i - 1];
			if (arr[i - 1] == 1) left[i] = i - 1;
		}

		// �����ʿ��� ���� ���� ����� �� ���ϱ�
		for (int i = N - 2; i >= 0; i--) {
			right[i] = right[i + 1];
			if (arr[i + 1] == 1) right[i] = i + 1;
		}

		// ���� ����� ����� �Ÿ� ���ϱ�
		int minVal = INF;
		for (int i = 0; i < N; i++) {
			if (arr[i] != 1) continue;
			int diff1 = abs(i - left[i]);
			int diff2 = abs(i - right[i]);

			minVal = min(minVal, diff1);
			minVal = min(minVal, diff2);
		}

		// �ִ밪 ����
		ans = max(ans, minVal);
	}
	else {
		for (int i = 0; i < N; i++) {
			if (arr[i] == 1) continue;
			arr[i] = 1;
			solve(k + 1);
			arr[i] = 0;
		}
	}
}

int main() {

	// �Է�
	string s;
	cin >> N >> s;
	for (int i = 0; i < s.length(); i++) {
		arr[i] = s[i] - '0';
	}

	solve(0);

	cout << ans;
}