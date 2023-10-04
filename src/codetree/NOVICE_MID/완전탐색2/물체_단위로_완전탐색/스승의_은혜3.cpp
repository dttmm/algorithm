#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 1000

class Node {
public:
	int num;	// 학생 번호
	int p;		// 선물 가격
	int sum;	// 선물 가격 + 배송비

	Node() {}
	Node(int num, int p, int s) {
		this->num = num;
		this->p = p;
		this->sum = p + s;
	}
};

int N;
int B;
Node* nodes[MAX_N];	// node 정보 순서대로 담은 배열
Node* arr[MAX_N];	// 정렬할 때 사용할 배열
int ans;

// 비교
bool cmp(Node* a, Node* b) {
	return (*a).sum < (*b).sum;
}

// 완탐
void solve() {
	// 학생 i에게 반값할인 적용
	for (int i = 0; i < N; i++) {
		Node* picked = nodes[i];

		// 반값할인 적용
		(*picked).sum -= (*picked).p * 0.5;

		// 선물가격 + 배송비 낮은 순으로 정렬
		sort(arr, arr + N, cmp);

		int total = 0;
		int cnt = 0;
		// 남은 학생들 선물 사기
		for (int j = 0; j < N; j++) {
			Node* node = arr[j];

			total += (*node).sum;

			// 예산 초과했는지 확인
			if (total > B) break;

			cnt++;
			ans = max(ans, cnt);
		}

		// 할인 했던거 복구
		(*picked).sum += (*picked).p * 0.5;
	}
}

int main() {

	// 입력 받기
	cin >> N >> B;
	for (int i = 0; i < N; i++) {
		int p;
		int s;
		cin >> p >> s;

		Node* node = new Node(i, p, s);
		nodes[i] = node;
		arr[i] = node;
	}

	// 완탐
	solve();

	cout << ans;
}