#include<iostream>
#include<string>

using namespace std;

class Node {
public:
	string data;
	Node* prev;
	Node* next;

	Node() {}

	Node(string data_) {
		data = data_;
		prev = nullptr;
		next = nullptr;
	}
};

int N;
Node nodes[250000];
int index;
Node* cur;

void insertCurPrev(Node* node) {
	node->prev = cur->prev;
	node->next = cur;

	if (node->prev != nullptr) node->prev->next = node;
	if (node->next != nullptr) node->next->prev = node;
}

void insertCurNext(Node* node) {
	node->prev = cur;
	node->next = cur->next;

	if (node->prev != nullptr) node->prev->next = node;
	if (node->next != nullptr) node->next->prev = node;
}

void changeCurPrev() {
	if (cur->prev == nullptr) return;

	cur = cur->prev;
}

void changeCurNext() {
	if (cur->next == nullptr) return;

	cur = cur->next;
}

void print() {
	string s;
	if (cur->prev != nullptr) s += cur->prev->data + " ";
	else s += "(Null) ";

	s += cur->data + " ";

	if (cur->next != nullptr) s += cur->next->data;
	else s += "(Null)";

	cout << s << "\n";
}

int main() {

	string start;
	cin >> start;
	nodes[index] = Node(start);
	cur = &nodes[index++];

	cin >> N;
	for (int i = 0; i < N; i++) {
		int x;
		cin >> x;

		if (x == 1) {
			string s;
			cin >> s;

			nodes[index] = Node(s);
			insertCurPrev(&nodes[index++]);
		}
		else if (x == 2) {
			string s;
			cin >> s;

			nodes[index] = Node(s);
			insertCurNext(&nodes[index++]);
		}
		else if (x == 3) {
			changeCurPrev();
		}
		else if (x == 4) {
			changeCurNext();
		}

		print();
	}
}