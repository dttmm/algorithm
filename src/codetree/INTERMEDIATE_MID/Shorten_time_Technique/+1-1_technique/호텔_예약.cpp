#include <iostream>
#include <tuple>
#include <queue>
#include <vector>
#include <algorithm>

using namespace std;

#define MAX_N 100000
#define MAX(a, b) ((a) > (b) ? (a) : (b))

int N;
int rooms[MAX_N + 1];
priority_queue<int> pq;
vector<tuple<int, int, int> > lists;

// 이용 가능한 방 초기화
void init() {
	for (int i = 1; i <= MAX_N; i++) {
		pq.push(-1 * i);
	}
}

int main() {

	init();

	cin >> N;
	for (int i = 0; i < N; i++) {
		int s, e;
		cin >> s >> e;

		lists.push_back(make_tuple(s - 1, 1, i));	// 시작 지점, 종료 지점과 시작 지점이 겹치는 경우 처리하기 위해 -1
		lists.push_back(make_tuple(e, -1, i));	// 종료 지점
	}

	// 정렬
	sort(lists.begin(), lists.end());

	int maxRoom = 0;	// 최대 방 번호
	for (auto it : lists) {
		int x, v, id;
		tie(x, v, id) = it;

		// 시작 지점인 경우
		if (v > 0) {
			// 가능한 방 할당
			int n = pq.top() * -1;
			pq.pop();

			rooms[id] = n;
			maxRoom = MAX(maxRoom, n);
		}
		// 종료 지점인 경우
		else {
			// 방 반납
			int n = rooms[id];
			pq.push(-1 * n);
		}
	}

	cout << maxRoom;

}