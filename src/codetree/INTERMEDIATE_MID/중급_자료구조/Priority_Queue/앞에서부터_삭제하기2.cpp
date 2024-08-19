#include<iostream>
#include<queue>

using namespace std;

#define MAX_N 100000

int N;
int arr[MAX_N];
priority_queue<int> pq;

int main() {

	cin >> N;
	// �Է� �Ųٷ� �ޱ�
	for (int i = 0; i < N; i++) {
		int index = N - 1 - i;
		cin >> arr[index];
	}

	double ans = 0;
	int sum = 0;

	// ���� �Է� �������� �ڿ��� i��° ���ұ��� �������� �� (�տ������� K���� ������ �Ͱ� ����)
	for (int i = 0; i < N; i++) {
		int n = arr[i];
		sum += n;
		pq.push(-1 * n);

		// ���� �Է� �������� �� ������ ���Ҵ� �о�
		if (i == 0) continue;

		int min = pq.top() * -1;

		// ��հ� ����
		double avg = (double)(sum - min) / i;
		ans = avg > ans ? avg : ans;
	}

	cout << fixed;
	cout.precision(2);

	cout << ans;
}