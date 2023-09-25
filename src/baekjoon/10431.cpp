#include <fstream>
#include <iostream>

/*
* ���� 7�� ����+���� 9��
* ����
* ���������� ���������� �ϰ� �ְ�
* �������Ŀ��� ������ �Ͼ Ƚ���� ���ϸ� ��
* 
* �ƿ� C++���̷��� ������
* ide���� �Ǵµ� ä��ȯ�濡���� �ȵǸ� ��¥
* �迭 �ʱ�ȭ fill�� �ؾߵǴ°� �����ϳ�
*/
using namespace std;

int T;
int N = 20;
int arr[20];

// ����
void swap(int& a, int& b) {
	int temp = a;
	a = b;
	b = temp;
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/10431.txt", "r", stdin);

	cin >> T;

	for (int t = 1; t <= T; t++) {
		int n;
		cin >> n;

		// �迭 �ʱ�ȭ
		fill(arr, arr + N, 0);

		// �Է� �ޱ�
		for (int i = 0; i < N; i++) {
			cin >> arr[i];
		}

		// ���� ����
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N - i; j++) {
				if (arr[j] < arr[j - 1]) {
					swap(arr[j], arr[j - 1]);
					cnt++;
				}
			}
		}

		cout << t << " " << cnt << "\n";
	}
}