
public class linkedList {
	treeNode listHead;
	linkedList() {
		listHead = new treeNode("dummy", 0);
	}
	
	treeNode findSpot(treeNode newNode) {
		treeNode current = listHead;
		while(current.next != null && current.next.prob <= newNode.prob) {
			current = current.next;
		}
		return current;
	}
	
	void insertTN(treeNode newNode) {
		treeNode s = findSpot(newNode);
		newNode.next = s.next;
		s.next = newNode;
	}
	
	String printList() {
		treeNode current = listHead;

		String result = "";
		result += "listHead --> ";
		while(current.next != null) {
			result += "(character:" + current.chStr + ",prob:" + current.prob + ",next's prob:" + current.next.chStr + ") --> ";
			current = current.next;
		}
		result += "(character:" + current.chStr + ",prob:" + current.prob + ",next's prob:" + "null" + ")";
		
		return result;
	}
	
	
	
}
