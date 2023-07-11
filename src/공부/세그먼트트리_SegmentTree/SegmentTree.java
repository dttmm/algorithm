package 공부.세그먼트트리_SegmentTree;

// 세그먼트 트리 원리 이해하고
// 나만의 세그먼트 트리 직접 구현하기
public class SegmentTree {
    static int N;       // 원본 배열의 크기
    static int[] arr;   // 원본 배열
    static int[] tree;  // 트리 배열

    // 세그먼트 트리 단계
    // 1. 트리 초기화 하기 -> 크기 정하기, 부모 노드값 채우기
    // 2. 쿼리값 구하기 -> 인덱스를 트리에 맞게 변경, 노드 선택, 다음 부모 구하기, 종료 조건 설정


    // 1. 트리 초기화 하기
    static void initTree() {
        // 1.1 트리 크기 정하기
        // 2^k >= N을 만족하는 k의 최솟값(트리 높이)을 구한 후 2^k * 2를 트리 배열의 크기로 정의해야 됨
        // ex) 원본 배열의 크기 N이 8인 경우 -> k=3 이므로 16만큼 트리배열을 만들어야됨
        // 원본 배열의 크기 N이 5인 경우에도 -> k=3 이므로 16만큼 트리배열을 만들어야됨
        int h = (int) Math.ceil(Math.log(N) / Math.log(2)); // 트리 높이
        int treeCount = (int) Math.pow(2, h + 1);
        tree = new int[treeCount];

        // ++ 최소값 트리 만들 경우
        // 모든 노드 MAX_VALUE로 초기화
        for (int i = 1; i < treeCount; i++) {
            tree[i] = Integer.MAX_VALUE;
        }

        // 1.2 부모 노드값 채우기
        // 원본 배열을 트리의 리프노드에 채워 넣음
        // 리프노드의 시작 인덱스는 2^k임
        int startIndex = treeCount / 2;
        // 리프 노드 초기화
        for (int i = 0; i < N; i++) {
            tree[startIndex + i] = arr[i];
        }

        // 부모노드: index / 2
        // 왼쪽 자식: index * 2
        // 오른쪽 자식: index * 2 + 1

        // 구간 합 구하기
        // 트리의 끝에서 부터
        // 자신의 부모 노드에 자신의 값을 더해줌
        // ex) 원본 배열 {5, 8, 4, 3, 7, 2, 1, 6}인 경우
        // 트리는 { , 36, 20, 16, 13, 7, 9, 7, 5, 8, 4, 3, 7, 2, 1, 6}이 됨
        for (int i = tree.length - 1; i > 1; i--) {
            int parentIndex = i / 2;
            tree[parentIndex] += tree[i];
        }

        // 최대값 트리도 프로세스 똑같음
        // 트리의 끝에서 부터 자신의 부모 노드와 값을 비교해서 최대값을 넣어주면 됨
        // ex) 원본 데이터가 {5, 8, 4, 3, 7, 2, 1, 6}인 경우
        // 트리는 { , 8, 8, 7, 8, 4, 7, 6, 5, 8, 4, 3, 7, 2, 1, 6}이 됨

        // ++ 최소값 트리 초기화하는 경우
        for (int i = treeCount - 1; i > 1; i -= 2) {    // i를 2씩 줄여서 불필요한 탐색 최소화
            int n1 = tree[i];       // 현재 노드와
            int n2 = tree[i - 1];   // 형제 노드 중

            int parentIndex = i / 2;
            tree[parentIndex] = Math.min(n1, n2);   // 둘 중 최소값으로 부모 설정
        }
    }


    // 2. 쿼리값 구하기
    // 2.1 인덱스를 트리에 맞게 변경
    // 원본 배열에서 특정 인덱스 사이의 구간합을 구해야 한다면
    // 원본 배열의 인덱스를 트리 배열의 인덱스로 변환해야됨
    // 트리 인덱스 = 원본 배열 인덱스 + 2^k
    // ex) 원본 배열 {5, 8, 4, 3, 7, 2, 1, 6}인 경우
    // 트리는 { , 36, 20, 16, 13, 7, 9, 7, 5, 8, 4, 3, 7, 2, 1, 6}이 되고
    // 2 ~ 6 구간의 구간합을 구해야 한다면
    // 원본 배열의 인덱스는 1 ~ 5이므로
    // 1 + 2^3 ~ 5 + 2^3 -> 트리에서 9 ~ 13의 인덱스 구간합을 구해야됨

    // 구간 -> 배열 인덱스(구간 - 1) -> 트리 인덱스(배열 인덱스 + 리프노드 시작인덱스)로 변환
    // 트리 시작인덱스 = 구간합 시작구간 - 1 + tree.length / 2
    // 트리 끝인덱스 = 구간합 끝구간 - 1 + tree.length / 2

    // 원본 배열의 인덱스를 트리 배열의 인덱스로 변환한 후라고 가정
    static int sum(int start, int end, int total) {
        // 2.4 종료 조건 설정
        // start > end면 그만
        if (start > end) return total;

        // 2.2 노드 선택
        // start % 2 == 1이면 해당 노드 선택
        // end % 2 == 0이면 해당 노드 선택
        if (start % 2 == 1) total += tree[start];
        if (end % 2 == 0) total += tree[end];

        // 2.3 다음 부모 구하기
        // start = (start + 1) / 2
        // end = (end - 1) / 2
        start = (start + 1) / 2;
        end = (end - 1) / 2;

        return sum(start, end, total);
    }

    // ++ 최소값 쿼리 구하는 경우
    static int getMin(int start, int end, int min) {
        if (start > end) return min;

        if (start % 2 == 1) min = Math.min(min, tree[start]);
        if (end % 2 == 0) min = Math.min(min, tree[end]);

        start = (start + 1) / 2;
        end = (end - 1) / 2;

        return getMin(start, end, min);
    }

    // 3. 업데이트 하기
    // 원본 배열의 값을 바꾼 경우

    // 바꿀 구간 -> 배열 인덱스(바꿀 구간 - 1) -> 트리 인덱스(배열 인덱스 + 리프노드 시작인덱스)로 변환
    // index = 바꿀 구간 - 1 + tree.length / 2;

    // 바꿀 값과 기존 값의 차이
    // diff = 바꿀 값 - tree[index];

    // 원본 배열의 인덱스를 트리 배열의 인덱스로 변환한 후라고 가정
    static void update(int index, int diff) {

        if (index < 1) return;

        // 부모노드로 올라가면서 차이를 업데이트 해주면 됨
        tree[index] += diff;
        update(index / 2, diff);

        // 최대 최소의 경우 형제 노드와 비교해서 최대 최소값 부모노드에 업데이트해주면 됨
    }
}
