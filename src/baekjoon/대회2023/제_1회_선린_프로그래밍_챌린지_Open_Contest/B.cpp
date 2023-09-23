#include <iostream>
#include <fstream>

/*
* 설계 1분 구현 4분
* 기본 구현 문제
* 백분율에 따른 각 등급을 p배열에 미리 만들어 놓음
*/

using namespace std;

int N;
int K;
int p[101];

void setP() {
	for (int i = 0; i <= 4; i++) p[i] = 1;
	for (int i = 5; i <= 11; i++) p[i] = 2;
	for (int i = 12; i <= 23; i++) p[i] = 3;
	for (int i = 24; i <= 40; i++) p[i] = 4;
	for (int i = 41; i <= 60; i++) p[i] = 5;
	for (int i = 61; i <= 77; i++) p[i] = 6;
	for (int i = 78; i <= 89; i++) p[i] = 7;
	for (int i = 90; i <= 96; i++) p[i] = 8;
	for (int i = 97; i <= 100; i++) p[i] = 9;
}

int main() {

	freopen("src/baekjoon/대회2023/제_1회_선린_프로그래밍_챌린지_Open_Contest/B.txt", "r", stdin);
	cin >> N >> K;

	setP();

	for (int k = 0; k < K; k++) {
		int n;
		cin >> n;

		int ret = p[(n * 100) / N];
		cout << ret << " ";
	}
}