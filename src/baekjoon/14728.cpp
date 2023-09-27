#include <iostream>
#include <fstream>
#include <algorithm>

/*
* ���� 3�� ���� 6�� ����� 8��
* dp
* �������� dp����
* ������ �ϳ��� �����ذ���
* �ش� ������� ������ ���� �� �ִ밪�� ���س���
* 
* Ʋ��
* ������ �������� ���� �������� �ʾ��� ��(�밢��)�� �����
* ��, �� ���⿡�� ���� �ִ밪�� �������ߵ�
*/
using namespace std;

#define MAX_N 100
#define MAX_T 10000

int N;
int T;
int krr[MAX_N + 1];	// i������ ���� �ð�
int srr[MAX_N + 1];	// i������ ����
int d[MAX_N + 1][MAX_T + 1];	// i������� �����ϰ� j�ð��� �־����� �� �ִ밪

int main() {

	freopen("res/baekjoon/14728.txt", "r", stdin);

	// �Է� �ޱ�
	cin >> N >> T;
	for (int i = 1; i <= N; i++) {
		int k;
		int s;
		cin >> k >> s;

		krr[i] = k;
		srr[i] = s;
	}

	// ������ dp
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= T; j++) {
			int k = krr[i];
			int s = srr[i];

			// ��, �� �ִ밪 ��
			d[i][j] = max(d[i][j], d[i][j - 1]);
			d[i][j] = max(d[i][j], d[i - 1][j]);

			// i������ �������� �ʾ��� ���� �������� �� �ִ밪 ��
			if (j >= k) d[i][j] = max(d[i][j], d[i - 1][j - k] + s);
		}
	}

	cout << d[N][T];
}