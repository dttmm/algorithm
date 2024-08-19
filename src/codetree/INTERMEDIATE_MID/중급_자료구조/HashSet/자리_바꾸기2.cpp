#include <iostream>
#include <unordered_set>
using namespace std;

#define MAX_N 100000

int N;
int K;
int arr[MAX_N + 1];
int Arr[MAX_N];	// �Է� ����
int Brr[MAX_N];
unordered_set<int> Set[MAX_N + 1];	// i���ڰ� ��ġ�ߴ� �ڸ� ����

// �ʱ�ȭ
void init() {
	for (int i = 1; i <= N; i++) {
		arr[i] = i;
		Set[i].insert(i);
	}
}

// ����
void swap(int a, int b) {
	int n = arr[a];
	int m = arr[b];

	Set[n].insert(b);
	Set[m].insert(a);

	arr[a] = m;
	arr[b] = n;
}

int main() {

	// �Է�
	cin >> N >> K;

	init();

	// �Է� ����
	for (int i = 0; i < K; i++) {
		cin >> Arr[i] >> Brr[i];
	}

	// 3K�� ����
	for (int k = 0; k < 3; k++) {
		for (int i = 0; i < K; i++) {
			int a = Arr[i];
			int b = Brr[i];

			swap(a, b);
		}
	}

	// ���
	for (int i = 1; i <= N; i++) {
		cout << Set[i].size() << "\n";
	}
}