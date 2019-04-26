import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {
	public static void main(String args[]) throws Exception {
		
		try {
			PrintWriter writer4 = new PrintWriter(args[4]);
			PrintWriter writer1 = new PrintWriter(args[1]);
			PrintWriter writer2 = new PrintWriter(args[2]);
			PrintWriter writer3 = new PrintWriter(args[3]);
			writer1.print("");
			writer3.print("");
			writer2.print("");
			writer4.print("");
			writer1.close();
			writer2.close();
			writer3.close();
			writer4.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HuffmanBinaryTree h = new HuffmanBinaryTree();
		linkedList l = h.constructHuffmanLList(args[0], args[1]);
		h.constructHuffmanBinTree(l, args[1]);
		h.constructCode(h.Root, "");
		h.preOrderTraversal(h.Root, args[2]);
		h.inOrderTraversal(h.Root, args[3]);
		h.postOrderTraversal(h.Root, args[4]);
		h.userInterface();
	}
}
