#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_X 100
#define MAX_N 10
#define R 3

int N;
int cntArr[MAX_X + 1];	// ������ �����ϰ� �ִ� �� ī��Ʈ
int startArr[MAX_N];	// i�� ������ ������
int endArr[MAX_N];		// j�� ������ ������
int tr[R];
int ans;

// ��ģ ������ �ִ��� �˻�
bool isNotOver() {
	for (int i = 0; i <= MAX_X; i++) {
		if (cntArr[i] > 1) return false;
	}
	return true;
}

// ����
void solve(int k, int s) {
	if (k == R) {
		// ���� ���� ī��Ʈ ����
		for (int i = 0; i < R; i++) {
			int index = tr[i];
			int start = startArr[index];
			int end = endArr[index];

			for (int x = start; x <= end; x++) {
				cntArr[x]--;
			}
		}

		// ��ģ ������ �ִ��� �˻�
		bool ret = isNotOver();

		// ������ ī��Ʈ ����
		for (int i = 0; i < R; i++) {
			int index = tr[i];
			int start = startArr[index];
			int end = endArr[index];

			for (int x = start; x <= end; x++) {
				cntArr[x]++;
			}
		}

		// ��ģ ������ ���� ���
		if (ret) ans++;
	}
	else {
		for (int i = s; i < N; i++) {
			tr[k] = i;
			solve(k + 1, i + 1);
		}
	}
}

int main() {

	// �Է� �ޱ�
	cin >> N;
	for (int i = 0; i < N; i++) {
		int start;
		int end;
		cin >> start >> end;

		for (int x = start; x <= end; x++) {
			cntArr[x]++;
		}
		startArr[i] = start;
		endArr[i] = end;
	}

	solve(0, 0);

	cout << ans;
}