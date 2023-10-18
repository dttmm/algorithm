#include <iostream>

using namespace std;

#define MAX_N 100

int N;
int arr[MAX_N];

// ����
void swap(int& a, int& b) {
	int temp = a;
	a = b;
	b = temp;
}

// ���� ���� ����
void solve() {
	for (int i = 0; i < N; i++) {

		// ��ü ���߿� ���� ����(ū) ���� ã��
		int minIndex = i;
		for (int j = i + 1; j < N; j++) {
			if (arr[j] < arr[minIndex]) minIndex = j;
		}

		// ���� ����(ū) ���Ҹ� ���ʿ������� ���ʴ�� ��ġ
		swap(arr[i], arr[minIndex]);
	}
}

int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	solve();

	for (int i = 0; i < N; i++) {
		cout << arr[i] << " ";
	}
}