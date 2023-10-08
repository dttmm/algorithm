#include <iostream>
#include <fstream>

/*
* 구현 24분
* 세그먼트트리
* C++로 라이브러리 사용없이 세그먼트트리 풀어봄
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

// 2의 몇제곱인지 반환
int getLog2(long long n) {
	int ret = 0;
	if (n % 2 == 0) n--;	// ceil효과
	while (n != 0) {
		ret++;
		n /= 2;
	}
	return ret;
}

// 2의 n제곱 반환
long long getPow2(int n) {
	if (n == 1) return 2;

	long long half = getPow2(n / 2);

	if (n % 2 == 1) return half * half * 2;
	else return half * half;
}

// 세그먼트트리 초기화
void initTree() {
	int h = getLog2(N);
	treeSize = getPow2(h + 1);
	treeStartIndex = treeSize / 2;

	// 리프노드 초기화
	for (int i = 0; i < N; i++) {
		tree[treeStartIndex + i] = arr[i];
	}

	// 합 구하기
	for (int i = treeStartIndex - 1; i > 0; i--) {
		tree[i] = tree[i * 2] + tree[i * 2 + 1];
	}
}

// 구간합 쿼리 구하기
long long getSum(int start, int end, long long total) {
	if (start > end) return total;

	if (start % 2 == 1) total += tree[start];
	if (end % 2 == 0) total += tree[end];

	start = (start + 1) / 2;
	end = (end - 1) / 2;

	return getSum(start, end, total);
}

// 원소 업데이트하기
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

	// 입력
	cin >> N >> M >> K;

	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	initTree();

	// 쿼리 수행하기
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