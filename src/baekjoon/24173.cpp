#include <fstream>
#include <iostream>

/*
* ����14�� ����� 1��
* �� ����
* �� ���� ���� �����ؼ� Ǯ�
* ������ ��, �迭�� ���� �Ѱ���� �ϴµ�
* �ε����� �Ѱ��ִ� �Ǽ� ����
*/

using namespace std;

#define MAX_N 500000

int N;
int K;
int arr[MAX_N + 1];
int cnt;
int ans[2];

// ����
void swap(int& a, int& b) {
	int temp = a;
	a = b;
	b = temp;

	cnt++;
	if (cnt == K) {
		ans[0] = a < b ? a : b;
		ans[1] = a > b ? a : b;
	}
}

// �������� ����
void heapify(int* arr, int n, int index) {
	if (cnt >= K) return;

	int min = index;	// �θ� �������� ����
	int left = index * 2;
	int right = index * 2 + 1;

	// ���� �ڽ��� �ڽź��� ������ �˻�
	if (left <= n && arr[left] < arr[min]) min = left;
	// ������ �ڽ��� �ڽź��� ������ �˻�
	if (right <= n && arr[right] < arr[min]) min = right;

	if (min != index) {
		swap(arr[min], arr[index]);
		heapify(arr, n, min);
	}
}

// �� ���� ����
void heapSort(int* arr, int n) {
	//  �� ���� ����� -> ������� �����ϰ� �������� ����
	for (int index = n / 2; index >= 1; index--) {
		if (cnt >= K) return;

		heapify(arr, n, index);
	}

	// ������带 �迭�� ���������� ������ �������� �ݺ� -> �������� �迭 �������
	for (int size = n; size > 1; size--) {
		if (cnt >= K) return;

		swap(arr[1], arr[size]);
		heapify(arr, size - 1, 1);
	}
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/24173.txt", "r", stdin);

	// �Է�
	cin >> N >> K;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}


	heapSort(arr, N);

	if (cnt < K) cout << -1;
	else cout << ans[0] << " " << ans[1];
}