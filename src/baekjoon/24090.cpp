#include <fstream>
#include <iostream>

/*
* ���� 11�� ����� 5��
* ������
* �� ���� ���迡 ������ ������ �ߴµ�
* �������� �䱸�ϴ� �� ���� ��İ�
* ���� ���� �����ϴ� �� ���� ��İ�
* �������ݾ� �ٸ� �κ��� �־
* �� ��� �״�� ����������
* �������� ���ϴ� ���� �ȳ��ͼ�
* �������� �䱸�ϴ� ���� ����� ���ߴ��� �������
* 
* swap(arr[low], arr[high])�� �ϱ� ���� �����̶� �簡
* swap(arr[high], arr[e])�� �ϱ� ���� ������ �߰� �Ǿ��ٴ��� ��
*/

using namespace std;

#define MAX_N 10000

int N;
int K;
int arr[MAX_N];
int ans[2];
int cnt;

// ����
void swap(int& a, int& b) {
	int temp =a;
	a = b;
	b = temp;
	cnt++;

	if (cnt == K) {
		ans[0] = a < b ? a : b;
		ans[1] = a > b ? a : b;
	}
}

// �ǹ� ���ϱ�
int selectPivot(int s, int e) {
	return arr[e];
}

// �ǹ��� �������� ������
int partition(int s, int e) {
	int pivot = selectPivot(s, e);

	int high = s;	// �ǹ����� ū �༮ ����ų �� ���
	// low: �ǹ����� �۰ų� ���� �༮ ����ų �� ���
	for (int low = s; low < e; low++) {
		if (arr[low] <= pivot) {
			swap(arr[low], arr[high]);
			high++;
		}
	}

	// �������� �ǹ��� �ǹ����� ū ���� �ٲ���
	if (high != e) swap(arr[high], arr[e]);

	// �ǹ� ��ġ ����
	return high;
}

// �� ����
void quickSort(int s, int e) {
	if (cnt >= K) return;
	if (s >= e) return;

	int index = partition(s, e);

	quickSort(s, index - 1);
	quickSort(index + 1, e);

}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/24090.txt", "r", stdin);

	// �Է�
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// ���� ����
	quickSort(0, N - 1);

	if (cnt >= K) {
		cout << ans[0] << " " << ans[1];
	}
	else cout << -1;
}