#include <iostream>
#include <fstream>

/*
* ���� 39�� �ٽ� ���� + ����� 1�ð� 12��
* ���׸�ƮƮ��
* ���� ���� �ϴ� ��Ĵ��
* ���Ҿ� ������� �ϸ鼭 ���׸�ƮƮ���� lazy propagation�� �����Ϸ� ������
* ���Ҿ� ������δ� ó���� �� ���� ���ܰ� ������
* ���� �θ��� lazy�� ó���ؾ��� ���� ���� �ִµ���
* �ڽ��ʿ��� �ش� ���� ó�� ���ϰ� �����ع����� ��Ȳ �߻�
* 
* ��¿ �� ���� �׵��� ����ؿԴ� ���Ҿ� ����� ������
* ž�ٿ� ������� �ٽ� �ڵ带 «
* 
* Ʋ��
* +=�� ����� �Ǵµ� =�� �ع����� �Ǽ�,
* tree[index]�� �����ؾ� �Ǵµ� tree[start]�� �����ع����� �Ǽ�
* ��� �ܽǼ��� ó������
* 
* Ʋ��
* ������ Ʋ�� �κ��� �� ã�ھ
* �����Խ��ǿ� �ö�� �ٸ� ����� �ڵ� ������
* �ٵ� �ƹ������� ���� �ʰ����� Ʋ�� ������ ��ã�ٰ�
* �ٵ� ���׸�ƮƮ�� ũ�⸦ ���� �迭�� 4�踦 ���� ���� �߰�
* �迭 ũ�� ������ Ʋ�ȴ���
* 
* �ִ� N�� ��Ȯ�� 2�� ���������
* ���׸�ƮƮ�� ũ�⸦ ���� �迭�� 2��� ���൵ �������
* ex) �ִ� N�� 4��� ���׸�ƮƮ�� �ִ� ũ��� 4*2�� ��
* 
* ������ �ִ� N�� �ٸ� ����� ������ ����
* ex) �ִ� N�� 5��� �ʿ��� ���׸�ƮƮ�� ũ��� 16�� ��
*	�׷��� ���׸�ƮƮ�� ũ�⸦ 2����ϸ� ũ�Ⱑ ���������� ������
*	4�踦 �ؾ� ũ�Ⱑ �������� ����!!
*/

using namespace std;

#define MAX_N 1000000

int N;
int M;
int K;
long long arr[MAX_N + 1];
long long tree[MAX_N * 4];
long long lazy[MAX_N * 4];
int treeSize;
int treeStartIndex;

// 2�� ���������� ���
int getLog2(int n) {
	if (n % 2 == 0) n--;	// ceilȿ��

	int ret = 0;
	while (n != 0) {
		ret++;
		n /= 2;
	}
	return ret;
}

// 2�� n���� ���
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

// ���� ����
void propagation(int index, int start, int end) {
	if (lazy[index] == 0) return;

	tree[index] += (lazy[index] * (end - start + 1));

	// ������尡 �ƴ� ���
	if (start != end) {	
		lazy[index * 2] += lazy[index];
		lazy[index * 2 + 1] += lazy[index];
	}
	lazy[index] = 0;
}

// ���� ������Ʈ�ϱ�
void update(int index, int start, int end, int left, int right, long long diff) {
	propagation(index, start, end);

	if (start > right || end < left) return;

	// ���� �ȿ� ���� ��
	if (start >= left && end <= right) {
		tree[index] += (diff * (end - start + 1));

		// ������尡 �ƴ� ���
		if (start != end) {
			lazy[index * 2] += diff;
			lazy[index * 2 + 1] += diff;
		}

		return;
	}

	int mid = (start + end) / 2;
	update(index * 2, start, mid, left, right, diff);
	update(index * 2 + 1, mid + 1, end, left, right, diff);

	// �� ����
	tree[index] = tree[index * 2] + tree[index * 2 + 1];
}

// ���� �� ����
long long getSum(int index, int start, int end, int left, int right) {
	propagation(index, start, end);

	if (start > right || end < left) return 0;

	if (start >= left && end <= right) return tree[index];

	int mid = (start + end) / 2;
	return getSum(index * 2, start, mid, left, right) + getSum(index * 2 + 1, mid + 1, end, left, right);
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	freopen("res/baekjoon/10999.txt", "r", stdin);

	// �Է�
	cin >> N >> M >> K;

	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// Ʈ�� �ʱ�ȭ
	initTree();

	// ���� ����
	for (int i = 0; i < M + K; i++) {
		int a;
		int b;
		int c;
		long long d;
		cin >> a >> b >> c;

		if (a == 1) {
			cin >> d;

			int startIndex = b - 1 + treeStartIndex;
			int endIndex = c - 1 + treeStartIndex;
			update(1, treeStartIndex, treeSize - 1, startIndex, endIndex, d);
		}
		else {
			int startIndex = b - 1 + treeStartIndex;
			int endIndex = c - 1 + treeStartIndex;

			long long ret = getSum(1, treeStartIndex, treeSize - 1, startIndex, endIndex);
			cout << ret << "\n";
		}
	}
}