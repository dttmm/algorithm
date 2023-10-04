#include <fstream>
#include <iostream>
#include <algorithm>

/*
* ���� 2�� ���� 4��
* �׸���
* ������ ũ�⸦ �迭�� �����Ѵ���
* �迭�� ū ������� ����
* ���ڸ� �ϳ��� �������鼭 ������ ���
* �� ���� ������ ���ڸ� ���
* ������ �� ū ���ڰ� ���� ���� �ϵ���
* �迭�� ���� �ְ� �� ��, -1������
*/

using namespace std;

#define MAX_N 1000

int T;
int N;
int J;
int arr[MAX_N];	// ������ ũ�� ����

// ���ڿ� ���� ���
int solve() {
	int remain = J;	// ���� �ִ� ������ ��
	int cnt = 0;	// ����� ���� ��
	int index = 0;

	// ���� ������ ���� ������ �ݺ�
	while (remain > 0) {
		int n = (arr[index++]) * -1;
		remain -= n;
		cnt++;
	}

	return cnt;
}

int main() {

	freopen("res/baekjoon/11256.txt", "r", stdin);

	// ����
	cin >> T;
	for (int tc = 0; tc < T; tc++) {
		// �Է� �ޱ�
		cin >> J >> N;
		for (int i = 0; i < N; i++) {
			int r;
			int c;
			cin >> r >> c;

			arr[i] = (r * c) * -1;
		}

		// ����
		sort(arr, arr + N);

		// ���
		int ret = solve();

		cout << ret << "\n";
	}
}