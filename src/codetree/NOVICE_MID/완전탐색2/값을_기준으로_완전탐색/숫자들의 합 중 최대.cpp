#include <iostream>
#include <algorithm>

using namespace std;

int X;
int Y;
int ans;

// ��Ž
void solve() {
	for (int i = X; i <= Y; i++) {
		int n = i;
		int sum = 0;

		// �ڸ� ���� ���ϱ�
		while (n != 0) {
			sum += n % 10;
			n /= 10;
		}

		ans = max(ans, sum);
	}
}

int main() {

	// �Է�
	cin >> X >> Y;

	// ��Ž
	solve();

	cout << ans;
}