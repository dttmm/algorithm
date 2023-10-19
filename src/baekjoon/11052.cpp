#include <fstream>
#include <iostream>
#include <algorithm>

/*
* ���� 2�� ���� 3��
* dp
* �ϳ��� �������� ������ ����� �� �����Ƿ�
* ���� dp�迭���� �� �������� �߰��� ����غ��鼭
* �� �߿��� �ִ밪�� ã��
*/

using namespace std;

#define MAX_N 1000

int N;
int p[MAX_N + 1];	// Pi�� ����
int d[MAX_N + 1];	// i���� �������� �� �ִ밪

// dp�� ����(���Ҿ�)
void setD() {
	for (int i = 1; i <= N; i++) {
		int maxVal = 0;

		// (���� ī����� �������� ���� �ִ� �ݾ� + �� �����ؾ� �ϴ� �ݾ�)�� �ִ밪 ����
		for (int j = i - 1; j >=0; j--) {
			maxVal = max(maxVal, d[j] + p[i - j]);
		}

		d[i] = maxVal;
	}
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/11052.txt", "r", stdin);

	// �Է�
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> p[i];
	}

	setD();

	cout << d[N];
}