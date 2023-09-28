#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_N 100

int N;
int S;
int arr[MAX_N];

int main() {

	// �Է��� �� ��
	int total = 0;

	// �Է� �ޱ�
	cin >> N >> S;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		arr[i] = n;
		total += n;
	}

	// ����
	sort(arr, arr + N);

	int ans = INT_MAX;
	int L = 0;
	int R = N - 1;

	// ��������
	while (L < R) {
		int a = arr[L];
		int b = arr[R];

		// ������ �ΰ� ���� ���������� ��
		int remain = total - a - b;
		// ������ ���� �հ� S�� ����
		int ret = abs(remain - S);
		// �ּҰ� ����
		ans = min(ans, ret);

		// ���� ������ ���� S���� �� ū ���
		// -> L�� �� ū ���� �����ؼ� remain�� �ٿ��� ��
		if (remain > S) L++;
		// ���� ������ ���� S���� �� ���� ���
		// -> R�� �� ���� ���� �����ؼ� remain�� ũ���ؾ� ��
		else if (remain < S) R--;
		// ���� ������ ���� S�� ������ ���
		else break;
	}

	cout << ans;
}