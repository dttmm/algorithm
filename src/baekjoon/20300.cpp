#include <fstream>
#include <iostream>
#include <algorithm>

/*
* ���� 4�� ���� 6��
* �׸���
* M�� �ּҷ� �ϱ� ���ؼ��� �� ���� ������ ���� �ִ밪�� �۰� ������ ���ؼ���
* ū ���� ���� ���� ���������
* �׷��� ���� ���� ���� ���� ū ���� ���ϰ�
* �״��� �������� �״��� ū���� ���Ѵٸ�
* �� ���� ������ ���� �ִ밪�� �۰� ������ �� �� ����
* �ٵ�, N�� Ȧ����� ���� ū ���� �ٸ� ���� ���ϸ� �ȵ�
* ū �� ��ü�� M�� �� ��� �ٸ� ���� ���Ѵٸ� M�� Ŀ���� �Ǽ� �ȵ�
* ���� ū ���� ������ ȥ�� �־�ߵǰ�
* ū ���� ������ ������ ���� ���� ���ؾߵ�
* 
* N�� ¦���� ����
* 0�� ��� N-1��, 1���� N-2��.. �� ¦����� �ǰ�
* Ȧ���� ����
* 0���� N-2��, 2���� N-3��.. �� ¦����� ��
* Ȧ¦�� ���� N-����� �������� �ϴ��� offset������ ���� ������
*/

using namespace std;

#define MAX_N 10000

int N;
long long arr[MAX_N];
long long maxVal;	// PT�� �ѹ� ���� �� �ִ� �ټս� ���� <- �� ���� ���� ���� ���� M��

void solve() {
	int offset = 1;	// 0���� N-offset���� ���� ���Ҳ���

	// N�� Ȧ���� ���
	if (N & 1) {
		maxVal = arr[N - 1];	// ���� ū�� �ִ� �ټֽ� ������ �ʱ�ȭ
		offset = 2;	// 0���� N-2���� �ΰ��� ¦��� ���ؾߵ�
	}

	// � �ΰ��� ¦��� �ټս� ������
	int half = N / 2;
	for (int i = 0; i < half; i++) {
		long long sum = arr[i] + arr[N - offset - i];
		maxVal = max(maxVal, sum);
	}
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/20300.txt", "r", stdin);

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	sort(arr, arr + N);

	solve();

	cout << maxVal;
}