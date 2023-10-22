#include <fstream>
#include <iostream>
#include <algorithm>

/*
* ���� 2�� ���� 3�� ����� 1��
* dp
* ��� ������ ����غ��鼭
* 1������ K���� ����
* �׸��� Ư�� �ݾ��� ���� �� �ִ� ��� �߿�
* ����� ���� ������ �ּҰ��� ������Ʈ �ϸ鼭
* dp�迭�� ä����
* 
* Ʋ��
* ������ ����Ͽ� K���� ���� �� ���� ����
* ���� ó���� ��������
*/

using namespace std;

#define MAX_N 100
#define MAX_K 10000
#define INF 100000000

int N;
int K;
int coins[MAX_N];	// ������
int d[MAX_K];		// k���� ����� ���� �ּ����� ���� ����

// dp�迭 ä���
void solve() {
	// 1�� ~ K������ ����� ���� ������ �ּ� ���� ���ϱ�
	for (int k = 1; k <= K; k++) {
		int minVal = 100000000;

		// k���� ����� ���� ������ ������ Ž��
		for (int i = 0; i < N; i++) {
			int n = coins[i];

			// �ش� �������� k���� ���� �� ���� ���
			if (k - n < 0) continue;

			// �� �� �ּҰ� ����
			minVal = min(minVal, d[k - n] + 1);
		}

		d[k] = minVal;
	}
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/2294.txt", "r", stdin);

	// �Է�
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> coins[i];
	}

	solve();

	// �־��� �������� K�� ����� �Ұ����� ���
	if (d[K] == 100000000) cout << -1;
	else cout << d[K];
}