package BOJ.WEEK1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이진 트리를 행과 열 번호가 붙은 격자 모양 틀 속에 그리려 한다.
 * 1. 이진트리에서 같은 레벨에 있는 노드는 같은 행에 위치한다.
 * 2. 한 열에는 한 노드만 존재한다.
 * 3. 임의의 노드의 왼쪽에 있는 노드들은 해당 노드 보다 왼쪽에 위치하고 오른쪽에 있는 노드는 오른쪽에 있어야한다.
 * 4. 노드가 배치된 가장 왼쪽열과 오른쪽 열 사이에 아무 노드도 없이 비어있는 열은 없다.
 * 레벨별 너비 = 가장 오른쪽 노드의 열번호 - 가장 왼쪽의 열번호 + 1
 * 가장 너비가 큰 레벨과 너비를 출력하며 같은 너비라면 더 작은 레벨을 출력한다.
 * 중위순회 + 1이 자기 번호 레벨별로 해당 번호의 최대 - 최소가 가장 큰 너비가 된다
 */
public class BOJ_2250_트리의높이와너비 {
	static BufferedReader br;
	static StringTokenizer st;
	static int numNode;
	static int maxLevel, maxWidth;
	static Node[] nodes;
	// 레벨별 가장 왼쪽의 번호와 오른쪽의 번호가 들어갈 배열
	static int[] left;
	static int[] right;
	static int position;
	// root 노드 식별을 위한 node 선언
	static boolean[] isChild;
	static Node rootNode;

	static class Node {
		int vertex;
		Node left, right;

		public Node(int vertex) {
			this.vertex = vertex;
		}
	}

	public static void getInput() throws IOException {
		numNode = Integer.parseInt(br.readLine().trim());
		nodes = new Node[numNode + 1];
		left = new int[numNode + 1];
		right = new int[numNode + 1];
		isChild = new boolean[numNode + 1];

		for (int idx = 0; idx < numNode; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int parent = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());

			// 좌, 우 자식노드 에서 생성된적이 없다면 생성
			if (nodes[parent] == null) {
				nodes[parent] = new Node(parent);
			}

			Node parentNode = nodes[parent];
			if (left != -1) {
				if (nodes[left] == null) {
					nodes[left] = new Node(left);
				}
				isChild[left] = true;
				parentNode.left = nodes[left];
			}

			if (right != -1) {
				if (nodes[right] == null) {
					nodes[right] = new Node(right);
				}
				isChild[right] = true;
				parentNode.right = nodes[right];
			}
		}

		// rootNode를 찾는다.
		for (int idx = 1; idx <= numNode; idx++) {
			if (!isChild[idx]) {
				rootNode = nodes[idx];
				break;
			}
		}

		// 레벨별 왼쪽 값을 기록하는 배열 초기화
		for (int idx = 1; idx <= numNode; idx++) {
			left[idx] = numNode;
		}
		position = 1;
	}

	public static void inorder(Node node, int level) {
		if (node == null)
			return;
		inorder(node.left, level + 1);
		left[level] = Math.min(left[level], position);
		right[level] = Math.max(right[level], position++);
		inorder(node.right, level + 1);
	}

	public static void findMaxWidth() {
		maxLevel = 1;
		maxWidth = 1;
		for (int level = 2; level <= numNode; level++) {
			int width = right[level] - left[level] + 1;
			if (width > maxWidth) {
				maxWidth = width;
				maxLevel = level;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		// 입력
		getInput();
		// 중위 순회하며 레벨별 좌우 설정
		inorder(rootNode, 1);
		// 최대 너비를 찾는다.
		findMaxWidth();

		System.out.println(maxLevel + " " + maxWidth);
	}
}
