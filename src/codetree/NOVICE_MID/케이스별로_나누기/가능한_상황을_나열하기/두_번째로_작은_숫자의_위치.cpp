#include <iostream>

using namespace std;

#define MAX_N 100

int N;
int cnt[MAX_N + 1];		// �� ���ڰ� �� �� �ִ���
int index[MAX_N + 1];	// �� ������ ��ġ ����

int solve() {
	int rank = 0;	// �� ��°�� ���� ������
	for (int i = 1; i <= MAX_N; i++) {
		// ���� ���� �о�
		if (cnt[i] == 0) continue;

		// �� ��°�� ������ ����
		rank++;

		// �� ������ ���� ��
		if (rank == 2) {
			// �ش� ���� �ϳ��� �ִ� ���
			if (cnt[i] == 1) return index[i];
			// ������ �ִ� ���
			else return -1;
		}
	}

	// �ι����� ���� ���� ���� ���
	return -1;
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 1; i <= N; i++) {
		int n;
		cin >> n;

		cnt[n]++;
		index[n] = i;
	}

	int ret = solve();

	cout << ret;
}