#include <iostream>
#include <cmath>

using namespace std;

#define MAX_X 10000

int N;
int K;
int arr[MAX_X + 1];

int main() {

	// �Է� �ޱ�
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		int x;
		char c;
		cin >> x >> c;
		if (c == 'G') arr[x] = 1;
		else if (c == 'H') arr[x] = 2;
	}

	int ans = 0;
	int sum = 0;
	// ���� ��ȸ
	for (int i = 1; i <= MAX_X; i++) {
		// K+1���� ���� �� ������
		// ���ݱ��� ���� �͵� �հ� ����
		if (i <= K + 1) {	// ���� ������ ���� K�̴ϱ� �̸��� i�������� �� K+1���� �̾Ҵٴ� �ǹ���
			sum += arr[i];
			ans = max(ans, sum);
			continue;
		}

		// ���ķδ� �����ϸ鼭 ���� ���� ���ϰ�
		sum += arr[i];
		// K+1������ ��
		sum -= arr[i - (K + 1)];
		ans = max(ans, sum);
	}

	cout << ans;
}