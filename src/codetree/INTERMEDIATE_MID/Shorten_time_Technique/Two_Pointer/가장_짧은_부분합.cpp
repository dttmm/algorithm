#include <iostream>

using namespace std;

#define MAX_N 100000
#define MIN(a, b) ((a) < (b) ? (a) : (b))
#define INF 1000000000

int N;
int S;
int arr[MAX_N];

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> N >> S;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	int sum = 0;
	int ans = INF;
	int L = 0;
	// R������ �ϳ� �̵��Ը鼭
	for (int R = 0; R < N; R++) {
		sum += arr[R];

		// ���� ���� S�̻��� ���
		while (sum >= S) {
			// ���� ���� �ּҰ� ����
			int len = R - L + 1;
			ans = MIN(ans, len);

			// L������ �ϳ� �̵�
			sum -= arr[L];
			L++;
		}
	}

	if (ans == INF) ans = -1;
	cout << ans;
}