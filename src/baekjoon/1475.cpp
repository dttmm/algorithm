#include <fstream>
#include <iostream>

/*
*  설계 2분 구현 3분
* 구현
* N에서 각 숫자가 몇 개씩 있는지 arr배열에 카운트 함
* 단, 9는 6으로 치환함
* 
* 그리고 가장 많은 숫자 개수가 바로 정답
* 단, 6의 경우는 한 세트에 2개씩 있으므로
* 6의 개수를 반으로 나눠주면 6을 만족할 수 있는 세트 수를 구할 수 있음
* 단, 6의 개수가 홀수 일 때는 반으로 나눌 때 ceil을 해야 하므로
* 6의 개수에 1을 더하여 반으로
*/

using namespace std;

int N;
int arr[9];

// 각 숫자가 몇 개씩 있는지 카운트
void setArr() {
	int x = N;
	while (x != 0) {
		int n = x % 10;

		if (n == 9) n = 6;	// 9는 6으로 치환
		arr[n]++;

		x /= 10;
	}
}

// 필요한 세트 개수 구하기
int solve() {
	arr[6] = (arr[6] + 1) / 2;	// ceil효과

	int maxVal = 0;
	for (int i = 0; i < 9; i++) {
		maxVal = arr[i] > maxVal ? arr[i] : maxVal;
	}

	return maxVal;
}

int main() {

	freopen("res/baekjoon/1475.txt", "r", stdin);

	// 입력
	cin >> N;

	setArr();

	int ans = solve();

	cout << ans;
}