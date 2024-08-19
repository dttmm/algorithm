#include <iostream>

using namespace std;

#define MAX_N 200000

/*
* in 2001 out 10727
*/

struct Node {
	int i;
	int j;
	int data;
	bool isMax;

	Node() {}
	Node(int i_, int j_, int data_) {
		i = i_;
		j = j_;
		data = data_;
		isMax = false;
	}
}nodes[MAX_N];

int N;
int M;
int Q;
Node* row[MAX_N];
Node* col[MAX_N];
int sum;
long long total;

void init() {
	sum = 0;
	total = 0;
}

void setMax() {
	// row���� max��� ����
	for (int i = 0; i < N; i++) {
		Node* maxNode = new Node(-1, -1, 0);
		for (int j = 0; j < M; j++) {
			int index = i * M + j;

			if (nodes[index].data > maxNode->data) maxNode = &nodes[index];
		}
		row[i] = maxNode;
	}

	// col���� max��� ����
	for (int j = 0; j < M; j++) {
		Node* maxNode = new Node(-1, -1, 0);
		for (int i = 0; i < N; i++) {
			int index = i * M + j;

			if (nodes[index].data > maxNode->data) maxNode = &nodes[index];
		}
		col[j] = maxNode;
	}

	// row�� col���� ��� �ִ밪�� ��� ã��
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (row[i] == col[j]) {
				row[i]->isMax = true;
				sum++;
			}
		}
	}
}

void update(int i, int j, int val) {
	int index = i * M + j;

	// ���� row�� �ִ밪�� ��
	if (val > row[i]->data) {
		Node* maxNode = row[i];
		if (maxNode->isMax) {
			maxNode->isMax = false;
			sum--;
		}

		row[i] = &nodes[index];
	}

	// ���� col�� �ִ밪�� ��
	if (val > col[j]->data) {
		Node* maxNode = col[j];
		if (maxNode->isMax) {
			maxNode->isMax = false;
			sum--;
		}

		col[j] = &nodes[index];
	}

	nodes[index].data = val;
	// row�� col �Ѵ� �ִ밪�̸�
	if (&nodes[index] == row[i] && &nodes[index] == col[j]) {
		nodes[index].isMax = true;
		sum++;
	}

	total += sum;

}

int main() {

	int T;
	cin >> T;

	for (int tc = 1; tc <= T; tc++) {
		cin >> N >> M >> Q;
		init();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int n;
				cin >> n;

				int index = i * M + j;
				nodes[index] = Node(i, j, n);
			}
		}

		setMax();

		for (int q = 0; q < Q; q++) {
			int i, j, val;
			cin >> i >> j >> val;

			update(i - 1, j - 1, val);
		}

		cout << "#" << tc << " " << total << "\n";
	}
}