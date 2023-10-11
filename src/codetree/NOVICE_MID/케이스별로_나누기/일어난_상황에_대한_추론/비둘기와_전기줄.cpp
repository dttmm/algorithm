#include <iostream>

using namespace std;

#define MAX_X 10

int N;
int arr[MAX_X + 1]; // i�� ��ѱ��� ���� ����

// ������ �ٲ������ üũ
// true: ���� �ٲ�
bool solve(int num, int dir) {
	// ���� ������ ���� ���
	if (arr[num] == -1) {
		arr[num] = dir;
		return false;;
	}

	// ���� ����� �Ȱ��� ������ ���
	if (arr[num] == dir) return false;

	// ������ �ٲ� ���
	arr[num] = dir;
	return true;
}

int main() {

	// ��� ��ѱ� ���� ���ٰ� �ʱ�ȭ
	fill_n(arr + 1, MAX_X, -1);

	// �Է�
	cin >> N;
	int cnt = 0;
	for (int i = 0; i < N; i++) {
		int num;
		int dir;
		cin >> num >> dir;

		// ���� �ٲ������ üũ
		bool ret = solve(num, dir);
		if (ret) cnt++;
	}

	cout << cnt;
}