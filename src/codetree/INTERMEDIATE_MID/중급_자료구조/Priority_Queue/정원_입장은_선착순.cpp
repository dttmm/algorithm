#include<iostream>
#include<queue>
#include<vector>
using namespace std;

class Node {
public:
	int num;
	int a;
	int t;

	Node(int num_, int a_, int t_) {
		num = num_;
		a = a_;
		t = t_;
	}
};

// ����ð� a �켱
struct cmpA {
	bool operator()(Node a, Node b) {
		if (a.a != b.a) return !(a.a < b.a);
		return !(a.num < b.num);
	}
};

// num��° ��� �켱
struct cmpNum {
	bool operator()(Node a, Node b) {
		return !(a.num < b.num);
	}
};

int N;
priority_queue<Node, vector<Node>, cmpA> pq;
priority_queue<Node, vector<Node>, cmpNum> waitPQ;
int ans;

void findFirst();
void findWait(int endTime);
void findEnter(int endTime);

// ������ �ƹ��� ������ ���� �� ��� ã�� (a �켱)
void findFirst() {
	if (pq.empty()) return;

	Node node = pq.top();
	pq.pop();

	// �ش� ����� �������� ������ �ð�
	int endTime = node.a + node.t;

	findWait(endTime);
}

// ����� ��� ã��
void findWait(int endTime) {
	// �̹� ������ �� ����� ������ �ð� ���� �� ������� ��� ť�� ����
	while (!pq.empty() && pq.top().a <= endTime) {
		waitPQ.push(pq.top());
		pq.pop();
	}

	// ����� �ο� ������ ������ �� ���� ��� ã��
	if (waitPQ.empty()) findFirst();
	// ����� �ο������� �ش� �ο� ó��
	else  findEnter(endTime);
}

// ����ϴ� ����� �߿��� �� ��� ã�� (num �켱)
void findEnter(int endTime) {
	Node node = waitPQ.top();
	waitPQ.pop();

	int diff = endTime - node.a;
	ans = diff > ans ? diff : ans;

	endTime += node.t;

	// �ش� ����� ���� ������ �ð� �������� �ٽ� ����� ��� �߰���
	findWait(endTime);
}

int main() {

	cin >> N;
	for (int i = 1; i <= N; i++) {
		int a, t;
		cin >> a >> t;

		pq.emplace(i, a, t);
	}

	findFirst();

	cout << ans;
}