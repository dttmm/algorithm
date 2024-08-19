#include <bits/stdc++.h>

using namespace std;

#define MAX_N 100000
#define MAX_X 1000000000
#define MIN(a, b) ((a) < (b)) ? (a) : (b)
#define MAX(a, b) ((a) > (b)) ? (a) : (b)

int N;
int M;
int arr[MAX_N]; // �� �� ���
int brr[MAX_N + 1];	// �ҹ漭

int main() {

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}
	for (int i = 0; i < M; i++) {
		cin >> brr[i];
	}

	brr[M] = MAX_X; // ������ ���� ����
	sort(arr, arr + N);
	sort(brr, brr + M);

	int ans = 0;
	int L = 0;  // �� �� �� �������� ���� �ҹ漺
	int R = 0;  // �� �� �� �������� ������ �ҹ漺
	// �� �� ���� �ϳ��� Ž��
	for (int i = 0; i < N; i++) {
		int n = arr[i];

		if (n < brr[L]) continue;
		while (R < M && n > brr[R]) {
			L = R;
			R++;
		}

		// �� ����� �ҹ漭 ����
		int minVal = MIN(n - brr[L], brr[R] - n);
		ans = MAX(ans, minVal);
	}

	cout << ans;
}