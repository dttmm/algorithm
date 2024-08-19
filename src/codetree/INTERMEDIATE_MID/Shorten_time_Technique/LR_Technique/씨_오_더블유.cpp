#include <iostream>
#include <string>

using namespace std;

#define MAX_N 100000

int N;
char arr[MAX_N + 2];
int CL[MAX_N + 2];	// ���ʿ������� C ���� ���� ��
int WR[MAX_N + 2];	// �����ʿ������� W ���� ���� ��


int main() {

	cin >> N;

	string s;
	cin >> s;
	for (int i = 1; i <= N; i++) {
		arr[i] = s[i - 1];
	}

	// CL ����
	for (int i = 1; i <= N; i++) {
		CL[i] = CL[i - 1] + (arr[i] == 'C');
	}

	// WR ����
	for (int i = N; i >= 1; i--) {
		WR[i] = WR[i + 1] + (arr[i] == 'W');
	}

	// O�� ���� C������ ������ R���� ���ϱ�
	long long total = 0;
	for (int i = 2; i < N; i++) {
		if (arr[i] != 'O') continue;

		total += (CL[i - 1] * WR[i + 1]);
	}

	cout << total;
}