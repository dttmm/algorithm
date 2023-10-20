#include <iostream>
#include <fstream>
#include <cmath>

/*
* 설계 5분 구현 8분 디버깅 19+2분
* dp
* 
* 숫자를 11까지 써보면서 정답의 패턴을 찾아봄
* 자신(n)보다 작은 제곱수 중에 가장 가까운 제곱수(m)와 나머지수(n-m)의 dp합을 하면 답이 나옴
* d[n] = d[m] + d[n-m]
* 
* 틀림
* 예외가 발생
* 위 점화식을 이용하면
* 128의 경우 d[121] + d[7]이 되어 답이 6이 나오지만
* 실제는 d[64] + d[64]로 답이 2가 나와야됨
* 결국 자신보다 작은 제곱수 중에 가장 가까운 제곱수만 따지면 안되고
* 자신보다 작은 모든 제곱수에 대해서 따져서
* 그 중에서 최소값을 찾아야 됨
* 
* 실패
* 근데 숫자가 커지면
* d[n] = d[1] + d[n-1]을 하는 과정에서
* d[1] + d[n-1]을 N번 수행하고
* 그 안에서
* d[n-1] = d[1] + d[n-2]도 N-1번 수행하게 되면서
* 거의 무한 재귀로 시간 초과발생
* d[n] = d[1] + d[n-1]인 경우를 따지주긴 따져줘야 되는데
* 시간초과를 해결할 방법이 떠오르지 않음
* 결국 다른 사람의 아이디어를 찾아봄
* 
* 와우
* d[n] = d[1] + d[n-1]이 되는 경우를
* d[n]을 모두 1로만 더했을 경우로 미리 초기화해 놓고
* d[n] = d[4] + d[n-4]부터 따져주기 시작했네
* 으렵다
*/

using namespace std;

#define MAX_N 100000

int N;
int d[MAX_N + 1];	// n을 나타내기 위한 제곱수들의 합의 최소값

// dp 배열 채우기
void setD() {
	for (int n = 2; n <= N; n++) {
		int nn = (int)sqrt(n);

		// 제곱수인 경우 -> 제곱수들의 합은 1
		if (nn * nn == n) {	
			d[n] = 1;
			continue;
		}

		// 자신보다 작은 제곱수(d[m])와 나머지(d[n - m])의 합 중에서 최소값 고름
		int minVal = d[n];
		for (int i = 2; i <= nn; i++) {
			int m = i * i;
			minVal = min(minVal, d[m] + d[n - m]);
		}
		d[n] = minVal;
	}
}

int main() {

	freopen("res/baekjoon/1699.txt", "r", stdin);

	cin >> N;
	
	// dp 배열 초기화 -> 일단 i을 만들기 위해 1만 더했을 경우를 넣음
	for (int i = 1; i <= MAX_N; i++) {
		d[i] = i;
	}

	// dp 배열 채우기
	setD();

	cout << d[N];
}