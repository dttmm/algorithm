#include <iostream>
#include <fstream>

/*
* ���� 1�� ���� 12��
* ����
* �ڵ�Ʈ������ ����� ��ų �����
* ������ ����ų� �̹� �湮�� ���� ��� ������ ȸ����
* 1���� �����ϴ� ���� �ƴ� ������ ���ں��� �迭�� ȸ���ϸ� ���ڸ� ä��
*/
using namespace std;

#define MAX_N 999

int N;
int arr[MAX_N][MAX_N];
int answer[2];	// ã���� �ϴ� ������ ��ǥ 0: i�� ����, 1: j�� ����
int target;		// ã���� �ϴ� ����
int di[] = { 1,0,-1,0 };
int dj[] = { 0,1,0,-1 };

// ���� üũ
bool isIn(int i, int j) {
	return (i >= 0 && i < N&& j >= 0 && j < N);
}

// �迭 ������
void solve() {
	// 0,0 ���� ����
	int i = 0;
	int j = 0;
	int dir = 0;
	int limit = N * N;
	// N*N�� �ݺ��ϸ� ���� ä��
	for (int k = N * N; k > 0; k--) {
		arr[i][j] = k;

		// ã�� ���� ã���� ���
		if (k == target) {
			answer[0] = i + 1;
			answer[1] = j + 1;
		}

		int newI = i + di[dir];
		int newJ = j + dj[dir];

		// ���� ����ų� �̹� �湮�� ���
		if (!isIn(newI, newJ) || arr[newI][newJ] != 0) {
			dir = (dir + 1) % 4;
			newI = i + di[dir];
			newJ = j + dj[dir];
		}

		// ���� ��ġ ����
		i = newI;
		j = newJ;
	}
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	freopen("res/baekjoon/1913.txt", "r", stdin);

	// �Է� �ޱ�
	cin >> N;
	cin >> target;

	// �迭 ������
	solve();

	// ���
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cout << arr[i][j] << " ";
		}
		cout << "\n";
	}

	cout << answer[0] << " " << answer[1];
}