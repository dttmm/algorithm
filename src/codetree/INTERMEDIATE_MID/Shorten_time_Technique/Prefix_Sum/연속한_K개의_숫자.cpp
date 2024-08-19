#include <iostream>
#include<unordered_map>

using namespace std;

#define MAX_N 100000

int N;
int K;
int B;
int arr[MAX_N + 1];	// 1: i�ڸ��� ���ڰ� ����
int sum[MAX_N + 1]; // ������ġ���� ���� ���� ����

int main() {

	cin >> N >> K >> B;
	for (int i = 0; i < B; i++) {
		int n;
		cin >> n;

		// ���� ���� ǥ��
		arr[n] = 1;
	}

	// ������
	for (int i = 1; i <= N; i++) {
		sum[i] = sum[i - 1] + arr[i];
	}

	// ������ġ���� ���ӵ� K�� ����� �� ���� ���� ������ �ּҰ� ����
	int min = MAX_N;
	for (int i = K; i <= N; i++) {
		int cnt = sum[i] - sum[i - K];
		min = cnt < min ? cnt : min;
	}

	cout << min;
}