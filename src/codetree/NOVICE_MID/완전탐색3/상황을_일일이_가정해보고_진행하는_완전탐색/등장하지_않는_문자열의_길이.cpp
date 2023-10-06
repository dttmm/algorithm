#include <iostream>
#include <string>

using namespace std;

#define MAX_N 100

int N;
char arr[MAX_N];

int solve() {
	// �� ���� ���� ���� ����
	for (int k = 1; k <= N; k++) {
		bool flag = false;

		// ���� ���� ����
		for (int i = 0; i <= N - k; i++) {
			string s1 = "";

			// ���� ������������ k�� ����
			for (int x = i; x < i + k; x++) {
				s1 += arr[x];
			}

			// ���� ���� ���� ����
			for (int j = i + 1; j <= N - k; j++) {
				string s2 = "";

				// ���� ������������ k�� ����
				for (int x = j; x < j + k; x++) {
					s2 += arr[x];
				}

				// ���� ���� ������ ���
				if (s1 == s2) {
					flag = true;
					break;
				}
			}
			if (flag) break;
		}

		if (flag) continue;
		// �ش� ���̿��� �ߺ��� ���ڰ� �������� �ʴ� ���
		else return k;
	}
}

int main() {

	// �Է�
	cin >> N >> arr;

	int ret = solve();

	cout << ret;
}