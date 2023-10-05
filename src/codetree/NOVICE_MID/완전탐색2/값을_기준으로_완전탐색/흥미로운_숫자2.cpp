#include <iostream>
#include <algorithm>

using namespace std;

int X;
int Y;
int ans;

// ��̷ο� �������� �Ǻ�
bool solve(int n) {
	int cntArr[10] = {};	// �� �ڸ����� ������ ����
	while (n != 0) {
		int r = n % 10;
		cntArr[r]++;
		n /= 10;
	}

	int cnt1 = 0;	// ���ڰ� �ѹ� �̻� ���Դ��� ī��Ʈ
	int cnt2 = 0;	// ���ڰ� �ѹ��� ���Դ��� ī��Ʈ
	for (int i = 0; i < 10; i++) {
		int c = cntArr[i];

		if (c == 1) cnt2++;
		if (c >= 1) cnt1++;
	}

	// ��̷ο� ���� �����ϴ��� �Ǻ�
	return (cnt1 == 2 && cnt2 == 1);
}

int main() {

	// �Է�
	cin >> X >> Y;

	// ��Ž
	for (int i = X; i <= Y; i++) {
		bool ret = solve(i);

		if (ret) ans++;
	}

	cout << ans;
}