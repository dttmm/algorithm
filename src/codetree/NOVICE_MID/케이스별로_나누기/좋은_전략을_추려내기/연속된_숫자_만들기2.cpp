#include <iostream>
#include <algorithm>
#include <cstdlib>

using namespace std;

int main() {

	// �Է�
	int a, b, c;
	cin >> a >> b >> c;

	int diff1 = abs(a - b);
	int diff2 = abs(b - c);
	int diff3 = abs(c - a);

	int cnt1 = 0;
	int cnt2 = 0;

	if (diff1 == 1) cnt1++;
	if (diff2 == 1) cnt1++;
	if (diff3 == 1) cnt1++;

	if (diff1 == 2) cnt2++;
	if (diff2 == 2) cnt2++;
	if (diff3 == 2) cnt2++;

	// 1���� ���� ���� 2���� ��� -> ������ �ʿ� ����
	if (cnt1 == 2) {
		cout << 0;
		return 0;
	}
	// 2���� ���� ���� 1�� �̻��� ��� -> 2���� ���� �� ���̷� ������ ���� ���� ��
	if (cnt2 >= 1) {
		cout << 1;
		return 0;
	}
	cout << 2;
}