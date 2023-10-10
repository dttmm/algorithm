#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 1000

int N;
int arr[MAX_N];	// �� ���� �� ����
int tr[MAX_N];	// ���� �����ϱ� ���� �迭
bool visited[MAX_N + 1];	// ���� ����ߴ��� ����

bool solve(int k, int n) {
	if (k == N) return true;
	else {
		int target = arr[k - 1] - n;	// �� ���� �� ������ �������� ������ ���� ���� ����

		// ���� �� ���� ���� ���
		if (target <= 0) return false;
		// �̹� �ش� ���� ����� ���	<- 1���� N���� ���ڵ��� �ѹ��� ����� �� �ֱ� ����
		if (visited[target]) return false;

		visited[target] = true;
		tr[k] = target;

		return solve(k + 1, target);
	}
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < N - 1; i++) {
		cin >> arr[i];
	}

	// n: ���� ���� ����
	// ���� ���� ���� ��, �Է����� ���� �� ���� �� ������ ��������
	// ���� ���ڸ� ������. �� N���� ���ڸ� ��� ������ �� �ִٸ� �װ��� �ٷ� ����
	for (int n = 1; n <= N; n++) {
		fill(visited, visited + MAX_N + 1, false);	// ���� ��뿩�� �ʱ�ȭ
		visited[n] = true;
		tr[0] = n;
		bool ret = solve(1, n);
		if (ret) break;
	}

	for (int i = 0; i < N; i++) {
		cout << tr[i] << " ";
	}
}