#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100

int N;
int arr[MAX_N];
int brr[MAX_N];
int crr[MAX_N];
int ans;

// �� ����
void swap(int* cup, int a, int b) {
	int temp = *(cup + a);
	*(cup + a) = *(cup + b);
	*(cup + b) = temp;
}

void solve() {
	// 1~3�� �ſ� ���൹ �� �־��
	for (int i = 1; i <= 3; i++) {
		int cup[4] = { 0,1,2,3 };	// i�� ��ġ�� value���� ����
		int cnt = 0;

		// �Է� ��ȸ
		for (int k = 0; k < N; k++) {
			int a = arr[k];
			int b = brr[k];
			int c = crr[k];

			swap(cup, a, b);
			// c�� ����ġ�� ���൹�� �ִ� ���� ���
			if (cup[c] == i) cnt++;
		}

		ans = max(ans, cnt);
	}
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i] >> brr[i] >> crr[i];
	}

	solve();

	cout << ans;
}