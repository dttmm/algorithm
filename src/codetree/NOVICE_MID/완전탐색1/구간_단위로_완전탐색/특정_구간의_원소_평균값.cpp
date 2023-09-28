#include <iostream>

using namespace std;

#define MAX_N 100

int N;
int arr[MAX_N];

int main() {

	// �Է� �ޱ�
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	int ans = 0;
	// ������ ���� ���� i~j
	for (int i = 0; i < N; i++) {
		for (int j = i; j < N; j++) {
			int sum = 0;

			// �����ȿ� �ִ� ���ҵ� ���ϱ�
			for (int h = i; h <= j; h++) {
				sum += arr[h];
			}

			// ��� ���ϱ�
			double avg = sum / (double)(j - i + 1);

			// �ٽ� ���� ���鼭 ��հ� ��ġ�ϴ� ���� �ִ��� �˻�
			for (int h = i; h <= j; h++) {
				if (arr[h] == avg) {
					ans++;
					break;
				}
			}
		}
	}

	cout << ans;
}