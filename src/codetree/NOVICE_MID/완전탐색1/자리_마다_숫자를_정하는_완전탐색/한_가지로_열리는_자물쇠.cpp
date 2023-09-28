#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define R 3

int N;
int tr[R];
int key[R];	// �ڹ��� ��ȣ
int ans;

// �ߺ� ����
void solve(int k) {
	if (k == R) {
		bool flag = false;
		// ���� ���ڿ� �ڹ��� ��ȣ ��
		for (int i = 0; i < 3; i++) {
			// ���� ���̰� 2 �̳��� ���
			if (abs(tr[i] - key[i]) <= 2) {
				flag = true;
				break;
			}
		}

		if (flag) ans++;
	}
	else {
		for (int i = 1; i <= N; i++) {
			int n = i;
			tr[k] = n;
			solve(k + 1);
		}
	}
}

int main() {

	// �Է� �ޱ�
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> key[i];
	}

	// �ߺ� ����
	solve(0);

	cout << ans;
}