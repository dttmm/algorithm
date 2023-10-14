#include <iostream>
#include <fstream>

/*
* ���� 8��
* ���� ��Ʈ
* ������Ʈ ���� �����ؼ� Ǯ��
*/

using namespace std;

#define MAX_N 500000

int N;
int K;
int arr[MAX_N];
int sorted[MAX_N];
int cnt;		// ���� Ƚ�� ī��Ʈ
int ans = -1;	// ���� ������ ����

// s~m, m+1~e �� ���� �����ϱ�
void merge(int s, int m, int e) {
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

	for (int x = s; x <= e; x++) {
		arr[x] = sorted[x];
		cnt++;	// ���� Ƚ�� ����

		if (cnt == K) {
			ans = arr[x];
			return;
		}
	}
}

// s~e���� ������Ʈ�ϱ�
void mergeSort(int s, int e) {
	if (cnt == K) return;
	if (s >= e) return;
	int m = (s + e) / 2;

	mergeSort(s, m);
	mergeSort(m + 1, e);

	merge(s, m, e);
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	freopen("res/baekjoon/24060.txt", "r", stdin);

	// �Է�
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// ���� ��Ʈ ����
	mergeSort(0, N - 1);

	cout << ans;
}