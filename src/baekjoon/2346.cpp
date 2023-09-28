#include <iostream>
#include <fstream>

/*
* 설계 8분 구현 5분
* 링크드리스트
* 처음에는 풍선을 리스트에 담아서
* 풍선을 터뜨릴 때마다 원소를 삭제해줄까
* 아니면 풍선을 배열에 담아서
* 풍선을 터뜨릴 때마다 배열에 있는 원소를 이동시켜줄까
* 생각했는데
* 너무 비효율적이라서
* 뭔가 효율적인 방법이 없을까 생각하다
* 링크드리스트 유레카해버림
* 
* 처음에는 클래스로 링크드리스트를 구현하려고 했으나
* 메모리 제한이 빡세서
* 배열을 이용해서 더블리 링크드 리스트 구현함
* 다음 노드를 가리키고 있는 정보 배열 nextArr과
* 이전 노드를 가리키고 있는 정보 배열 prevArr을 사용
* 
* 풍선을 터뜨릴때마다 노드 가리키고 있는 정보 갱신해줌
*/
using namespace std;

#define MAX_N 1000

int N;
int nextArr[MAX_N + 1];	// 다음 노드 정보 저장
int prevArr[MAX_N + 1];	// 이전 노드 정보 저장
int goArr[MAX_N + 1];	// 어느 방향으로 몇칸 가야하는지 저장

int main() {

	freopen("res/baekjoon/2346.txt", "r", stdin);

	// 입력 받기
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> goArr[i];
	}

	// 링크드리스트 초기화
	for (int i = 1; i <= N; i++) {
		nextArr[i] = i + 1;
		prevArr[i] = i - 1;
	}
	nextArr[N] = 1;
	prevArr[1] = N;

	int cur = 1;	// 현재 풍선
	// 풍선 개수만큼 반복
	for (int i = 0; i < N; i++) {
		cout << cur << " ";

		int next = nextArr[cur];
		int prev = prevArr[cur];

		// 링크 정보 갱신
		nextArr[prev] = next;
		prevArr[next] = prev;

		int go = goArr[cur];
		// 오른쪽으로 이동할 경우
		if (go > 0) {
			for (int j = 0; j < go; j++) cur = nextArr[cur];
		}
		// 왼쪽으로 이동할 경우
		else {
			go *= -1;
			for (int j = 0; j < go; j++) cur = prevArr[cur];
		}
	}
}