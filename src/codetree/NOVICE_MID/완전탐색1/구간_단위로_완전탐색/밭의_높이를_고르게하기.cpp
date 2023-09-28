#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_N 100

int N;
int T;
int H;
int arr[MAX_N];	// H���� ���� ���� ����

int main() {

	cin >> N >> H >> T;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		// H���� ���� ���� (H�� �Ǳ� ���� �ʿ��� ��)
		int diff = abs(H - n);
		arr[i] = diff;
	}

	int sum = 0;
	int ans = INT_MAX;
	// arr���� ���������� T���� ����� �� �ּҰ��� �����ָ� ��
	for (int i = 0; i < N; i++) {
		// T���� ���� �� ����� ��
		if (i < T) {
			sum += arr[i];

			// T�� ����� ��
			if (i == T - 1) ans = min(ans, sum);
			continue;
		}

		// ���� ���� �����ְ� ������ ������ ���� ����
		sum += arr[i];
		sum -= arr[i - T];
		ans = min(ans, sum);
	}

	cout << ans;
}