#include <iostream>
#include <string>

using namespace std;

#define MAX_N 50

int N;
int M;
char arr[MAX_N][MAX_N];
int di[] = { -1,-1,0,1,1,1,0,-1 };
int dj[] = { 0,1,1,1,0,-1,-1,-1 };
int ans;

// ���� üũ
bool isIn(int i, int j) {
	return (i >= 0 && i < N&& j >= 0 && j < M);
}

// EE�ִ��� �˻�
void solve(int start_i, int start_j) {
	// 8���� üũ
	for (int dir = 0; dir < 8; dir++) {
		int i = start_i;
		int j = start_j;
		int n = 2;

		// dir �������� �ι� ���� E�� �ִ��� �˻�
		while (n > 0) {
			int newI = i + di[dir];
			int newJ = j + dj[dir];

			// ���� ��� ���
			if (!isIn(newI, newJ)) break;
			// E�� �ƴ� ���
			if (arr[newI][newJ] != 'E') break;

			// ���� ��ġ ����
			i = newI;
			j = newJ;
			n--;
		}

		// EE�� ã�� ���
		if (n == 0) ans++;
	}
}

int main() {

	// �Է� �ޱ�
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;
		for (int j = 0; j < M; j++) {
			char c;
			c = s[j];

			arr[i][j] = c;
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			// L�� �ƴ� ���
			if (arr[i][j] != 'L') continue;

			// L��ġ�������� EEã��
			solve(i, j);
		}
	}

	cout << ans;
}