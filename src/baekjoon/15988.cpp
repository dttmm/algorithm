#include <iostream>
#include <fstream>

/*
* 설계 2분 구현 3분 디버깅 2분
* dp
* 1부터 경우의 수를 구해보며 규칙을 찾아봄
* 
* 1은 1개가 가능하고
* 
* 2는 1을 만들 수 있는 경우의 수(1)들에 +1을 하면 2를 만들수 있게 되고
* 자기 자신도 사용할 수 있으므로 총 경우의 수는 2개가 됨
* 
* 3은 2로 만들 수 있는 경우의 수(1+1, 2)들에 +1을 하면 3을 만들 수 있고,
* 1로 만들 수 있는 경우의 수(1)에 +2를 하면 또 3을 만들 수 있게 되고
* 3도 자기 자신을 사용할 수 있으므로 총 경우의 수는 4개가 됨
* 
* 이후 4부터는
* 3의 경우의 수에 +1,
* 2의 경우의 수에 +2,
* 1의 경우의 수에 +3을 하면 4를 만들 수 있음
* 그러므로 점화식은 d[i] = d[i - 1] + d[i - 2] + d[i - 3]
* 
* 틀림
* MOD만 long long형으로 해주면 될 줄 알았는데
* d[i - 1] + d[i - 2] + d[i - 3] <- 이 결과 자체가 오버플로우가 날 수 있구나
* 이런
*/

using namespace std;

#define MAX_N 1000000
#define MOD 1000000009L

int N;
int d[MAX_N + 1];

void setD() {
	d[1] = 1;
	d[2] = 2;
	d[3] = 4;

	for (int i = 4; i <= MAX_N; i++) {
		d[i] = ((long long)d[i - 1] + d[i - 2] + d[i - 3]) % MOD;
	}
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	freopen("res/baekjoon/15988.txt", "r", stdin);

	setD();

	int T;
	cin >> T;
	for (int tc = 0; tc < T; tc++) {
		cin >> N;

		cout << d[N] << "\n";
	}
}