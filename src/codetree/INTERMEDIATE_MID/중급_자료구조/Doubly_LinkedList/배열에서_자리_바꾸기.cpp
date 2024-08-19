#include<iostream>

#define MAX_N 250000

using namespace std;

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
int Q;

// �� ��� ����
void connect(Node* s, Node* e) {
	if (s != nullptr) s->next = e;
	if (e != nullptr) e->prev = s;
}

void swap(Node* a, Node* b, Node* c, Node* d) {
	Node* prevA = a->prev;
	Node* nextB = b->next;
	Node* prevC = c->prev;
	Node* nextD = d->next;

	// b�� c�� �پ��ִ� ���
	if (nextB == c) {
		prevC = d;
		nextB = a;
	}
	// d�� a�� �پ��ִ� ���
	if (nextD == a) {
		nextD = c;
		prevA = b;
	}

	connect(prevC, a);
	connect(b, nextD);

	connect(prevA, c);
	connect(d, nextB);
}

int main() {

	cin >> N >> Q;

	for (int i = 0; i <= N; i++) {
		nodes[i] = Node(i);

		// ������ ��峢�� ����
		if (i > 0) {
			connect(&nodes[i - 1], &nodes[i]);
			connect(&nodes[i], &nodes[i + 1]);
		}
	}

	// head ��� ����
	nodes[0].next = &nodes[1];
	for (int i = 0; i < Q; i++) {
		int a, b, c, d;
		cin >> a >> b >> c >> d;

		swap(&nodes[a], &nodes[b], &nodes[c], &nodes[d]);
	}

	// head�������� ����� ��� Ž��
	Node* head = &nodes[0];
	while (N--) {
		head = head->next;
		cout << head->data << " ";
	}
}