#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100

int N;
int M;
int arr[MAX_N][MAX_N];
bool row[MAX_N];	// �� �࿡ �ູ�� ���� �� ������
bool col[MAX_N];	// �� ���� �ູ�� ���� �� ������

// �� ���� �ູ�� �� ī��Ʈ
void setRow() {
	for (int i = 0; i < N; i++) {
		int prev = arr[i][0];
		int len = 1;	// ������ ������ ����
		bool flag = false;	// �ູ�� �������� �÷���
		if (len == M) flag = true;

		for (int j = 1; j < N; j++) {
			int n = arr[i][j];

			// ���� ���� ���� ���
			if (n == prev) len++;
			else len = 1;

			// �ູ�� �� ������ �����ϴ� ���
			if (len == M)flag = true;

			prev = n;
		}

		row[i] = flag;
	}
}

// �� ���� �ູ�� �� ī��Ʈ
void setCol() {
	for (int j = 0; j < N; j++) {
		int prev = arr[0][j];
		int len = 1;
		bool flag = false;
		if (len == M) flag = true;

		for (int i = 1; i < N; i++) {
			int n = arr[i][j];

			if (n == prev) len++;
			else len = 1;

			if (len == M)flag = true;

			prev = n;
		}

		col[j] = flag;
	}
}

// �� ���� ����
int solve() {
	int total = 0;

	for (int i = 0; i < N; i++) {
		total += row[i];
		total += col[i];
	}

	return total;
}

int main() {

	// �Է�
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	setRow();

	setCol();

	int ret = solve();

	cout << ret;
}