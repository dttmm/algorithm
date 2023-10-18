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
	bool sorted = false;	// �̹� ���ĵǾ��ִ��� üũ �÷���

	while (!sorted) {	// �̹� ���ĵ� ���¶�� Ż��
		sorted = true;

		for (int i = 0; i < N - 1; i++) {
			if (arr[i] > arr[i + 1]) {	// �� ���� ���Ͽ� ���� ����
				swap(arr[i], arr[i + 1]);
				sorted = false;
			}
		}
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