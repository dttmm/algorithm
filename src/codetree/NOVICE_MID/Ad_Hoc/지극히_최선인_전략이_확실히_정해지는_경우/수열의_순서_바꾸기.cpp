#include <iostream>

using namespace std;

#define MAX_N 100

int N;
int arr[MAX_N + 1];

int main() {

	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}

	int prev = arr[N];
	int index = 0;
	// �ڿ������� Ž���ϸ鼭
	// ������ ���Դ� ���ں��� ū ���ڰ� ���´ٸ�
	// �ش� ����(ū ����)������ ������ ������ �;��ϹǷ�
	// �ش� �ε��������� �տ������� ������ �ٲ�ߵ�
	// �׸��� �ٷ� �ش� �ε����� �ٷ� �ٲٴ� Ƚ���� ��
	for (int i = N; i > 0; i--) {
		int n = arr[i];
		if (n > prev) {
			index = i;
			break;
		}
		prev = n;
	}

	cout << index;
}