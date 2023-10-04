#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_NUM 1000
#define MAX_N 100

int N;
int K;
int arr[MAX_NUM + 1];	// i�� ��ź�� �ֱ� ��ġ
int ans = -1;

int main() {

	// �Է� �ޱ�
	cin >> N >> K;

	for (int i = 1; i <= N; i++) {
		int n;
		cin >> n;

		// i�� ��ź�� ó�� ���� ���
		if (arr[n] == 0) arr[n] = i;

		// i�� ��ź�� �̹� ������ �ִ� ���
		else {
			// ������ �ִ� ��ġ�� ���� ��ġ ���̰� K�̳����� üũ
			if (i - arr[n] <= K) ans = max(ans, n);
			arr[n] = i;
		}
	}

	cout << ans;
}