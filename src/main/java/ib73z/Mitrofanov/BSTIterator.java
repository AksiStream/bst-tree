package ib73z.Mitrofanov;

import java.util.Stack;

public class BSTIterator {
    private Stack<Node> stack;

    public BSTIterator(Node root) {
        stack = new Stack<Node>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    public String currentData(){
        return stack.peek().toString();
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public int next() {
        Node node = stack.pop();
        int result = node.key;
        if (node.right != null) {
            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        return result;
    }

}