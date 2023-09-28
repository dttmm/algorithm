#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_N 100

int N;
int xrr[MAX_N];
int yrr[MAX_N];

// k�� üũ����Ʈ�� �ǳ� �پ��� �� �̵� �Ÿ�
int solve(int k) {
	int x = xrr[0];
	int y = yrr[0];

	int d = 0;
	for (int i = 1; i < N; i++) {
		// �ǳʶ� üũ����Ʈ
		if (i == k) continue;

		// �̵��� üũ����Ʈ
		int newX = xrr[i];
		int newY = yrr[i];

		// �Ÿ� ���
		d += abs(x - newX);
		d += abs(y - newY);

		// ���� ��ġ ����
		x = newX;
		y = newY;
	}

	return d;
}

int main() {

	// �Է� �ޱ�
	cin >> N;
	for (int i = 0; i < N; i++) {
		int x;
		int y;
		cin >> x >> y;

		xrr[i] = x;
		yrr[i] = y;
	}

	// ���� Ž��
	int minValue = INT_MAX;
	for (int i = 1; i < N - 1; i++) {
		int ret = solve(i);

		minValue = min(minValue, ret);
	}

	cout << minValue;
}