#include <iostream>

using namespace std;

#define MAX_N 100000

int N;
int arr[MAX_N];
int sorted[MAX_N];

// �����ϱ�
void merge(int left, int mid, int right) {
	// �� ����Ʈ�� ���� ��ġ
	int i = left;
	int j = mid + 1;

	int k = left;	// ���ս� ���Ҹ� ���� ��ġ
	// �� �� ����(ū) ���Ҹ� ���ĵ� �迭�� ����
	while (i <= mid && j <= right) {
		if (arr[i] < arr[j])
			sorted[k++] = arr[i++];
		else
			sorted[k++] = arr[j++];
	}

	// ���� ���ҵ� �Ű���
	while (i <= mid)sorted[k++] = arr[i++];
	while (j <= right)sorted[k++] = arr[j++];

	// ���� �迭�� ���� ���� ����
	for (int x = left; x <= right; x++) {
		arr[x] = sorted[x];
	}
}

// ���� ���� ����
void mergeSort(int left, int right) {
	if (left >= right) return;
	int mid = (left + right) / 2;

	// �� �κ����� ������ ���� ���� ��
	mergeSort(left, mid);
	mergeSort(mid + 1, right);

	// �� �κ� ��ġ��
	merge(left, mid, right);
}

int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		arr[i] = n;
	}

	mergeSort(0, N - 1);

	for (int i = 0; i < N; i++) {
		cout << arr[i] << " ";
	}
}