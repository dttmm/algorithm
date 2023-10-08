#include <iostream>
#include <fstream>

/*
* 구현 39분 다시 구현 + 디버깅 1시간 12분
* 세그먼트트리
* 원래 내가 하던 방식대로
* 바텀업 방식으로 하면서 세그먼트트리에 lazy propagation을 적용하려 했지만
* 바텀업 방식으로는 처리할 수 없는 예외가 생겼음
* 아직 부모쪽 lazy에 처리해야할 값이 남아 있는데도
* 자식쪽에서 해당 값을 처리 못하고 종료해버리는 상황 발생
* 
* 어쩔 수 없이 그동안 고수해왔던 바텀업 방식을 버리고
* 탑다운 방식으로 다시 코드를 짬
* 
* 틀림
* +=를 해줘야 되는데 =를 해버리는 실수,
* tree[index]를 조작해야 되는데 tree[start]를 조작해버리는 실수
* 등등 잔실수들 처리해줌
* 
* 틀림
* 도저히 틀린 부분을 못 찾겠어서
* 질문게시판에 올라온 다른 사람들 코드 참고함
* 근데 아무리봐도 로직 쪽같은데 틀린 이유를 못찾다가
* 다들 세그먼트트리 크기를 원본 배열의 4배를 해준 것을 발견
* 배열 크기 때문에 틀렸던것
* 
* 최대 N이 정확히 2의 제곱수라면
* 세그먼트트리 크기를 원본 배열의 2배로 해줘도 상관없음
* ex) 최대 N이 4라면 세그먼트트리 최대 크기는 4*2가 됨
* 
* 하지만 최대 N이 다른 수라면 문제가 생김
* ex) 최대 N이 5라면 필요한 세그먼트트리 크기는 16이 됨
*	그래서 세그먼트트리 크기를 2배로하면 크기가 부족해지기 때문에
*	4배를 해야 크기가 부족하지 않음!!
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

// 2의 몇제곱인지 계산
int getLog2(int n) {
	if (n % 2 == 0) n--;	// ceil효과

	int ret = 0;
	while (n != 0) {
		ret++;
		n /= 2;
	}
	return ret;
}

// 2의 n제곱 계산
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

// 지연 전달
void propagation(int index, int start, int end) {
	if (lazy[index] == 0) return;

	tree[index] += (lazy[index] * (end - start + 1));

	// 리프노드가 아닌 경우
	if (start != end) {	
		lazy[index * 2] += lazy[index];
		lazy[index * 2 + 1] += lazy[index];
	}
	lazy[index] = 0;
}

// 구간 업데이트하기
void update(int index, int start, int end, int left, int right, long long diff) {
	propagation(index, start, end);

	if (start > right || end < left) return;

	// 범위 안에 있을 때
	if (start >= left && end <= right) {
		tree[index] += (diff * (end - start + 1));

		// 리프노드가 아닌 경우
		if (start != end) {
			lazy[index * 2] += diff;
			lazy[index * 2 + 1] += diff;
		}

		return;
	}

	int mid = (start + end) / 2;
	update(index * 2, start, mid, left, right, diff);
	update(index * 2 + 1, mid + 1, end, left, right, diff);

	// 합 갱신
	tree[index] = tree[index * 2] + tree[index * 2 + 1];
}

// 구간 합 리턴
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

	// 입력
	cin >> N >> M >> K;

	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// 트리 초기화
	initTree();

	// 쿼리 수행
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