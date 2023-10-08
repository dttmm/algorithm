#include <iostream>
#include <fstream>

/*
* ���� 24��
* ���׸�ƮƮ��
* C++�� ���̺귯�� ������ ���׸�ƮƮ�� Ǯ�
*/

using namespace std;

#define MAX_N 1000000

int N;
int M;
int K;
long long arr[MAX_N];
long long tree[MAX_N * 2];
int treeSize;
int treeStartIndex;

// 2�� ���������� ��ȯ
int getLog2(long long n) {
	int ret = 0;
	if (n % 2 == 0) n--;	// ceilȿ��
	while (n != 0) {
		ret++;
		n /= 2;
	}
	return ret;
}

// 2�� n���� ��ȯ
long long getPow2(int n) {
	if (n == 1) return 2;

	long long half = getPow2(n / 2);

	if (n % 2 == 1) return half * half * 2;
	else return half * half;
}

// ���׸�ƮƮ�� �ʱ�ȭ
void initTree() {
	int h = getLog2(N);
	treeSize = getPow2(h + 1);
	treeStartIndex = treeSize / 2;

	// ������� �ʱ�ȭ
	for (int i = 0; i < N; i++) {
		tree[treeStartIndex + i] = arr[i];
	}

	// �� ���ϱ�
	for (int i = treeStartIndex - 1; i > 0; i--) {
		tree[i] = tree[i * 2] + tree[i * 2 + 1];
	}
}

// ������ ���� ���ϱ�
long long getSum(int start, int end, long long total) {
	if (start > end) return total;

	if (start % 2 == 1) total += tree[start];
	if (end % 2 == 0) total += tree[end];

	start = (start + 1) / 2;
	end = (end - 1) / 2;

	return getSum(start, end, total);
}

// ���� ������Ʈ�ϱ�
void  update(int index, long long diff) {
	if (index == 0) return;

	tree[index] += diff;

	update(index / 2, diff);
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	freopen("res/baekjoon/2042.txt", "r", stdin);

	// �Է�
	cin >> N >> M >> K;

	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	initTree();

	// ���� �����ϱ�
	for (int i = 0; i < M + K; i++) {
		int a;
		long long b;
		long long c;
		cin >> a >> b >> c;

		if (a == 1) {
			int index = b - 1 + treeStartIndex;
			update(index, c - tree[index]);
			tree[index] = c;
		}
		else {
			int startIndex = b - 1 + treeStartIndex;
			int endIndex = c - 1 + treeStartIndex;

			long long ret = getSum(startIndex, endIndex, 0);
			cout << ret << "\n";
		}
	}
}