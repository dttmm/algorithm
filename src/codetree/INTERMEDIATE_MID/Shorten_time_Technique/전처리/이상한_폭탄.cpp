#include <iostream>
#include <unordered_map>

using namespace std;

/*
* ���� 3�� ���� 4��
*/

#define MAX(a, b) ((a) > (b) ? (a) : (b))

int N;
int K;
unordered_map<int, int> Map;	// �ֱٿ� ���� n�� ���� �ε���i ����

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N >> K;

	int ans = -1;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		// ������ n�� ���Դٸ�
		if (Map.find(n) != Map.end()) {
			// ������ ���Դ� n���� �Ÿ��� K���϶�� ���� ����
			if (i - Map[n] <= K) ans = MAX(ans, n);
		}

		Map[n] = i;
	}

	cout << ans;
}