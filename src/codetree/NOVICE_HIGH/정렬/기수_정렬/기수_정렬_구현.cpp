#include <iostream>
#include <vector>

using namespace std;

#define MAX_N 100

int N;
vector<int> v;

// pos번째 자릿수 반환
int getDigit(int n, int pos) {
	for (int i = 0; i < pos; i++) {
		n /= 10;
	}

	return n % 10;
}

// 기수 정렬 수행
void solve(int k) {
	for (int pos = 0; pos < k; pos++) {
		vector<int> newV[10];	// 0부터 9까지 수를 저장하는 빈 자료형 만들어줌

		// pos번째 숫자를 각 자리수를 저장하는 벡터에 담음
		for (int i = 0; i < v.size(); i++) {
			int n = v[i];
			int digit = getDigit(n, pos);
			newV[digit].push_back(n);
		}

		// 0부터 9까지 순회하며
		// 각 자리수를 기준으로 정렬된 수를 차례대로 담음
		vector<int> sortedV;	
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < newV[i].size(); j++) {
				sortedV.push_back(newV[i][j]);
			}
		}

		// 갱신
		v = sortedV;
	}
}

int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		v.push_back(n);
	}

	solve(7);

	for (int i = 0; i < N; i++) {
		cout << v[i] << " ";
	}
}