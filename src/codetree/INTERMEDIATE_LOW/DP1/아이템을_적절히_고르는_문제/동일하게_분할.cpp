#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define MAX_X 100000

int N;
int arr[MAX_N];
bool d[MAX_X + 1];
int sum;

// dp
bool solve() {
	// ������ Ȧ���� �����ϰ� ���� �Ұ�
	if (sum & 1)return false;
	// ������ �� ����
	int half = sum / 2;

	d[0] = true;
	// ���� �ϳ� ���
	for (int i = 0; i < N; i++) {
		int n = arr[i];

		// ���ڵ��� ���� x�� �����ϴ��� �˻�
		for (int x = half; x >= n; x--) {
			if (d[x - n]) {
				d[x] = true;

				// ���õ� ���ڵ��� ���� half��� -> ������ ���ڵ��� �յ� half�ϱ�
				// �յ��ϰ� ���� ��!
				if (x == half) return true;
			}
		}
	}
	return false;
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
		sum += arr[i];
	}

	bool ret = solve();

	if (ret) cout << "Yes";
	else cout << "No";
}