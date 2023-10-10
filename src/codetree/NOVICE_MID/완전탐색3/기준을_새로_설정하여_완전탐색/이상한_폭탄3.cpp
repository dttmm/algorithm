#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define MAX_X 1000000

int N;
int K;
int arr[MAX_N];
int boom[MAX_X + 1];

// �ִ밪 ã�Ƽ� ����
// K�̳���� ���� �� ���� K+1���� �����Ѵٴ� ���� ����
int solve() {
	int cnt[MAX_X + 1]; // �ش� ��ȣ�� �� �� ���õǾ����� ����
	// �ڽ� ���� K+1�� �����ذ��� ����� ������
	for (int i = 0; i < N; i++) {
		int n = arr[i];

		// K+1���� ���� �������� ���� ���
		if (i < K + 1) {
			cnt[n]++;
			if (cnt[n] > 1) boom[n]++;	// �� ��ȣ�� ���� �� �ִٸ�
			continue;
		}
		// K+1���� ������ ���

		cnt[n]++;

		int prev = arr[i - (K + 1)];
		cnt[prev]--;
		if (cnt[n] > 1) boom[n]++;		// �� ��ȣ�� ���� �� �ִٸ�
	}

	// �ִ밪 ã��
	int maxN = 0;
	for (int n = 1; n <= MAX_X; n++) {
		if (boom[n] >= boom[maxN]) maxN = n;
	}

	// ���� ��ź�� ���� ���
	if (boom[maxN] == 0) maxN = 0;
	return maxN;
}

int main() {

	// �Է�
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	int ret = solve();

	cout << ret;
}