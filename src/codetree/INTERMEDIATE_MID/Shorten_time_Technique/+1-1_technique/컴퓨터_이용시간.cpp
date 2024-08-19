#include <iostream>
#include <vector>
#include <tuple>
#include <algorithm>
#include <set>

using namespace std;

#define MAX_N 100000

int N;
int arr[MAX_N];
vector<tuple<int, int, int> > list;	// x시간, v시작종료 여부, id
set<int> Set;	// 사용 가능한 컴퓨터

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> N;
	for (int i = 0; i < N; i++) {
		int a, b;
		cin >> a >> b;

		list.push_back(make_tuple(a, 1, i));	// 시작 지점
		list.push_back(make_tuple(b, -1, i));	// 종료 지점
	}

	// 정렬
	sort(list.begin(), list.end());

	// 사용 가능한 컴퓨터 세팅
	for (int i = 1; i <= N; i++) {
		Set.insert(i);
	}

	for (auto it : list) {
		int x, v, id;
		tie(x, v, id) = it;

		// 시작지점이면 사용가능한 컴퓨터 사용
		if (v > 0) {
			int com = *Set.begin();
			Set.erase(com);

			arr[id] = com;
		}
		// 종료지점이면 사용한 컴퓨터 반납
		else {
			Set.insert(arr[id]);
		}
	}

	for (int i = 0; i < N; i++) {
		cout << arr[i] << " ";
	}
}