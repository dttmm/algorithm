#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100000
#define INF 1000000000

int N;
int arr[MAX_N * 2];

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < 2 * N; i++) {
		cin >> arr[i];
	}

	// ����
	sort(arr, arr + N * 2);

	// i��° ���� i+N��° �� ������ �ִ밪 ���ϱ�
	int minVal = INF;
	for (int i = 0; i < N; i++) {
		int diff = arr[N + i] - arr[i];
		minVal = min(minVal, diff);
	}

	cout << minVal;
}