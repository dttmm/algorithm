#include <iostream>

using namespace std;

# define MAX_N 100000

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
}nodes[MAX_N];

int Q;
int cur = 1;
Node* head;
Node* tail;

// �� ��带 ����
void connect(Node* a, Node* b) {
	if (a != nullptr) a->next = b;
	if (b != nullptr) b->prev = a;
}

// ���� ��� �������� cnt�� ��� ����
void generate(int cnt) {
	// ��� ����
	for (int i = 0; i < cnt; i++) {
		nodes[cur + i + 1] = Node(cur + i + 1);
	}

	// ��� �̾��ֱ�
	for (int i = 0; i < cnt - 1; i++) {
		connect(&nodes[cur + i + 1], &nodes[cur + i + 1 + 1]);
	}
}

// node �տ� cnt�� ��� ����
void pushFront(Node* node, int cnt) {
	generate(cnt);

	Node* newHead = &nodes[cur + 1];
	Node* newTail = &nodes[cur + cnt];

	connect(node->prev, newHead);
	connect(newTail, node);

	if (node == head) head = newHead;

	cur += cnt;
}

// node �ڿ� cnt�� ��� ����
void pushBack(Node* node, int cnt) {
	generate(cnt);

	Node* newHead = &nodes[cur + 1];
	Node* newTail = &nodes[cur + cnt];

	connect(newTail, node->next);
	connect(node, newHead);

	if (node == tail) tail = newTail;

	cur += cnt;
}

// �� �� ��带 ���
void print(Node* node) {
	if (node->prev == nullptr || node->next == nullptr) cout << "-1\n";
	else cout << node->prev->data << " " << node->next->data << "\n";
}

// �ʱ�ȭ
void init() {
	nodes[cur] = Node(cur);
	head = &nodes[cur];
	tail = &nodes[cur];
}

int main() {

	init();

	cin >> Q;
	for (int q = 0; q < Q; q++) {
		int n;
		cin >> n;

		if (n == 1) {
			int a, b;
			cin >> a >> b;

			pushBack(&nodes[a], b);
		}
		else if (n == 2) {
			int a, b;
			cin >> a >> b;

			pushFront(&nodes[a], b);
		}
		else if (n == 3) {
			int a;
			cin >> a;

			print(&nodes[a]);
		}
	}
}