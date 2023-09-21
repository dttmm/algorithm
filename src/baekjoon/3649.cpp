#include <iostream>
#include <fstream>
#include <algorithm>

/*
* ���� 4�� ���� 5�� ���� 13��
* ��������
* ���� ������ ������ ����
* ���ʿ��� �������͸� �̿��Ͽ�
* ������ ���̿� ��ġ�ϴ� �� ���� ������ ã��
* 
* �� ���� ���� ������ ���밪�� Ŀ���ϴϱ�
* ���ʿ��� ���� ������
* 
* ����ʰ�
* ??
* ���� �������� ���� ã�ٰ�
* arr �迭 �ʱ�ȭ �Ѵ�ð� arr[10000001] = {} ����µ�
* ����� �ʱ�ȭ ���ϳ�
* �̰� ���ִϱ� ����
* �̹� ����� �迭 �ʱ�ȭ �Ϸ��� fill_n()��� �ǳ�
*/

using namespace std;

int X;
int N;
int arr[1000001];

// ��������
void solve() {
	int L = 0;
	int R = N - 1;
	while (L < R) {
		int sum = arr[L] + arr[R];

		// ������ ���� �� �ִ� ���
		if (sum == X) {
			cout << "yes " << arr[L] << " " << arr[R] << endl;;
			return;
		}
		// ���ۺ��� �� ū ���
		else if (sum > X) {
			R--;
		}
		//���ۺ��� �� ���� ���
		else {
			L++;
		}
	}

	cout << "danger" << endl;
}

int main() {

	freopen("res/baekjoon/3649.txt", "r", stdin);

	// �Է� ���� ������ �ݺ�
	while (cin >> X) {
		X *= 10000000;
		cin >> N;

		// �Է� �ޱ�
		for (int i = 0; i < N; i++) {
			cin >> arr[i];
		}

		// ����
		sort(arr, arr + N);

		// ��������
		solve();
	}
}