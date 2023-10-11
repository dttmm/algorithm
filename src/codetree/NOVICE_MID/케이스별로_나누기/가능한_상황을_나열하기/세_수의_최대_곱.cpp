#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100000
#define MAX_X 1000

int N;
int arr[MAX_N];	// ���
int brr[MAX_N];	// ����
int cntA;	// ��� ����
int cntB;	// ���� ����
int ans = MAX_X * MAX_X * MAX_X * -1;

int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		if (n > 0) arr[cntA++] = n;
		else if (n < 0) brr[cntB++] = n;
		else ans = 0;	// 0�� ���� ���
	}

	sort(arr, arr + cntA);
	sort(brr, brr + cntB);

	// ��� 3�� ���� ���
	if (cntA >= 3) {
		int total = 1;
		// ���� ū ��� 3�� ����
		for (int i = 0; i < 3; i++) {
			total *= arr[cntA - 1 - i];
		}

		ans = max(ans, total);
	}

	// ��� 2��, ���� 1�� ���� ���
	if (cntA >= 2 && cntB >= 1) {
		int total = 1;
		// ���� ���� ��� 2�� ����
		for (int i = 0; i < 2; i++) {
			total *= arr[i];
		}
		// ���� ���� ���� 1�� ����
		for (int i = 0; i < 1; i++) {
			total *= brr[cntB - 1 - i];
		}

		ans = max(ans, total);
	}

	// ��� 1��, ���� 2�� ���� ���
	if (cntA >= 1 && cntB >= 2) {
		int total = 1;
		// ���� ū ��� 1�� ����
		for (int i = 0; i < 1; i++) {
			total *= arr[cntA - 1 - i];
		}
		// ���� ū ���� 2�� ����
		for (int i = 0; i < 2; i++) {
			total *= brr[i];
		}

		ans = max(ans, total);
	}

	// ���� 3�� ���� ���
	if (cntB >= 3) {
		int total = 1;
		// ���� ���� ���� 3�� ����
		for (int i = 0; i < 3; i++) {
			total *= brr[cntB - 1 - i];
		}

		ans = max(ans, total);
	}

	cout << ans;
}