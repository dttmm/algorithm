#include <fstream>
#include <iostream>
#include <algorithm>

/*
* 설계 4분 구현 6분
* 그리디
* M을 최소로 하기 위해서는 두 값을 더했을 때의 최대값이 작게 나오기 위해서는
* 큰 값에 작은 값을 더해줘야함
* 그래서 제일 작은 수와 제일 큰 수를 더하고
* 그다음 작은수와 그다음 큰수를 더한다면
* 두 값을 더했을 때의 최대값을 작게 나오게 할 수 있음
* 근데, N이 홀수라면 제일 큰 수에 다른 수를 더하면 안됨
* 큰 수 자체가 M이 될 경우 다른 수와 더한다면 M이 커지게 되서 안됨
* 제일 큰 수는 무조건 혼자 있어야되고
* 큰 수를 제외한 나머지 수들 끼리 더해야됨
* 
* N이 짝수일 때는
* 0번 운동과 N-1번, 1번과 N-2번.. 을 짝지어야 되고
* 홀수일 때는
* 0번과 N-2번, 2번과 N-3번.. 을 짝지어야 됨
* 홀짝에 따라 N-몇번을 고려해줘야 하는지 offset변수를 통해 조정함
*/

using namespace std;

#define MAX_N 10000

int N;
long long arr[MAX_N];
long long maxVal;	// PT를 한번 받을 때 최대 근손실 정도 <- 이 값이 제일 작을 때가 M임

void solve() {
	int offset = 1;	// 0부터 N-offset까지 수를 더할꺼임

	// N이 홀수인 경우
	if (N & 1) {
		maxVal = arr[N - 1];	// 제일 큰수 최대 근솔실 값으로 초기화
		offset = 2;	// 0부터 N-2까지 두개씩 짝지어서 더해야됨
	}

	// 운동 두개씩 짝지어서 근손실 더해줌
	int half = N / 2;
	for (int i = 0; i < half; i++) {
		long long sum = arr[i] + arr[N - offset - i];
		maxVal = max(maxVal, sum);
	}
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/20300.txt", "r", stdin);

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	sort(arr, arr + N);

	solve();

	cout << maxVal;
}