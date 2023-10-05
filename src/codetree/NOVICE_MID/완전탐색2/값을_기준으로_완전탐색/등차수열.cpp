#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100

int N;
int arr[MAX_N];
int ans;

void solve() {
	// k �ϳ� ����
	for (int k = 1; k <= 100; k++) {
		int total = 0;	// k�� �� �� ����� ��

		for (int i = 0; i < N; i++) {
			int n = arr[i];		// k �տ� �ִ� ����
			int diff = k - n;
			int m = k + diff;	// ���������� �̷�� ���� k �ڿ� �־�� �ϴ� ����

			for (int j = i + 1; j < N; j++) {
				if (arr[j] == m) {	// �ش� ���� m�� k �ڿ� �ִ��� Ȯ��
					total++;
					break;
				}
			}
		}
		ans = max(ans, total);
	}
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	solve();

	cout << ans;
}