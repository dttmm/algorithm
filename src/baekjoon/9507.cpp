#include <fstream>
#include <iostream>

/*
* ���� 4��
* dp
* ��ͷ��ұ� �ݺ������� �ұ� ����ϴ�
* �ݺ������� �׳� d�迭 �̸� �� ���س���
* �Է� ���� ������ �ٷ� �������
*/

using namespace std;

#define MAX_N 67

int N;
long long d[MAX_N + 1];

// dp �迭 ����
void setD() {
	d[0] = 1;
	d[1] = 1;
	d[2] = 2;
	d[3] = 4;

	for (int i = 4; i <= MAX_N; i++) {
		// ��ȭ��
		d[i] = d[i - 1] + d[i - 2] + d[i - 3] + d[i - 4];
	}
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/9507.txt", "r", stdin);

	setD();

	// �Է�
	int T;
	cin >> T;
	for (int tc = 0; tc < T; tc++) {
		cin >> N;

		cout << d[N] << "\n";
	}
}