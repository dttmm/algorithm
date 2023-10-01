#include <iostream>
#include <fstream>
#include <algorithm>

/*
* ���� 1�� ���� 2��
* dp
* 2���� �迭�� ��ȸ�ϸ鼭
* ���� �������� ���µ� ������ �� �ִ� ������ �ִ밳���� ������
* �� �� �ִ� ������ ������, �Ʒ��ʻ��̹Ƿ�
* ���� ��ġ�� �������� ���ʿ��� ���� ��, ���ʿ��� ���� ��
* �� �߿� �ִ밪�� ��󰡸� ���� �����ϰ�
* ���� ��ġ�� ������ �ִ��� ���ο� ���� �߰����� ���� ������
*/

using namespace std;

#define MAX_N 300

int N;
int M;
int arr[MAX_N + 1][MAX_N + 1];
int d[MAX_N + 1][MAX_N + 1];	// dp�迭

// dp��ȸ
void solve() {
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			// ����, ���� �� �ִ밪 + ���� ��ġ ���� ����
			d[i][j] = max(d[i - 1][j], d[i][j - 1]) + arr[i][j];
		}
	}
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	freopen("res/baekjoon/14430.txt", "r", stdin);

	// �Է� �ޱ�
	cin >> N >> M;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			cin >> arr[i][j];
		}
	}

	// dp
	solve();

	cout << d[N][M];
}