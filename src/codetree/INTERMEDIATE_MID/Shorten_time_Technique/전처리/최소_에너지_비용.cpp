#include <iostream>

using namespace std;

/*
* ���� 8�� ���� 4��
*/

#define MAX_N 100000
#define INF 1000000000
#define MIN(a, b) ((a) < (b) ? (a) : (b))

int N;
int arr[MAX_N + 1];		// i���� �������� ä��µ� �ʿ��� ���
int needs[MAX_N + 1];	// i���� �������� �Ѿ�� ���� ������
int L[MAX_N + 1];		// i���� �������� ä��µ� �ʿ��� ����� �ּҰ�

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	// �Է�
	cin >> N;
	for (int i = 1; i < N; i++) {
		cin >> needs[i];
	}
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}

	// L�迭 �ʱ�ȭ
	L[0] = INF;
	for (int i = 1; i < N; i++) {
		L[i] = MIN(L[i - 1], arr[i]);
	}

	// i���� �������� �Ѿ�� ���� �ʿ��� �������� �ּҰ����� �ΰ� ä��� ��
	long long ans = 0;
	for (int i = 1; i < N; i++) {
		ans += (long long)L[i] * needs[i];
	}

	cout << ans;
}