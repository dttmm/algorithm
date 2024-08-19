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

// 입장시간 a 우선
struct cmpA {
	bool operator()(Node a, Node b) {
		if (a.a != b.a) return !(a.a < b.a);
		return !(a.num < b.num);
	}
};

// num번째 사람 우선
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

// 정원에 아무도 없을때 먼저 들어갈 사람 찾음 (a 우선)
void findFirst() {
	if (pq.empty()) return;

	Node node = pq.top();
	pq.pop();

	// 해당 사람이 정원에서 나오는 시간
	int endTime = node.a + node.t;

	findWait(endTime);
}

// 대기할 사람 찾음
void findWait(int endTime) {
	// 이미 정원에 들어간 사람이 나오는 시간 전에 온 사람들을 대기 큐에 넣음
	while (!pq.empty() && pq.top().a <= endTime) {
		waitPQ.push(pq.top());
		pq.pop();
	}

	// 대기할 인원 없으면 정원에 들어갈 다음 사람 찾음
	if (waitPQ.empty()) findFirst();
	// 대기할 인원있으면 해당 인원 처리
	else  findEnter(endTime);
}

// 대기하는 사람들 중에서 들어갈 사람 찾음 (num 우선)
void findEnter(int endTime) {
	Node node = waitPQ.top();
	waitPQ.pop();

	int diff = endTime - node.a;
	ans = diff > ans ? diff : ans;

	endTime += node.t;

	// 해당 사람이 정원 나오는 시간 기준으로 다시 대기할 사람 추가함
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