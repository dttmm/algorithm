#include <fstream>
#include <iostream>

/*
*  ���� 2�� ���� 3��
* ����
* N���� �� ���ڰ� �� ���� �ִ��� arr�迭�� ī��Ʈ ��
* ��, 9�� 6���� ġȯ��
* 
* �׸��� ���� ���� ���� ������ �ٷ� ����
* ��, 6�� ���� �� ��Ʈ�� 2���� �����Ƿ�
* 6�� ������ ������ �����ָ� 6�� ������ �� �ִ� ��Ʈ ���� ���� �� ����
* ��, 6�� ������ Ȧ�� �� ���� ������ ���� �� ceil�� �ؾ� �ϹǷ�
* 6�� ������ 1�� ���Ͽ� ������
*/

using namespace std;

int N;
int arr[9];

// �� ���ڰ� �� ���� �ִ��� ī��Ʈ
void setArr() {
	int x = N;
	while (x != 0) {
		int n = x % 10;

		if (n == 9) n = 6;	// 9�� 6���� ġȯ
		arr[n]++;

		x /= 10;
	}
}

// �ʿ��� ��Ʈ ���� ���ϱ�
int solve() {
	arr[6] = (arr[6] + 1) / 2;	// ceilȿ��

	int maxVal = 0;
	for (int i = 0; i < 9; i++) {
		maxVal = arr[i] > maxVal ? arr[i] : maxVal;
	}

	return maxVal;
}

int main() {

	freopen("res/baekjoon/1475.txt", "r", stdin);

	// �Է�
	cin >> N;

	setArr();

	int ans = solve();

	cout << ans;
}