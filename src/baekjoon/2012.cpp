#include <fstream>
#include <iostream>
#include <cstdlib>

/* ���� 3�� ���� 26��
* �׸���
* �� �������� �����
* ���� ����� ���� ������ �����ϰ�
* ���� ��� �տ��� ���� 1���� �ָ鼭
* �Ҹ��� ���̸� �����
* ���� �̹������ ���ܰ� ���� �������ؼ� ã�ƺ�����
* ���ٸ� ���̽��� ã�� ���Ͽ� �ٷ� �����غ�
* 
* ���� ����Ʈ, ������Ʈ �����ؼ� Ǯ���
* ���� ��Ʈ�� ����غ�
* 
* �����
* ���� ��Ʈ�� 80ms ���� ������
* �״����� ������Ʈ�� 104ms
* ����Ʈ�� �ð��ʰ��� ������
* ���� ����Ʈ ���� �����Ͱ� �־�����
* ������ �����
* 
* ��
* ���� ���� �����ϸ鼭 ������ �Ǽ��� ���� ���´�
* ţ ��Ʈ ������ �� arr[i] �̷� ���� ���ڷ� �Ѱ���� �ϴµ�
* �ڲ� i �̷� �ε����� �Ѱ��ִ� �Ǽ�
* ���� ��Ʈ �����Ͽ� ��Ʈ�ϰ�
* �������� ������ �����ִ� �Ǽ�
* �Ǽ� ���ɾ�
* 
*/

using namespace std;

#define MAX_N 500000

int N;
int arr[MAX_N];
int sorted[MAX_N];

void swap(int& a, int& b) {
	int temp = a;
	a = b;
	b = temp;
}

int selectPivot(int s, int e) {
	int mid = (s + e) / 2;
	swap(arr[mid], arr[e]);
	return arr[e];
}

int partition(int s, int e) {
	int pivot = selectPivot(s, e);

	int j = s;
	for (int i = s; i < e; i++) {
		if (arr[i] < pivot) {
			swap(arr[i], arr[j]);
			j++;
		}
	}

	swap(arr[j], arr[e]);
	return j;
}

void quickSort(int s, int e) {
	if (s >= e) return;
	int p = partition(s, e);

	quickSort(s, p - 1);
	quickSort(p + 1, e);
}

void merge(int s, int m, int e) {
	int i = s;
	int j = m + 1;
	int k = s;

	while (i <= m && j <= e) {
		if (arr[i] < arr[j]) sorted[k++] = arr[i++];
		else sorted[k++] = arr[j++];
	}

	while (i <= m) sorted[k++] = arr[i++];
	while (j <= e) sorted[k++] = arr[j++];

	for (int x = s; x <= e; x++) {
		arr[x] = sorted[x];
	}
}

void mergeSort(int s, int e) {
	if (s >= e)return;

	int m = (s + e) / 2;

	mergeSort(s, m);
	mergeSort(m + 1, e);

	merge(s, m, e);
}

long long solve() {
	long long sum = 0;
	for (int i = 0; i < N; i++) {
		int rank = i + 1;
		int diff = abs(arr[i] - rank);
		sum += diff;
	}

	return sum;
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/2012.txt", "r", stdin);

	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	mergeSort(0, N - 1);

	long long ret = solve();

	cout << ret;
}