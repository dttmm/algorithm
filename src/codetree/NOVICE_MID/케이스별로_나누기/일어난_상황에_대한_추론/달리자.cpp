#include <iostream>

using namespace std;

#define MAX_N 100

int N;
int arr[MAX_N];
int brr[MAX_N];

int solve() {
	int cost = 0;
	// ���� ������ ���� ��� �������� �̵�
	for (int i = 0; i < N - 1; i++) {
		int rest = arr[i] - brr[i];	// ���� ���
		cost += rest;				// �̵� ���
		arr[i + 1] += rest;			// ���� �ο��� ����
	}

	return cost;
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}
	for (int i = 0; i < N; i++) {
		cin >> brr[i];
	}

	int ret = solve();
	cout << ret;
}