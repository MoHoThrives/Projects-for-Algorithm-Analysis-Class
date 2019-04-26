import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class HuffmanBinaryTree {
	treeNode Root;
	String charCode[] = new String[256];

	HuffmanBinaryTree() {
		Root = null;
	}

	linkedList constructHuffmanLList(String fin, String fout) {
		linkedList h = new linkedList();
		try {

			Scanner in = new Scanner(new FileReader(fin));
			PrintWriter out = new PrintWriter(new FileWriter(fout, true));
			while (in.hasNext()) {
				String c = "";
				int p;
				treeNode n;
				c += in.next();
				if (c.equals("@")) {
					c = Character.toString((char) 32);
				}
				p = in.nextInt();
				n = new treeNode(c, p);
				h.insertTN(n);

			}
			out.print(h.printList());
			in.close();
			out.close();

		} catch (Exception e) {
			System.out.print("error in HBT construct");
			e.printStackTrace();
		}
		return h;

	}

	void constructCode(treeNode r, String c) {
		if (r.isLeaf()) {
			r.code = c;
			int index = (int) r.chStr.charAt(0);
			charCode[index] = c;
		} else {
			constructCode(r.left, c + "0");
			constructCode(r.right, c + "1");
		}
	}

	void Encode(String fin, String fout) {
		try {
			Scanner in = new Scanner(new FileReader(fin));
			PrintWriter out = new PrintWriter(new FileWriter(fout, true));
			while (in.hasNext()) {
				String textLine = in.nextLine();
				int i = 0;
				while (i < textLine.length()) {
					char charIn = textLine.charAt(i);
					int index = charIn;
					out.write(charCode[index]);
					i++;
				}
			}
			in.close();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void Decode(String fin, String fout) {
		try {
			String theBits = new Scanner(new FileReader(fin)).next();
			PrintWriter out = new PrintWriter(new FileWriter(fout, true));
			treeNode spot = Root;
				for (int i = 0; i < theBits.length(); i++) {
					if (spot.isLeaf()) {
						out.write(spot.chStr);
						spot = Root;
					}
					char oneBit = theBits.charAt(i);
					if (oneBit == '0')
						spot = spot.left;
					else if (oneBit == '1')
						spot = spot.right;
					else
						out.write("This file contains invalid character");
				}
			if ( !spot.isLeaf())
				out.write("Compressed file is corrupted");
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void constructHuffmanBinTree(linkedList l, String fout) {

		try {
			PrintWriter out = new PrintWriter(new FileWriter(fout, true));
			treeNode t = null;
			while ((l.listHead.next.next != null)) {

				int p = l.listHead.next.prob + l.listHead.next.next.prob;
				String c = l.listHead.next.chStr + l.listHead.next.next.chStr;
				t = new treeNode(c, p);
				t.left = l.listHead.next;
				t.right = l.listHead.next.next;
				l.insertTN(t);
				l.listHead.next = l.listHead.next.next.next;
			} 
			out.write("\n");
			out.write("\n");
			out.write(l.printList());
			Root = t;
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void userInterface() throws FileNotFoundException {
		String nameOrg;
		String nameCompress;
		String nameDeCompress;
		char yesNo;
		Scanner r = new Scanner(System.in);
		while (true) {
			System.out.println("Would you like to compress a file? (Y/N)");
			yesNo = r.next().charAt(0);
			if (yesNo == 'N') {
				r.close();
				return;
			}
			System.out.print("What's the name of the file?");
			nameOrg = r.next();
			nameCompress = nameOrg + "_Compressed.txt";
			nameDeCompress = nameOrg + "_DeCompress.txt";
			nameOrg += ".txt";
			PrintWriter writer1 = new PrintWriter(nameCompress);
			PrintWriter writer2 = new PrintWriter(nameDeCompress);
			writer1.print("");
			writer1.close();
			writer2.print("");
			writer2.close();
			Encode(nameOrg, nameCompress);
			Decode(nameCompress, nameDeCompress);
		}

	}
	


	void preOrderTraversal(treeNode t, String fout) {
		try {

			if (t == null)
				return;
			treeNode.printNode(t, fout);

			preOrderTraversal(t.left, fout);
			preOrderTraversal(t.right, fout);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void inOrderTraversal(treeNode t, String fout) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(fout, true));
			if (t.left != null) {
				inOrderTraversal(t.left, fout);
			}
			treeNode.printNode(t, fout);
			if (t.right != null)
				inOrderTraversal(t.right, fout);
			out.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void postOrderTraversal(treeNode t, String fout) {
		try {

			if (t != null) {
				postOrderTraversal(t.left, fout);
				postOrderTraversal(t.right, fout);
				treeNode.printNode(t, fout);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
