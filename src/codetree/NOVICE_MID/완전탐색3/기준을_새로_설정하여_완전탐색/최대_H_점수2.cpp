#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define MAX_X 100

int N;
int L;
int cnt[MAX_N + 2];	// ���� i�� ����
int sum[MAX_N + 1];	// ���� i���� ũ�ų� ���� �� ����(������)

// ������ ���ϱ�
void setSum() {
	for (int x = MAX_X; x >= 0; x--) {
		sum[x] = sum[x + 1] + cnt[x];
	}
}

int main() {

	// �Է�
	cin >> N >> L;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;
		cnt[n]++;
	}

	// ������
	setSum();

	// x�� �����̶�� ���� �� ������ �����ϴ��� �˻�
	for (int x = MAX_X; x >= 1; x--) {
		// x���� ũ�ų� �������� ������ +1�� ������ x-1�� ������ ���� x�� �Ѿ�� ��
		int total = sum[x] + min(cnt[x - 1], L);

		// ���� ������ �����ϴ� ���
		if (total >= x) {
			cout << x;
			break;
		}
	}
}