#include<iostream>

#define MAX_N 100000

using namespace std;

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

int N;
int Q;

void connect(Node* s, Node* e) {
	if (s != nullptr) s->next = e;
	if (e != nullptr) e->prev = s;
}

void disconnect(Node* node) {
	if (node->prev != nullptr) node->prev->next = node->next;
	if (node->next != nullptr) node->next->prev = node->prev;

	node->prev = nullptr;
	node->next = nullptr;
}

void print(Node* node) {
	if (node->prev != nullptr) cout << node->prev->data << " ";
	else cout << "0 ";

	if (node->next != nullptr) cout << node->next->data << " ";
	else cout << "0 ";

	cout << "\n";
}

int main() {

	cin >> N >> Q;
	for (int i = 1; i <= N; i++) {
		nodes[i] = Node(i);
	}

	for (int q = 0; q < Q; q++) {
		int x;
		cin >> x;
		int i;
		cin >> i;

		if (x == 1) {
			disconnect(&nodes[i]);
		}
		else if (x == 2) {
			int j;
			cin >> j;

			connect(nodes[i].prev, &nodes[j]);
			connect(&nodes[j], &nodes[i]);
		}
		else if (x == 3) {
			int j;
			cin >> j;

			connect(&nodes[j], nodes[i].next);
			connect(&nodes[i], &nodes[j]);
		}
		else if (x == 4) {
			print(&nodes[i]);
		}
	}

	for (int i = 1; i <= N; i++) {
		if (nodes[i].next != nullptr) cout << nodes[i].next->data << " ";
		else cout << "0 ";
	}
}