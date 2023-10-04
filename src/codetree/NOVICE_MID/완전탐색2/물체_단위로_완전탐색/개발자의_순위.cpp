#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_K 10
#define MAX_N 20
#define R 2

int N;
int K;
int arr[MAX_K + 1][MAX_N + 1];	// i��⿡�� j��° ����� ���
int tr[R];
bool visited[MAX_N + 1];
int ans;

// ����
void solve(int k) {
	if (k == R) {
		// n1�� n2���� �׻� ������ ���ٸ� ����� ��++
		int n1 = tr[0];
		int n2 = tr[1];

		// ��� ��⸦ ���鼭
		for (int k = 1; k <= K; k++) {
			// n1�� n2���� �ѹ��̶� ������ ������(rank�� ������) �о�
			if (arr[k][n1] > arr[k][n2]) return;
		}

		ans++;
	}
	else {
		for (int i = 1; i <= N; i++) {
			if (visited[i]) continue;

			visited[i] = true;
			tr[k] = i;
			solve(k + 1);
			visited[i] = false;
		}
	}
}

int main() {

	// �Է� �ޱ�
	cin >> K >> N;
	for (int k = 1; k <= K; k++) {
		for (int rank = 1; rank <= N; rank++) {
			int num;
			cin >> num;

			arr[k][num] = rank;
		}
	}

	// ����
	solve(0);

	cout << ans;
}