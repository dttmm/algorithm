#include <iostream>
#include <fstream>

/*
* ���� 4�� ���� 10�� ����� 3��
* ��������
* �� �迭�� ������ ����
* ���� ū ���ڵ���� ����Ű�鼭
* A�� ����Ű�� ���ڰ� B�� ����Ű�� ���ں��� ū ���
* A�� ����Ű�� ���ڴ� ������ B�� �ִ� ���ڵ麸�� ũ�Ƿ�
* ���� B�� ����Ű�� �ִ� �ε����� �̿��Ͽ� ������ ī��Ʈ ����
* 
* ������ �� ���� ���� ���� ��Ʈ �����ؼ� Ǯ�
* 
* ���� �ȳ����淡 �����ߴµ� �Ǽ���
* i�� j�� �ٲ㾲�� �Ǽ�
* ���������� �Ⱦ��� �Ǽ� ���
*/

using namespace std;

#define MAX_N 20000

int N;
int M;
int arr[MAX_N];	// A
int brr[MAX_N];	// B
int sorted[MAX_N];	// ���� ��Ʈ�� �� ����� �迭

// ����
void merge(int* arr, int s, int m, int e) {
	int i = s;
	int j = m + 1;

	int k = s;
	while (i <= m && j <= e) {
		if (arr[i] < arr[j]) sorted[k++] = arr[i++];
		else sorted[k++] = arr[j++];
	}

	while (i <= m) {
		sorted[k++] = arr[i++];
	}

	while (j <= e) {
		sorted[k++] = arr[j++];
	}

	// ���� �迭�� ���� �ݿ�
	for (int i = s; i <= e; i++) {
		arr[i] = sorted[i];
	}
}

// ���� ��Ʈ
void mergeSort(int* arr, int s, int e) {
	// ���� ����
	if (s >= e) return;

	int m = (s + e) / 2;

	mergeSort(arr, s, m);
	mergeSort(arr, m + 1, e);

	merge(arr, s, m, e);
}

// ���� ����
int solve() {
	int i = N - 1;
	int j = M - 1;

	int cnt = 0;
	while (i >= 0 && j >= 0) {
		// A�� B���� ū ��� -> B�� �ִ� �͵� �� A���� ����
		if (arr[i] > brr[j]) {
			cnt += j + 1;
			i--;
		}
		// A�� B���� ũ�� ���� ���
		else j--;
	}

	return cnt;
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	freopen("res/baekjoon/7795.txt", "r", stdin);

	int T;
	cin >> T;
	for (int tc = 0; tc < T; tc++) {

		// �Է�
		cin >> N >> M;

		for (int i = 0; i < N; i++) {
			cin >> arr[i];
		}

		for (int i = 0; i < M; i++) {
			cin >> brr[i];
		}

		// ����
		mergeSort(arr, 0, N - 1);
		mergeSort(brr, 0, M - 1);

		int ret = solve();

		cout << ret << "\n";
	}
}