#include <iostream>

using namespace std;

#define MAX_N 100
#define MAX_NUM 100

int N;
int M;
int arr[MAX_N + 1];
int cntB[MAX_NUM + 1];
int cntA[MAX_NUM + 1];

int main() {

	cin >> N >> M;
	// ���� A �Է� �ޱ�
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}
	// ���� B �� ������ ���� ����
	for (int i = 0; i < M; i++) {
		int n;
		cin >> n;
		cntB[n]++;
	}

	int ans = 0;
	// ���� A���� M���� ��
	for (int i = 0; i <= N - M; i++) {
		fill(cntA, cntA + MAX_NUM + 1, 0);

		for (int j = i; j < i + M; j++) {
			int n = arr[j];
			cntA[n]++;	// �� ���� ���� ����
		}

		bool flag = true;
		// A���� �� ���� B�� ������ �ִ� ���ڰ� ������ Ȯ��
		for (int k = 1; k <= MAX_NUM; k++) {
			if (cntA[k] != cntB[k]) {
				flag = false;
				break;
			}
		}

		// ���� ���
		if (flag) ans++;
	}

	cout << ans;
}