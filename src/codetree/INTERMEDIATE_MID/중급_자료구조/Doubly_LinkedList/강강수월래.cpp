#include <iostream>
#include<unordered_map>
using namespace std;

# define MAX_N 100000
# define MAX_M 100010

struct Node {
	int data;
	Node* prev;
	Node* next;

	Node() {}
	Node(int data_) {
		data = data_;
		prev = nullptr;
		next = nullptr;
	}
}nodes[MAX_N + 1];


int N;
int M;
int Q;
unordered_map<int, Node*> Map;
int nodeIndex = 1;


// 두 노드를 연결
void connect(Node* a, Node* b) {
	if (a != nullptr) a->next = b;
	if (b != nullptr) b->prev = a;
}

// 서로 다른 라인의 두 노드 연결
void connectBoth(Node* a, Node* b) {
	connect(b->prev, a->next);
	if (a->next == nullptr) connect(b->prev, a);

	connect(a, b);
	if (b->prev == nullptr) connect(b, a->next);

	if (a->next == nullptr && b->prev == nullptr) {
		connect(b, a);
	}
}

// a노드와 b노드 라인 분리
void cut(Node* a, Node* b) {
	Node* prevA = a->prev;
	connect(b->prev, a);
	connect(prevA, b);
}

// a노드 라인 출력
void print(Node* a) {
	// 최소 번호 찾기
	int Min = a->data;
	Node* node = a;

	do {
		Min = node->data < Min ? node->data : Min;
		node = node->next;
	} while (node != a && node != nullptr);

	node = Map[Min];

	do {
		cout << node->data << " ";
		node = node->prev;
	} while (node->data != Min && node != nullptr);
}

int main() {

	cin >> N >> M >> Q;

	// 초기 입력 세팅
	for (int i = 1; i <= M; i++) {
		int k;	// 현재 줄에 있는 사람 수
		cin >> k;

		// 노드 생성
		for (int j = 0; j < k; j++) {
			int n;	// 학생 번호
			cin >> n;

			nodes[nodeIndex] = Node(n);
			Map[n] = &nodes[nodeIndex];


			// 노드 연결
			if (j != 0) connect(&nodes[nodeIndex - 1], &nodes[nodeIndex]);
			if (j == k - 1) connect(&nodes[nodeIndex], &nodes[nodeIndex - (k - 1)]);

			nodeIndex++;
		}
	}

	for (int q = 0; q < Q; q++) {
		int n;
		cin >> n;

		if (n == 1) {
			int a, b;
			cin >> a >> b;

			connectBoth(Map[a], Map[b]);
		}
		else if (n == 2) {
			int a, b;
			cin >> a >> b;

			cut(Map[a], Map[b]);
		}
		else if (n == 3) {
			int a;
			cin >> a;

			print(Map[a]);
		}
	}
}