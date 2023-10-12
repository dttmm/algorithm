#include <fstream>
#include <iostream>

/*
* 설계 1분 구현 3분 디버깅 2분
* 수학
* 맨 마지막 레벨의 경험치를 알면
* 앞에 있는 레벨들의 경험치는
* 뒤에서부터 차례대로 1씩 감소시키면 된다 생각하고
* 수학적으로 접근 (1부터 N까지의 합)
* 근데 앞에 있는 레벨의 경험치가 항상 뒤에 있는 레벨의 경험치보다 높은게 아니고
* 앞에 있는 레벨의 경험치가 뒤에 있는 레벨의 경험치보다 작다해도
* 항상 1씩 차이나는 것이 아니라 1보다 더 크게 차이 날 수도 있어서
* 수학 공식이 꼭 먹히는 것이 아님
* 결국 다른 방법으로 접근해야함
* 
* 다시구현 6분
* 그리디
* 레벨이 높은 곳부터 레벨이 낮으곳으로 한단계씩 탐색을 하며
* 이전(prev) 보다 경험치가 같거나 높은 경우
* 경험치를 조정해줌
* 다만, 경험치를 조정해 주었을 때
* prev값 갱신을 기존 경험치가 아니라
* 새로 갱신된 값으로 갱신을 해줘야 됨
*/

using namespace std;

#define MAX_N 100
#define MAX_X 20000

int N;
int arr[MAX_N];

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/2847.txt", "r", stdin);

	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	int ans = 0;
	int prev = MAX_X + 1;
	for (int i = N - 1; i >= 0; i--) {
		int n = arr[i];

		if (n >= prev) {
			ans += (n - (prev - 1));
			prev = prev - 1;
		}
		else {
			prev = n;
		}
	}

	cout << ans;
}