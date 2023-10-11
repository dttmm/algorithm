#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_X 10000
#define INF 100000000

int X;
int d[MAX_X][MAX_X + 1];	// i���� jms�� �� �ɸ� �ð� �ּҰ�

int solve(int i, int j) {
	if (i < 0) return INF;	//	������ ����� ���
	if (j == 0) return INF;	// 0ms�� ���

	if (d[i][j] != 0) return d[i][j];

	// ������ �ӵ��� ���ΰ��, �ӵ��� ������ ���, �ӵ��� ���� ����� �ּҰ� ���ϰ�
	int minVal = min(solve(i - (j - 1), j - 1), solve(i - j, j));
	minVal = min(minVal, solve(i - (j + 1), j + 1));

	// �ּҰ��� +1�� ����
	return d[i][j] = minVal + 1;
}

int main() {

	// �Է�
	cin >> X;

	// ù ������ ����
	d[0][1] = 1;

	int ret = solve(X, 1);

	cout << ret;
}