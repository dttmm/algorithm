#include <iostream>
#include <fstream>

/*
* ���� 1�� ���� 3�� ����� 5��
* ������
* ������ ������ ����
* 
* �ð��ʰ�
* N�� ũ�Ƿ�
* �и� C++����°� ������ ���� �� ���Ƽ� ã�ƺ�
* 1. ����� �� endl���� \n�� �����ٰ� ��
* 2. cin, cout�� �ӵ��� ������ ���� �� ����
* 1, 2�� ��θ� �������� �� �ð��ʰ��� �ȳ�����
* �ϳ��� �������� �����ϱ� �ð��ʰ�����
* �����δ� �� �ΰ��� ������ ����� ����
*/

#define MAX_R 100000

using namespace std;

int N;
int M;
int arr[MAX_R + 1];
int sum[MAX_R + 1];	// ������

// ������ ���ϱ�
void setSum() {
	for (int i = 1; i <= N; i++) {
		sum[i] = sum[i - 1] + arr[i];
	}
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/11441.txt", "r", stdin);

	// �Է� �ޱ�
	cin >> N;
	for (int k = 1; k <= N; k++) {
		cin >> arr[k];
	}

	cin >> M;

	// ������ ���ϱ�
	setSum();

	// ���� ��� ���ϱ�
	for (int k = 0; k < M; k++) {
		int s;
		int e;
		cin >> s >> e;

		int ret = sum[e] - sum[s - 1];

		cout << ret << "\n";
	}
}