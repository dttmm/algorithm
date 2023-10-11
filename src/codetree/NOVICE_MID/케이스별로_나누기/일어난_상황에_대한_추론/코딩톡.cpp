#include <iostream>

using namespace std;

#define MAX_N 26

int N;
int M;
int P;
bool d[MAX_N][MAX_N];	// �� ���� ��� ���� i�϶�, j�� ä�ù濡 �־����� ����
bool read[MAX_N];		// i�� P�޽����� �о����� ����

int main() {

	// d�迭 ����
	fill(d[0], d[0] + MAX_N, true);

	cin >> N >> M >> P;
	for (int i = 1; i <= M; i++) {
		char c;
		int u;
		cin >> c >> u;

		d[u][c - 'A'] = true;

		// P��° �޽��� ������ ���
		if (i < P) continue;
		// P��° �޽����� ���
		if (i == P) {
			// �� ���� ��� ���� u�϶�, ä�ù濡 �־��� ������� ��� P�� �аԵ�
			for (int j = 0; j < MAX_N; j++) {
				if (d[u][j]) read[j] = true;
			}
		}

		// P���Ŀ� ä�ó��� ����� ������ ����
		read[c - 'A'] = true;
	}

	// ������ ��� ����
	int cnt = 0;
	for (int i = 0; i < N; i++) {
		if (!read[i]) cout << (char)('A' + i) << " ";
	}
}