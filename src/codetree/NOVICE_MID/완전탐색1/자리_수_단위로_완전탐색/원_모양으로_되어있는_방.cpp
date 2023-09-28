#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_N 1003

int N;
int arr[MAX_N];

// k���� �������� ���� �� ���
int solve(int k) {
	int sum = 0;
	int d = 1;	//  k�濡�� �ٸ� ������� �Ÿ�
	int n = N - 1;	// ���� �ݺ� Ƚ��

	// ó������ �����ϰ�
	// n-1�� �ݺ�
	while (n > 0) {
		int index = (k + d) % N;	// �ش� ���� arr���� �ε���
		sum += arr[index] * d;

		d++;
		n--;
	}

	return sum;
}

int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// ����Ž��
	int minValue = INT_MAX;
	for (int i = 0; i < N; i++) {

		int ret = solve(i);

		minValue = min(minValue, ret);
	}

	cout << minValue;
}