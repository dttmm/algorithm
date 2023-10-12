#include <iostream>

using namespace std;

#define MAX_N 100

int N;
int M;
bool arr[MAX_N];

int main() {

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	int ans = 0;		// �������� ��ġ ����
	bool flag = false;	// �������� ��ġ ���� ���� �������� �÷���
	int d = 0;			// �������� ��ġ ���� �����κ��� �Ÿ�
	for (int i = 0; i < N; i++) {
		// ���� ���� ���
		if (arr[i]) {
			flag = true;
		}

		if (!flag) continue;

		// ��ġ ���� ���� M��ŭ �Ÿ��� ������ ��� -> �������� ��ġ
		if (d == M) {
			ans++;
			d = 0;
			flag = false;
			i += M;
		}

		if (flag) d++;
	}

	// ���� ��ġ ���� ���� �ִ� ���
	if (d != 0) ans++;

	cout << ans;
}