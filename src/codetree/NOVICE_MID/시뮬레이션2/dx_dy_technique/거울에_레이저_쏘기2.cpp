#include <iostream>
#include <fstream>

using namespace std;

#define MAX_N 1000

int N;
int K;
char arr[MAX_N][MAX_N];
int i;
int j;
int di[] = { 1,0,-1,0 };
int dj[] = { 0,-1,0,1 };

// ���� �˻�
bool isIn(int i, int j) {
	return (i >= 0 && i < N&& j >= 0 && j < N);
}

// ���� ���� ���ϱ�
void setStart() {
	i = 0;
	j = -1;
	int dir = 3;
	int k = K;

	// �׵θ��� ���鼭 ���� ���� i, j ���ϱ�
	while (k > 0) {
		int newI = i + di[dir];
		int newJ = j + dj[dir];

		// ���� ��� ��� -> 90�� ȸ��
		if (!isIn(newI, newJ)) dir = (dir + 1) % 4;
		// ���� �ִ� ��� -> �̵�
		else {
			i = i + di[dir];
			j = j + dj[dir];
		}
		k--;
	}
}

// �̵��ϱ�
int solve() {

	int dir = K / N;	// �ʱ� ���� ���ϱ�
	i = i - di[dir];	// �ʱ� ��ġ ����
	j = j - dj[dir];	// �ʱ� ��ġ ����

	// �ſ￡ ƨ�� Ƚ��
	int cnt = 0;

	// ������ ��� ������ �̵�
	while (true) {
		int newI = i + di[dir];
		int newJ = j + dj[dir];

		// ������ ��� ���
		if (!isIn(newI, newJ)) break;

		i = newI;
		j = newJ;
		cnt++;

		// /��� �ſ��� ���� ���
		if (arr[newI][newJ] == '/') {
			// ���Ʒ� ������ ��
			if (dir % 2 == 0) dir = (dir + 1) % 4;
			// �¿� ������ ��
			else dir = (dir + 3) % 4;
		}
		// \��� �ſ��� ���� ���
		else {
			// ���Ʒ� ������ ��
			if (dir % 2 == 0) dir = (dir + 3) % 4;
			// �¿� ������ ��
			else dir = (dir + 1) % 4;
		}
	}

	return cnt;
}

int main() {

	freopen("res/1.txt", "r", stdin);

	// �Է� �ޱ�
	cin >> N;
	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;
		for (int j = 0; j < N; j++) {
			char c = s[j];

			arr[i][j] = c;
		}
	}

	cin >> K;

	// ���� ���� ���ϱ�
	setStart();

	// ���� ���ϱ�
	int ret = solve();

	cout << ret;
}