#include <fstream>
#include <iostream>

/*
* 설계 6분 구현 3분
* dp
* N번째 좌석을 보는데
* 이미 N-1번째 까지는 답이 나왔다 생각하고 dp로 접근함
* 만약 N번째 좌석이 VIP라면
* N보다 앞에 있는 좌석은 더이상 움직임이 없으므로
* d[N] = d[N-1]이 됨
* 만약 N번째 좌석이 VIP가 아니라면
* N번째 좌석은 N-1번째 좌석과 이동이 가능하므로
* N과 N-1번째 자리를 바꾼다면 N-2번째 좌석까지는 움직임이 없게되고
* 자리를 바꾸지 않는 다면 N-1번째 좌석까지는 움직임이 없게됨
* 그래서 점화식이 다음과 같이 나옴
* d[N] = d[N-1] + d[N-2] <- 자리를 바꾸지 않았을 때 + 자리를 바꿨을 때
* 
* 근데 N과 N-1이 자리를 바꾸기 위해서는
* N이 VIP가 아니어야되지만
* N-1도 VIP가 아니어야 됨
* 그래서 N과 N-1모두 VIP가 아닌 경우에만 자리를 바꿔줄 수 있음
*/

using namespace std;

#define MAX_N 40

int N;
int M;
int d[MAX_N + 1];
bool isfixed[MAX_N + 1];	// VIP 여부

// dp
void solve() {
	d[0] = 1;	// 의미상 필요함
	d[1] = 1;
	for (int i = 2; i <= N; i++) {
		// i번째 좌석과 i-1번재 좌석이 VIP가 아닌 경우
		if (!isfixed[i] && !isfixed[i - 1]) {
			d[i] = d[i - 1] + d[i - 2];
		}
		else {
			d[i] = d[i - 1];
		}
	}
}

int main() {

	freopen("res/baekjoon/2302.txt", "r", stdin);

	// 입력
	cin >> N >> M;
	for (int i = 0; i < M; i++) {
		int n;
		cin >> n;
		isfixed[n] = true;
	}

	solve();

	cout << d[N];
}