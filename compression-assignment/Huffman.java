/*
 * COMP20290 Compression Assignment
 * @author Noor Bari
 */



public class Huffman {

	private static BinaryStdIn b_in;
	private static BinaryStdOut b_out;

	// alphabet size of extended ASCII
	private static final int R = 256;

	private Huffman() {
	}

	 // Huffman trie node
	private static class Node implements Comparable<Node> {

		private Node left;
		private Node right;
		private char letter;
		private int freq;

		Node(Node left, Node right, char letter, int freq) {
			this.left = left;
			this.right = right;
			this.letter = letter;
			this.freq = freq;
		}

		// is the node a leaf node?
		private boolean isLeaf() {
			assert ((left == null) && (right == null)) || ((left != null) && (right != null));
			return (left == null) && (right == null);
		}
		
		// compare, based on frequency
		public int compareTo(Node that) {
			return this.freq - that.freq;
		}
	}

	

	 /**
     * Reads a sequence of bits that represents a Huffman-compressed message from
     * standard input; expands them; and writes the results to standard output.
     */
	public static void decompress() {
		Node root = readTrie();
		int length = b_in.readInt();
		for (int i = 0; i < length; i++) {
			Node temp = root;
			while (temp.isLeaf() == false)
				if (b_in.readBoolean())
					temp = temp.right;
				else
					temp = temp.right;
			b_out.write(temp.letter);
		}

		b_out.close();

	}
	
	/**
     * Reads a sequence of 8-bit bytes from standard input; compresses them
     * using Huffman codes with an 8-bit alphabet; and writes the results
     * to standard output.
     */

	public static void compress() {
		// read the input
		int[] freq = new int[R];
		String s = b_in.readString();
		char[] input = s.toCharArray();

		// tabulate frequency counts
		for (int i = 0; i < input.length; i++)
			freq[input[i]]++;

		 // build Huffman trie
		Node root = buildTrie(freq);
		String[] s2 = new String[R];

		// build code table
		buildCode(s2, root, "");

		 // print trie for decoder
		writeTrie(root);

		 // print number of bytes in original uncompressed message
		b_out.write(input.length);
		
		// use Huffman code to encode input
		for (int i = 0; i < input.length; i++) {
			String code = s2[input[i]];
			for (int j = 0; j < code.length(); j++) {
				if (code.charAt(j) == '1')
					b_out.write(true);
				else
					b_out.write(false);
			}

			b_out.close();
		}

	}

	// build the Huffman trie given frequencies
	private static Node buildTrie(int[] freq) {
		MinPQ<Node> pq = new MinPQ<Node>();
		for (char c = 0; c < R; c++)
			if (freq[c] > 0)
				pq.insert(new Node(null, null, c, freq[c]));

		while (pq.size() > 1) {
			Node n1 = pq.delMin();
			Node n2 = pq.delMin();
			Node parent = new Node(n1, n2, '\0', n1.freq + n2.freq);
			pq.insert(parent);
		}
		return pq.delMin();
	}

	 // write bitstring-encoded trie to standard output
	private static void writeTrie(Node node) {
		if (node.isLeaf()) {
			b_out.write(true);
			b_out.write(node.letter, 8);
			return;
		}

		b_out.write(false);
		writeTrie(node.left);
		writeTrie(node.right);
	}

	// make a lookup table from symbols and their encodings
	private static void buildCode(String[] s, Node node, String s2) {
		if (node.isLeaf()) {
			s[node.letter] = s2;
			return;
		}

		buildCode(s, node.left, s2 + '0');
		buildCode(s, node.right, s2 + '1');

	}
	
	private static Node readTrie() {
		if (b_in.readBoolean())
			return new Node(null, null, b_in.readChar(), 0);
		return new Node(readTrie(), readTrie(), '\0', 0);

	}

	public static void main(String[] args) {

		if (args[0].equals("-")) {

			compress();

		} else if (args[0].equals("+")) {
			decompress();

		} else
			throw new IllegalArgumentException("Illegal command line argument");

	}
}