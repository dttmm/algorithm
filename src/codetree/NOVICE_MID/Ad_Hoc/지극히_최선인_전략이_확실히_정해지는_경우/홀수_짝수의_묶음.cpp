#include <iostream>

using namespace std;

int N;
int cnt1;	// Ȧ���� ����
int cnt2;	// ¦���� ����

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		if (n % 2 == 0) cnt2++;
		else cnt1++;
	}

	// ¦���� �����ϱ� ������ ���� �����
	// ¦�� Ȧ���� �ϳ� �� ���ų�
	// ¦�� Ȧ�� ������ ���ƾ� ��

	// Ȧ���� ¦������ 2���̻� ���� ���
	// Ȧ�� 2���� ���� ¦���� ����� �����
	while (cnt1 > cnt2 + 1) {
		cnt1 -= 2;
		cnt2 += 1;
	}

	// Ȧ���� ¦������ ���� ���
	// Ȧ�� 3���� ���� Ȧ���� ����� �����
	while (cnt1 > cnt2) {
		cnt1 -= 2;
	}

	// ¦���� ������ ���� ���
	// ¦�� 2���� ���� ¦��ȣ ����� �����
	while (cnt2 > cnt1 + 1) {
		cnt2 -= 1;
	}

	cout << cnt1 + cnt2;
}