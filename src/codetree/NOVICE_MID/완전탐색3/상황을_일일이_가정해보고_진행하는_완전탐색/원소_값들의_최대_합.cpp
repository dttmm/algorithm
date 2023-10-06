#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100

int N;
int M;
int arr[MAX_N + 1];
int ans;

void solve() {
	// ���� ��ġ ���ϱ�
	for (int i = 1; i <= N; i++) {
		int x = i;

		// M�� ��ŭ �����̱�
		int sum = 0;
		for (int j = 0; j < M; j++) {
			int n = arr[x];	// �ش� ��ġ�� �ִ� ����
			sum += n;
			x = n;			// ���� ��ġ ������Ʈ
		}

		ans = max(ans, sum);
	}
}

int main() {

	// �Է�
	cin >> N >> M;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}

	solve();

	cout << ans;
}