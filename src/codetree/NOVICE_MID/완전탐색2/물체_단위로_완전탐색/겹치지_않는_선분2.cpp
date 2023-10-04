#include <iostream>

using namespace std;

#define MAX_N 100

int N;
int x1Arr[MAX_N];
int x2Arr[MAX_N];
bool overArr[MAX_N];

// ��ġ���� �����ϱ�
void solve() {
	// i: ����
	for (int i = 0; i < N; i++) {
		// ��ü ��ȸ�ϸ鼭 i�� ��ġ���� �Ǻ�
		for (int j = 0; j < N; j++) {
			if (i == j) continue;

			// x2�� ������ ũ�� x1�� ������ ������ ��ħ
			if (x2Arr[j] > x2Arr[i] && x1Arr[j] < x1Arr[i]) {
				overArr[i] = true;
				overArr[j] = true;
			}
		}
	}
}

int main() {

	// �Է� �ޱ�
	cin >> N;
	for (int i = 0; i < N; i++) {
		int x1;
		int x2;
		cin >> x1 >> x2;

		x1Arr[i] = x1;
		x2Arr[i] = x2;
	}

	// ��ġ���� �����ϱ�
	solve();

	// ��ġ�� �ʴ� ���� ���ϱ�
	int cnt = 0;
	for (int i = 0; i < N; i++) {
		if (!overArr[i]) cnt++;
	}

	cout << cnt;
}