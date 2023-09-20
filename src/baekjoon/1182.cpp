#include <iostream>
#include <fstream>

/*
* ���� 2�� ���� 9��
* ��Ʈ����ŷ
* �� ���Ҹ� �����ߴ��� ���ߴ����� ��Ʈ����ŷ���� ����� �� ����
* �������� �κм����� ��� �ϳ� �̻� �����ϱ� ������
* i�� 1���� ������
*/

using namespace std;

int N;
int S;
int arr[20];

// ��Ʈ����ŷ
int solve() {
	int answer = 0;

	for (int i = 1; i < (1 << N); i++) {
		int sum = 0;
		for (int j = 0; j < N; j++) {
			// �� ������ ���
			if (i & (1 << j)) {
				sum += arr[j];	// �ش� ���� ������
			}
		}

		// S�� ��ġ�ϴ��� Ȯ��
		if (sum == S) answer++;

	}

	return answer;
}

int main() {

	freopen("res/baekjoon/1182.txt", "r", stdin);

	cin >> N >> S;

	// �Է� �ޱ�
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// ��Ʈ����ŷ
	int ret = solve();

	cout << ret;
}