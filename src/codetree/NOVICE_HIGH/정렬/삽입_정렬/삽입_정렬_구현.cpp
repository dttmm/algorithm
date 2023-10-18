#include <iostream>

using namespace std;

#define MAX_N 100

int N;
int arr[MAX_N];

// ���� ���� ����
void solve() {

	// i������ ���ҵ��� �̹� ���ĵ� ������
	for (int i = 1; i < N; i++) {
		int j = i - 1;
		int key = arr[i];	// Ű�� ����� ���� ���� ����

		// ������ �۰ų� ���� ���� �տ��� ����
		while (j >= 0 && arr[j] > key) {
			arr[j + 1] = arr[j];
			j--;
		}

		arr[j + 1] = key;
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