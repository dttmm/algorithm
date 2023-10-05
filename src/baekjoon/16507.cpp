#include <fstream>
#include <iostream>

/*
* ���� 8�� ���� 14��
* ������
* ������ �����Ƿ� ������ �������� ��� ����� ����ϱ� ���ٴ�
* ���� ����� (1, 1)���� Ư�� ���� (i, j)������ ��� ������ �̸� ���� ������
* �������� ������ ��⸦ ����� �ʿ������
* �׸��� �׷��� �������� ��� ���Ž�ų�� �����ϸ� ��
*/

using namespace std;

#define MAX_N 1000

int N;
int M;
int Q;
int arr[MAX_N + 1][MAX_N + 1];
int sum[MAX_N + 1][MAX_N + 1];

// (1, 1)���� (i, j)������ ������ ���ϱ�
void setSum() {
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + arr[i][j];
		}
	}
}

// (i1, j1) ���� (i2, j2)������ ��� ���ϱ�
int query(int i1, int j1, int i2, int j2) {
	int total = sum[i2][j2] - sum[i2][j1 - 1] - sum[i1 - 1][j2] + sum[i1 - 1][j1 - 1];
	int cnt = (i2 * j2) - (i2 * (j1 - 1)) - ((i1 - 1) * j2) + ((i1 - 1) * (j1 - 1));
	return (total / cnt);
}


int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/16507.txt", "r", stdin);

	// �Է�
	cin >> N >> M >> Q;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			cin >> arr[i][j];
		}
	}

	// ������ ���
	setSum();

	// ���� ����
	for (int i = 0; i < Q; i++) {
		int i1, j1, i2, j2;
		cin >> i1 >> j1 >> i2 >> j2;

		int ret = query(i1, j1, i2, j2);
		cout << ret << "\n";
	}
}