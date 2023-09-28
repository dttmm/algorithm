#include <iostream>
#include <cmath>

using namespace std;

#define MAX_N 100
#define MAX_X 100

int N;
int K;
int arr[MAX_X + 1];	// x��ǥ�� ������ �� �� �ִ���

int main() {

	// �Է� �ޱ�
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		int cnt;
		int x;
		cin >> cnt >> x;

		arr[x] += cnt;
	}

	// c-k ~ c+k ������ 2k+1��ŭ�� ������ �Ȱ���
	int kk = 2 * K + 1;
	// 2k+1�� �ִ� ��ǥ ������ 101�� �Ѿ �� �����Ƿ� �ִ� kk�� ����
	kk = min(kk, MAX_X + 1);

	int sum = 0;
	int ans = 0;
	// ��ǥ ��ȸ�ϸ鼭 kk���� ��ŭ�� ���� ���� ����
	for (int i = 0; i < MAX_X + 1; i++) {
		if (i < kk) {
			sum += arr[i];
			ans = max(ans, sum);
			continue;
		}

		sum += arr[i];
		sum -= arr[i - kk];
		ans = max(ans, sum);
	}

	cout << ans;
}