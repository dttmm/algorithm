#include <iostream>
#include <algorithm>
#include <cmath>

using namespace std;

int N;

int main() {

	// �Է�
	cin >> N;

	// ���� a, b�� 2�辿 ������ ������ ���� ã��
	int p = 2;
	int maxVal = 0;
	for (int i = 0; i < N; i++) {
		int a, b;
		cin >> a >> b;

		// p�� ������ ��, a�� �ѱ� �� �ִ� ���� ���� ��
		int n = ceil((double)a / p);

		maxVal = max(maxVal, n);

		p *= 2;
	}

	cout << maxVal;
}