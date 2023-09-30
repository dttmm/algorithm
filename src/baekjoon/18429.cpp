#include <iostream>
#include <fstream>

/*
* ���� 2�� ���� 5��
* ����
* ������ ���� �� ������ ��� �ϳ��� ��󰡸�
* �������� ������ �߷��� ������ �߷����� ���� ��� ����ġ�⸦ ��
*/

using namespace std;

#define MAX_N 8

int N;
int K;
int arr[MAX_N];
bool visited[MAX_N];
int ans;

// ����
void solve(int k, int sum) {
	// �������� ������ �߷��� ������ �߷����� ���� ���
	if (sum < k * K) return;
	// N�� ���� ��� ���
	if (k == N) {
		ans++;
	}
	else {
		for (int i = 0; i < N; i++) {
			if (visited[i])  continue;

			visited[i] = true;
			int n = arr[i];
			solve(k + 1, sum + n);
			visited[i] = false;
		}
	}
}

int main() {

	freopen("res/baekjoon/18429.txt", "r", stdin);

	// �Է� �ޱ�
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// ����
	solve(0, 0);

	cout << ans;
}