#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_N 100
#define MAX_X 100

int N;
int M;
int arr[MAX_N];

bool solve(int x) {
	int sum = 0;	// ���� ��
	int cnt = 0;	// ĭ���� Ƚ��

	for (int i = 0; i < N; i++) {
		int n = arr[i];

		// ���� ���ڰ� �ּҰ����� ũ�� �Ұ���
		if (n > x) return false;

		// ���� ���ڸ� ���� ������ �տ� ����
		sum += n;

		// ������ ���� �ּҰ����� ũ��
		if (sum > x) {
			cnt++;		// ĭ���̸� ġ��
			sum = n;	// ���ο� ���� ����
		}

		// ĭ���� ������ M-1���� ũ�� �ȵ�
		if (cnt > M - 1) return false;
	}

	return true;
}

int main() {

	// �Է�
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	int limit = MAX_X * MAX_X;
	// i: �ִ밪�� �ּҰ� �Ǵ� ��
	// ������ �Ǵ� i�� �������� ���
	// i�� ������ ��, ������ �Ǵ� ������ �����ϴ��� �˻�
	for (int i = 1; i <= limit; i++) {
		bool ret = solve(i);

		if (ret) {
			cout << i;
			break;
		}
	}
}