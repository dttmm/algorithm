#include <iostream>
#include <fstream>

/*
* ���� 8�� ���� 5��
* ��ũ�帮��Ʈ
* ó������ ǳ���� ����Ʈ�� ��Ƽ�
* ǳ���� �Ͷ߸� ������ ���Ҹ� �������ٱ�
* �ƴϸ� ǳ���� �迭�� ��Ƽ�
* ǳ���� �Ͷ߸� ������ �迭�� �ִ� ���Ҹ� �̵������ٱ�
* �����ߴµ�
* �ʹ� ��ȿ�����̶�
* ���� ȿ������ ����� ������ �����ϴ�
* ��ũ�帮��Ʈ ����ī�ع���
* 
* ó������ Ŭ������ ��ũ�帮��Ʈ�� �����Ϸ��� ������
* �޸� ������ ������
* �迭�� �̿��ؼ� ���� ��ũ�� ����Ʈ ������
* ���� ��带 ����Ű�� �ִ� ���� �迭 nextArr��
* ���� ��带 ����Ű�� �ִ� ���� �迭 prevArr�� ���
* 
* ǳ���� �Ͷ߸������� ��� ����Ű�� �ִ� ���� ��������
*/
using namespace std;

#define MAX_N 1000

int N;
int nextArr[MAX_N + 1];	// ���� ��� ���� ����
int prevArr[MAX_N + 1];	// ���� ��� ���� ����
int goArr[MAX_N + 1];	// ��� �������� ��ĭ �����ϴ��� ����

int main() {

	freopen("res/baekjoon/2346.txt", "r", stdin);

	// �Է� �ޱ�
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> goArr[i];
	}

	// ��ũ�帮��Ʈ �ʱ�ȭ
	for (int i = 1; i <= N; i++) {
		nextArr[i] = i + 1;
		prevArr[i] = i - 1;
	}
	nextArr[N] = 1;
	prevArr[1] = N;

	int cur = 1;	// ���� ǳ��
	// ǳ�� ������ŭ �ݺ�
	for (int i = 0; i < N; i++) {
		cout << cur << " ";

		int next = nextArr[cur];
		int prev = prevArr[cur];

		// ��ũ ���� ����
		nextArr[prev] = next;
		prevArr[next] = prev;

		int go = goArr[cur];
		// ���������� �̵��� ���
		if (go > 0) {
			for (int j = 0; j < go; j++) cur = nextArr[cur];
		}
		// �������� �̵��� ���
		else {
			go *= -1;
			for (int j = 0; j < go; j++) cur = prevArr[cur];
		}
	}
}