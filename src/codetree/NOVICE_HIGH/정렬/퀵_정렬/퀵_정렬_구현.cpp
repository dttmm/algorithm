#include <iostream>

using namespace std;

#define MAX_N 100000

int N;
int arr[MAX_N];

// ����
void swap(int& a, int& b) {
	int temp = a;
	a = b;
	b = temp;
}

// �ǹ��� ���� �ǹ����� ��ȯ
int selectPivot(int s, int e) {
	// ��� ������ �ǹ� ����
	int index = (s + e) / 2;

	// �ǹ��� ������ ������ ������
	swap(arr[index], arr[e]);

	return arr[e];
}

// �ǹ��� �������� ���ڸ� ������
// �ǹ��� �ε����� ��ȯ
int parition(int s, int e) {
	int pivot = selectPivot(s, e);

	// �ǹ����� ���ų� ū ���� �ε��� ����Ŵ
	int high = s;

	// low: �ǹ����� ���� ���� �ε��� ����Ŵ
	for (int low = s; low < e; low++) {
		if (arr[low] < pivot) {
			swap(arr[low], arr[high]);
			high++;
		}
	}

	// �������� �ǹ��̶� �ǹ����� ū�ų� ���� ���ϰ� ����
	swap(arr[e], arr[high]);

	// �ǹ� �ε��� ����
	return high;
}

// �� ����
void quickSort(int s, int e) {
	// ���� ����
	if (s >= e) return;

	// �ǹ��� �������� ������ �ǹ��� �ε����� ����
	int index = parition(s, e);

	// ��������
	quickSort(s, index - 1);
	quickSort(index + 1, e);
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// ����
	quickSort(0, N - 1);

	// ���
	for (int i = 0; i < N; i++) {
		cout << arr[i] << " ";
	}
}