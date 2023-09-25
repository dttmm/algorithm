#include <iostream>

using namespace std;

#define MAX_N 50

int N;
int T;
int di[] = { -1,0,0,1 };
int dj[] = { 0,1,-1,0 };
int arr[MAX_N + 1][MAX_N + 1];
int i;
int j;
int dir;

bool isIn(int i, int j) {
	return (i >= 1 && i <= N && j >= 1 && j <= N);
}

int main() {

	// �Է� �ޱ�
	cin >> N >> T;
	cin >> i >> j;

	char c;
	cin >> c;

	if (c == 'U') dir = 0;
	else if (c == 'R') dir = 1;
	else if (c == 'L') dir = 2;
	else  dir = 3;

	// t�� ���� �����̱�
	while (T > 0) {
		// ���ο� ��ǥ
		int newI = i + di[dir];
		int newJ = j + dj[dir];

		// ���� ��� ���
		if (!isIn(newI, newJ)) dir = 3 - dir;
		// �̵��� �� �ִ� ���
		else {
			i = newI;
			j = newJ;
		}

		T--;
	}

	cout << i << " " << j;
}