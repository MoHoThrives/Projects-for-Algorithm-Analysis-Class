import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class treeNode {
	String chStr;
	int prob;
	treeNode next;
	treeNode left = null;
	treeNode right = null;
	String code;
	
	treeNode(String c, int p){
		chStr = c;
		prob = p;
		next = null;
	}
	
	boolean isLeaf() {
		if (this.right == null && this.left == null)
			return true;
		else
			return false;
	}
	
	static void printNode(treeNode T, String fout) {
		PrintWriter w;

		String result = "";
		result += "(character:";
		result += T.chStr + ",prob:" + T.prob;
		result += ",";
		result+= "code:" + T.code + ",";
		result+= T.next==null ? ("next's character: null") : ("next's character:"+T.next.chStr);
		result += ",";
		result+= T.left==null ? ("left's character:null") : ("left's character:"+T.left.chStr);
		result += ",";
		result+= T.right==null ? ("right's character:null") : ("right's character:"+T.right.chStr);
		result += ")";
		
		result+="\n";
		try {
			w = new PrintWriter(new FileWriter(fout, true));
			w.print(result);
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
