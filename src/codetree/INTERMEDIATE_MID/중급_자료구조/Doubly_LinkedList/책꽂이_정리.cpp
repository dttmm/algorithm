#include<iostream>
#include<string>
using namespace std;

#define MAX_N 250000
#define MAX_K 100

struct Node {
	int data;
	Node* prev;
	Node* next;

	Node() {};

	Node(int data_) {
		data = data_;
		prev = nullptr;
		next = nullptr;
	}
}nodes[MAX_N + 1];

int index = 1;
Node* heads[MAX_K + 1];
Node* tails[MAX_K + 1];
int N;
int K;
int Q;

// 두 노드를 연결
void connect(Node* s, Node* e) {
	if (s != nullptr) s->next = e;
	if (e != nullptr) e->prev = s;
}

// 두 노드 연결 끊기
void disconnect(Node* s, Node* e) {
	if (s != nullptr) s->next = nullptr;
	if (e != nullptr) e->prev = nullptr;
}

// i 책꽂이 뒤에 책 추가
void addToTail(int i, Node* node) {
	Node*& head = heads[i];
	Node*& tail = tails[i];

	if (head == nullptr) {
		head = node;
		tail = node;
		return;
	}

	// 기존 tail과 연결
	connect(tail, node);

	// tail 갱신
	tail = tail->next;
}

// i 책꽂이 앞에 책 추가
void addToHead(int i, Node* node) {
	Node*& head = heads[i];
	Node*& tail = tails[i];

	if (head == nullptr) {
		head = node;
		tail = node;
		return;
	}

	// 기존 head와 연결
	connect(node, head);

	// head 갱신
	head = head->prev;
}

// 1. i번 책꽂이의 맨 앞 책을 j번 책꽂이의 맨 뒤에 꽂습니다. 
void moveHeadToTail(int i, int j) {
	if (heads[i] == nullptr) return;

	// i 맨 앞책 j 맨 뒤에 꽂음
	addToTail(j, heads[i]);

	// i 맨 앞 갱신
	heads[i] = heads[i]->next;

	// 기존 i 맨 앞 i 책꽂이와 연결 끊기
	if (heads[i] != nullptr)	disconnect(heads[i]->prev, heads[i]);

	if (heads[i] == nullptr) tails[i] = nullptr;
}

// 2. i번 책꽂이의 맨 뒷 책을 j번 책꽂이의 맨 앞에 꽂습니다. 
void moveTailToHead(int i, int j) {
	if (tails[i] == nullptr) return;

	// i 맨 뒤책 j 맨 앞에 꽂음
	addToHead(j, tails[i]);

	// i 맨 뒤 갱식
	tails[i] = tails[i]->prev;

	// 기존 i 맨 뒤 i 책꽂이와 연결 끊기
	if (tails[i] != nullptr)	disconnect(tails[i], tails[i]->next);

	if (tails[i] == nullptr) heads[i] = nullptr;
}

// 3. i번 책꽂이의 책을 모두 j번 책꽂이의 맨 앞으로 옮깁니다. 
void moveAllToHead(int i, int j) {
	if (heads[i] == nullptr) return;
	if (i == j) return;

	// i 맨 뒤책 j 맨 앞에 연결
	connect(tails[i], heads[j]);

	// j 책꽂이 비어 있는 경우
	if (tails[j] == nullptr) tails[j] = tails[i];

	// j 맨 앞 갱신
	heads[j] = heads[i];

	// i 책꽂이 비우기
	heads[i] = nullptr;
	tails[i] = nullptr;
}

// 4. i번 책꽂이의 책을 모두 j번 책꽂이의 맨 뒤로 옮깁니다. 
void moveAllToTail(int i, int j) {
	if (heads[i] == nullptr) return;
	if (i == j) return;

	// j 맨 뒤 i 맨 앞이랑 연결
	connect(tails[j], heads[i]);

	// j 책꽂이 비어있는 경우
	if (heads[j] == nullptr) heads[j] = heads[i];

	// j 맨 뒤 갱신
	tails[j] = tails[i];

	// i 책꽂이 비우기
	heads[i] = nullptr;
	tails[i] = nullptr;
}

int main() {

	cin >> N >> K >> Q;

	// 책 생성해서 1번 책꽂이에 추가
	for (int i = 1; i <= N; i++) {
		nodes[index++] = Node(i);

		addToTail(1, &nodes[i]);
	}

	for (int q = 0; q < Q; q++) {
		int x, i, j;
		cin >> x >> i >> j;

		if (x == 1) {
			moveHeadToTail(i, j);
		}
		else if (x == 2) {
			moveTailToHead(i, j);
		}
		else if (x == 3) {
			moveAllToHead(i, j);
		}
		else if (x == 4) {
			moveAllToTail(i, j);
		}
	}

	// 출력
	for (int i = 1; i <= K; i++) {
		Node* head = heads[i];

		int cnt = 0;
		string s = "";
		while (head != nullptr) {
			s += to_string(head->data) + " ";
			head = head->next;
			cnt++;
		}
		cout << cnt << " " << s << "\n";
	}
}