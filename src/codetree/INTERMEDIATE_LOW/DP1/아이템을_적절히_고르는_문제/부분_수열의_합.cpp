#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_N 100
#define MAX_M 10000

int N;
int M;
int arr[MAX_N];
bool d[MAX_M + 1];	// �κ� ������ m�� ���� �� �ִ��� ������

// dp
void solve() {
	d[0] = true;

	// ���� �ϳ� n���
	for (int i = 0; i < N; i++) {
		int n = arr[i];

		// n�� �̿��ؼ� m�� ����� �ִ��� �˻�
		for (int m = M; m >= 1; m--) {
			if (n > m) continue;
			if (!d[m - n]) continue;

			d[m] = true;
		}
	}
}

int main() {

	// �Է�
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	solve();

	// M�� ����� �ִ� ���
	if (d[M]) cout << "Yes";
	// ���� ���
	else cout << "No";
}