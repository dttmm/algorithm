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

// �� ��带 ����
void connect(Node* s, Node* e) {
	if (s != nullptr) s->next = e;
	if (e != nullptr) e->prev = s;
}

// �� ��� ���� ����
void disconnect(Node* s, Node* e) {
	if (s != nullptr) s->next = nullptr;
	if (e != nullptr) e->prev = nullptr;
}

// i å���� �ڿ� å �߰�
void addToTail(int i, Node* node) {
	Node*& head = heads[i];
	Node*& tail = tails[i];

	if (head == nullptr) {
		head = node;
		tail = node;
		return;
	}

	// ���� tail�� ����
	connect(tail, node);

	// tail ����
	tail = tail->next;
}

// i å���� �տ� å �߰�
void addToHead(int i, Node* node) {
	Node*& head = heads[i];
	Node*& tail = tails[i];

	if (head == nullptr) {
		head = node;
		tail = node;
		return;
	}

	// ���� head�� ����
	connect(node, head);

	// head ����
	head = head->prev;
}

// 1. i�� å������ �� �� å�� j�� å������ �� �ڿ� �Ƚ��ϴ�. 
void moveHeadToTail(int i, int j) {
	if (heads[i] == nullptr) return;

	// i �� ��å j �� �ڿ� ����
	addToTail(j, heads[i]);

	// i �� �� ����
	heads[i] = heads[i]->next;

	// ���� i �� �� i å���̿� ���� ����
	if (heads[i] != nullptr)	disconnect(heads[i]->prev, heads[i]);

	if (heads[i] == nullptr) tails[i] = nullptr;
}

// 2. i�� å������ �� �� å�� j�� å������ �� �տ� �Ƚ��ϴ�. 
void moveTailToHead(int i, int j) {
	if (tails[i] == nullptr) return;

	// i �� ��å j �� �տ� ����
	addToHead(j, tails[i]);

	// i �� �� ����
	tails[i] = tails[i]->prev;

	// ���� i �� �� i å���̿� ���� ����
	if (tails[i] != nullptr)	disconnect(tails[i], tails[i]->next);

	if (tails[i] == nullptr) heads[i] = nullptr;
}

// 3. i�� å������ å�� ��� j�� å������ �� ������ �ű�ϴ�. 
void moveAllToHead(int i, int j) {
	if (heads[i] == nullptr) return;
	if (i == j) return;

	// i �� ��å j �� �տ� ����
	connect(tails[i], heads[j]);

	// j å���� ��� �ִ� ���
	if (tails[j] == nullptr) tails[j] = tails[i];

	// j �� �� ����
	heads[j] = heads[i];

	// i å���� ����
	heads[i] = nullptr;
	tails[i] = nullptr;
}

// 4. i�� å������ å�� ��� j�� å������ �� �ڷ� �ű�ϴ�. 
void moveAllToTail(int i, int j) {
	if (heads[i] == nullptr) return;
	if (i == j) return;

	// j �� �� i �� ���̶� ����
	connect(tails[j], heads[i]);

	// j å���� ����ִ� ���
	if (heads[j] == nullptr) heads[j] = heads[i];

	// j �� �� ����
	tails[j] = tails[i];

	// i å���� ����
	heads[i] = nullptr;
	tails[i] = nullptr;
}

int main() {

	cin >> N >> K >> Q;

	// å �����ؼ� 1�� å���̿� �߰�
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

	// ���
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