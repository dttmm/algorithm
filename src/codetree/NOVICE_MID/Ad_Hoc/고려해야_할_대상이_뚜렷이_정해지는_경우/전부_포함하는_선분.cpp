#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100

int N;
int xrr[MAX_N];
int yrr[MAX_N];

int main() {

	int minXIndex = 0;		// ���� ���� x�� ������ �ε���
	int maxYIndex = 0;	// ���� ū y�� ������ �ε���

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> xrr[i] >> yrr[i];

		if (xrr[i] < xrr[minXIndex]) minXIndex = i;
		if (yrr[i] > yrr[maxYIndex]) maxYIndex = i;
	}

	// ���� ���� x�� ������ ������ �����ϰ� ������ ������ �����ϴ� ������ ���� ���ϱ�
	int minX = 100;
	int maxY = 0;
	for (int i = 0; i < N; i++) {
		if (i == minXIndex) continue;

		minX = min(minX, xrr[i]);
		maxY = max(maxY, yrr[i]);
	}

	int len1 = maxY - minX;

	// ���� ū y�� ������ ������ �����ϰ� ������ ������ �����ϴ� ������ ���� ���ϱ�
	minX = 100;
	maxY = 0;
	for (int i = 0; i < N; i++) {
		if (i == maxYIndex) continue;

		minX = min(minX, xrr[i]);
		maxY = max(maxY, yrr[i]);
	}

	int len2 = maxY - minX;

	// �� �� �ּҰ� ����
	int ans = min(len1, len2);

	cout << ans;
}