#include <iostream>

using namespace std;

#define MAX_N 100000

int N;
int arr[MAX_N + 1];

// ����
void swap(int& a, int& b) {
	int temp = a;
	a = b;
	b = temp;
}

// �������� ����
void heapify(int n, int index) {
	int min = index;	// �θ� ����
	int left = index * 2;
	int right = index * 2 + 1;

	// ���� �ڽ��� �θ𺸴� ������ �˻�
	if (left <= n && arr[left] < arr[min]) min = left;
	// ������ �ڽ��� �θ𺸴� ������ �˻�
	if (right <= n && arr[right] < arr[min]) min = right;

	// �ڽ��߿� �ּҰ��� ���� �b��
	if (min != index) {
		swap(arr[min], arr[index]);
		heapify(n, min);
	}
}

// �� ���� ����
void heapSort(int n) {
	// ���� ��带 ������ �θ� ��忡�� ����
	for (int i = n / 2; i >= 1; i--) {
		heapify(n, i);
	}
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}

	// ����
	heapSort(N);

	// �������� ���
	for (int n = N; n >= 1; n--) {
		int min = arr[1];

		swap(arr[1], arr[n]);
		heapify(n - 1, 1);

		cout << min << " ";
	}
}