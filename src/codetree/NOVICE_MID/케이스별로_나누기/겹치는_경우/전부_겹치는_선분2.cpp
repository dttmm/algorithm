#include <iostream>

using namespace std;

#define MAX_N 100

int N;
int visited[MAX_N + 1];	// �ش� ĭ�� �� �� ������ �����ִ���
int x1Arr[MAX_N];
int x2Arr[MAX_N];

// ���� �׸���
void draw(int x1, int x2) {
	for (int i = x1; i <= x2; i++) {
		visited[i]++;
	}
}

// 1���� �������� �� ��� ��ġ���� üũ
bool check(int x1, int x2) {
	for (int i = 1; i <= MAX_N; i++) {
		// ������ ������ �ִ� ����
		// -> ������ ������ �����Ͽ� N���� �����־��, �ش� ������ �����Ͽ��� �� N-1���� �����ְ� ��
		if (i >= x1 && i <= x2) {
			if (visited[i] == N) return true;
		}
		// ������ ������ ���� ����
		// -> �ش� ������ �����ϵ� ���� N-1���� �����־�� ��
		else {
			if (visited[i] == N - 1) return true;
		}
	}

	return false;
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		int x1, x2;
		cin >> x1 >> x2;

		// ���� �׸���
		draw(x1, x2);
		x1Arr[i] = x1;
		x2Arr[i] = x2;
	}

	// ���� �ϳ��� �����غ��鼭 �˻�
	for (int i = 0; i < N; i++) {
		bool ret = check(x1Arr[i], x2Arr[i]);

		if (ret) {
			cout << "Yes";
			return 0;
		}
	}

	cout << "No";
}