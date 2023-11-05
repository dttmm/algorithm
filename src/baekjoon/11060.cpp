#include <fstream>
#include <iostream>
#include <algorithm>

/*
* ���� 3�� ���� 5��
* dp
* ĭ�� ��ȸ�ϸ鼭
* ���� ĭ���� �� �� �ִ� ���� ĭ�� Ž���ϰ�
* ���� ĭ�� ������ ������ ���� Ƚ���� �ּҰ����� ������
* dp�迭 �ʱⰪ�� INF�� �����ؼ�
* ���� ĭ�� INF���
* �տ��� ���� �����ؼ� ������ �� ���� ĭ�̹Ƿ�
* INFĭ�� �ǳʶ�
*/

using namespace std;

#define MAX_N 1000
#define INF 100000000

int N;
int arr[MAX_N];
int d[MAX_N];

// dp�迭 �ʱ�ȭ
void init() {
	// 0�� �ε����� ���������̹Ƿ� 1�� �ε������� �ʱ�ȭ
	for (int i = 1; i < N; i++) {
		d[i] = INF;
	}
}

// dp
void solve() {
	// ��� ĭ�� ��ȸ�ϸ鼭
	for (int i = 0; i < N; i++) {
		// ������ �� ���� ĭ�� ���
		if (d[i] == INF) continue;

		// ������ �� �ִ� ��ŭ ������
		int n = arr[i];
		for (int j = i + 1; j <= i + n; j++) {
			// ������ �Ѿ�� ���
			if (j >= N) break;

			// ����Ƚ�� ������Ʈ
			d[j] = min(d[j], d[i] + 1);
		}
	}
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/11060.txt", "r", stdin);

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	init();

	solve();

	// ������ ĭ�� ������ �� ���� ���
	if (d[N - 1] == INF) d[N - 1] = -1;

	cout << d[N - 1];
}