#include <iostream>
#include <string>

using namespace std;

#define MAX_N 100

int N;
char arr[MAX_N];

int solve() {
	// 몇 글자 고를지 길이 선택
	for (int k = 1; k <= N; k++) {
		bool flag = false;

		// 시작 지점 선택
		for (int i = 0; i <= N - k; i++) {
			string s1 = "";

			// 시작 지점에서부터 k개 고르기
			for (int x = i; x < i + k; x++) {
				s1 += arr[x];
			}

			// 다음 시작 지점 선택
			for (int j = i + 1; j <= N - k; j++) {
				string s2 = "";

				// 다음 지점에서부터 k개 고르기
				for (int x = j; x < j + k; x++) {
					s2 += arr[x];
				}

				// 같은 문자 등장한 경우
				if (s1 == s2) {
					flag = true;
					break;
				}
			}
			if (flag) break;
		}

		if (flag) continue;
		// 해당 길이에서 중복된 문자가 등장하지 않는 경우
		else return k;
	}
}

int main() {

	// 입력
	cin >> N >> arr;

	int ret = solve();

	cout << ret;
}