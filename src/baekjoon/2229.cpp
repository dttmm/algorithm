#include <fstream>
#include <iostream>
#include <algorithm>

/*
* ���� 21�� ���� 11�� ���������� 31��
* dp
* �� 1000���� ������ 1�� �߶󺸰� 2�� �߶󺸰�... 1000�� �߶󺸰�
* ����� ���� ���� �ȵȴ�
* Ư�� ����d[i][j]������ �ִ밪��
* d[i][k]������ �ִ밪 + d[k+1][j]������ �ִ밪 (k=i~j-1)���� ���� �� ����
* 
* �ٸ� �� �ϳ��� ���� d[i][i]������ �ִ밪�� 0�̰� d[i+1][i+1]������ �ִ밪�� 0�� �ǹǷ�
* d[i][i+1]�� �ִ밪�� 0+0�� �Ǿ����
* �׷��� �ɰ� �������� �ִ밪�� ���ϰ�
* �ɰ��� ���� ��ü ������
* i ~ i+1���� ���Ұ� �ִ� ���̸� �������� ������
* Ư�� ���� [i][j]������ ���Ұ� �ִ� ���̸� ���ϱ� ����
* �ش� ���������� �ִ밪, �ּҰ� ���Ҹ� maxArr, minArr �迭�� ���� �����
* 
* �ٵ� ������ �ȳ���
* ������ �ɰ��� ���� �� k�� j-1���� ������ �Ǵµ�
* j���� ������ �ٶ��� ������Ͱ� �Ͼ��
* �ٵ� k���� ���ĵ� ���� �ȳ��ͼ� ���� �������� �����ߴµ�
* �˰��� ���� ���� ������ �����ϰ� �־���
* ���� �� �����µ� Ʋ���� �˾���
* �̷�.. �ð� ���ȳ�
*/

using namespace std;

#define MAX_N 1000

int N;
int arr[MAX_N];
int d[MAX_N][MAX_N];		// i~j���������� ���� �� ¥���� ������ �ִ밪
int maxArr[MAX_N][MAX_N];	// i~j���������� �ִ밪 ����
int minArr[MAX_N][MAX_N];	// i~j���������� �ּҰ� ����

// dp
int solve(int i, int j) {
	// �̹� ����� ���
	if (d[i][j] != -1) return d[i][j];
	// �ڱ� �ڽ��� ���
	if (i == j) return d[i][j] = 0;

	// ������ �ڸ��鼭 �ִ밪 ã��
	int maxValue = 0;
	for (int k = i; k < j; k++) {
		maxValue = max(maxValue, solve(i, k) + solve(k + 1, j));
	}

	// ������ �ִ밪�� �� �� ������ �ִ밪 ��
	maxValue = max(maxValue, maxArr[i][j] - minArr[i][j]);
	return d[i][j] = maxValue;
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/2229.txt", "r", stdin);

	// �Է� �ޱ�
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// �ʱ�ȭ
	for (int i = 0; i < MAX_N; i++) {
		fill(d[i], d[i] + MAX_N, -1);
	}

	// i~j���������� �ִ밪, �ּҰ� ���ϱ�
	for (int i = 0; i < N; i++) {
		int maxValue = 0;
		int minValue = 10000;
		for (int j = i; j < N; j++) {
			maxValue = max(maxValue, arr[j]);
			minValue = min(minValue, arr[j]);

			maxArr[i][j] = maxValue;
			minArr[i][j] = minValue;
		}
	}

	// dp
	solve(0, N - 1);

	cout << d[0][N - 1];
}