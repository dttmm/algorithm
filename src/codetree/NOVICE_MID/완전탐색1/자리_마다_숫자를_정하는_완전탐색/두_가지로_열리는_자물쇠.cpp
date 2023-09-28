#include <iostream>
#include<algorithm>
using namespace std;

#define R 3
#define MAX_N 100

int N;
int key1[R];	// ù��° �ڹ���
int key2[R];	// �ι�° �ڹ���
int tr[R];
int ans;

// �ߺ� ����
void solve(int k) {
	if (k == R) {
		// �ڹ��踦 �� �� �ִ��� �÷���
		bool flag = true;

		// ù��° �ڹ���� ��
		for (int i = 0; i < R; i++) {
			int diff = abs(tr[i] - key1[i]);
			// 2 �̳��� ������ ���� ���� ���
			if (diff > 2 && diff < N - 2) {	// �� ������ ���̰� N-2���� ũ�ٸ� �������� 2 �̳��� ����������
				flag = false;
				break;
			}
		}

		// ù��° �ڹ��谡 ������ ���
		if (flag) {
			ans++;
			return;
		}

		flag = true;
		// �ι�° �ڹ���� ��
		for (int i = 0; i < R; i++) {
			int diff = abs(tr[i] - key2[i]);
			if (diff > 2 && diff < N - 2) {
				flag = false;
				break;
			}
		}

		// �ι�° �ڹ��谡 ������ ���
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
	for (int i = 0; i < R; i++) {
		cin >> key1[i];
	}
	for (int i = 0; i < R; i++) {
		cin >> key2[i];
	}

	// �ߺ� ����
	solve(0);

	cout << ans;
}