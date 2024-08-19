#include <iostream>

using namespace std;

/*
* in 1920 out 10507
*/

#define MAX_N 200000
#define MAX_X 1000000
#define MAX(a, b) ((a) > (b) ? (a) : (b))

int N;
int P;
bool arr[MAX_X + 2];	// i�Ͽ� ���� �ߴ��� ����
int L[MAX_X + 2];	// 0~i�ϱ��� ������ ���� ����
// �־����� ���� ���� 0 ~ 10^6�̶�
// 0�� 1�Ϸ�, 10^6�� 10^6+1�Ϸ� ������

// �ʱ�ȭ
void init() {
	fill_n(arr, MAX_X + 1, false);
}

// L�迭 ����
void setL() {
	for (int i = 1; i <= MAX_X + 1; i++) {
		L[i] = L[i - 1] + arr[i];
	}
}

// n�� �̻� ���� ���ΰ� �������� üũ
bool isPossible(int n) {
	for (int i = n; i <= MAX_X + 1; i++) {
		// i-n�� ~ i�� ���� �� ������ ���� p���� ������ ��
		// n�� �̻���ΰ� ������ ���
		if (L[i] - L[i - n] + P >= n) return true;
	}
	return false;
}

// �̺� Ž��
int solve() {
	int maxN = 1; // �ִ�� ������ ���� ���� �ϼ�
	int s = 1;
	int e = MAX_X + 1;

	while (s <= e) {
		int mid = s + (e - s) / 2;

		// mid�� �̻� ���� ���ΰ� ������ ���
		if (isPossible(mid)) {
			s = mid + 1;
			maxN = MAX(maxN, mid);
		}
		else {
			e = mid - 1;
		}
	}

	return maxN;
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	int T;
	cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		cin >> N >> P;

		init();

		for (int i = 1; i <= N; i++) {
			int n;
			cin >> n;
			arr[n + 1] = true;
		}

		setL();

		int ret = solve();

		cout << "#" << tc << " " << ret << "\n";
	}
}