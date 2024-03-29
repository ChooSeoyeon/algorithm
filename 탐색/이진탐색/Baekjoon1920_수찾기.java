package 탐색.이진탐색;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
[설명]
- N개의 정수 주어졌을 때, 이 안에 X라는 정수 존재하는지 알아내는 프로그램
- X가 여러 개(M개)임
[접근] 이진탐색
- N과 M의 최대 범위가 10만(10^5)인데 N^2이면 10^10으로 1억을 넘어가서 제한 시간인 1초 내에 풀 수 없음
- O(logN)인 이진탐색을 사용해서 찾으면 -> O(MlogN)이라서 시간 안에 풀 수 있음
[메모]
[오답]
- 정렬 까먹음... 바보
- 입력 받을 때 m개 받아야 하는데 실수로 n개 받음...
- 정답도 m개인데 실수로 n개로 함... 바보...
*/
public class Baekjoon1920_수찾기 {
	private static int[] numbers; // size = n
	private static int[] targets; // size = m

	private static int[] solution(int m) {
		int[] answer = new int[m];
		Arrays.sort(numbers);
		int left = 0;
		int right = numbers.length - 1;

		for (int i = 0; i < m; i++) {
			int result = binarySearchWithRecursive(left, right, targets[i]);
			// int result = binarySearchWithLoop(left, right, targets[i]);
			answer[i] = result == -1 ? 0 : 1;
		}

		return answer;
	}

	private static int binarySearchWithLoop(int left, int right, int target) {
		while (left <= right) {
			int mid = (left + right) / 2;
			if (target < numbers[mid]) {
				right = mid - 1;
			} else if (target > numbers[mid]) {
				left = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	private static int binarySearchWithRecursive(int left, int right, int target) {
		if (left > right) {
			return -1;
		}

		int mid = (left + right) / 2;
		if (target < numbers[mid]) {
			return binarySearchWithRecursive(left, mid - 1, target);
		} else if (target > numbers[mid]) {
			return binarySearchWithRecursive(mid + 1, right, target);
		} else {
			return mid;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new java.io.FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		numbers = new int[n];
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(input[i]);
		}

		int m = Integer.parseInt(br.readLine());
		targets = new int[m];
		input = br.readLine().split(" ");
		for (int i = 0; i < m; i++) {
			targets[i] = Integer.parseInt(input[i]);
		}

		int[] answer = solution(m);
		for (int ans : answer) {
			System.out.println(ans);
		}

		br.close();
	}
}