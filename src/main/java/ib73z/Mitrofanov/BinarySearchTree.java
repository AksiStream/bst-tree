package ib73z.Mitrofanov;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree {

    Node root;

    //опрос размера дерева
    public int getSize() {
        Node node = root;
        int count = 0;
        if (node == null) return count;
        Stack<Node> sizeStack = new Stack();
        sizeStack.push(node);
        while (!sizeStack.isEmpty()) {
            node = sizeStack.pop();
            while (node != null) {
                count++;
                if (node.right != null) {
                    sizeStack.push(node.right);
                }
                node = node.left;
            }
        }
        return count;
    }

    //очистка дерева
    public void clearAllTree() {
        if (root == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.left != null) q.add(node.left);
            if (node.right != null) q.add(node.right);
        }
        root = null;
    }

    //проверка дерева на пустоту
    public boolean isEmpty() {
        return root == null;
    }

    //поиск элемента с заданным ключом
    public Node findNode(int key) {
        Node node = root;
        while (node.key != key) {
            if (key < node.key) {
                node = node.left;
            } else {
                node = node.right;
            }
            if (node == null) return null;
        }
        return node;
    }

    //включение нового элемента с заданным ключом
    public <T> void addNode(int key, T data) {
        Node newNode = new Node(key, data);
        if (root == null) root = newNode;
        else {
            Node node = root;
            Node parent;
            while (true) {
                parent = node;
                if (key < node.key) {
                    node = node.left;
                    if (node == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    node = node.right;
                    if (node == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }

    //удаление элемента с заданным ключом
    public boolean removeNode(int key) {
        Node node = root;
        Node parent = root;

        boolean isLeft = true;

        while (node.key != key) {
            parent = node;
            if (key < node.key) {
                isLeft = true;
                node = node.left;
            } else {
                isLeft = false;
                node = node.right;
            }
            if (node == null) return false;
        }
        if (node.left == null && node.right == null) {
            if (node == root) {
                root = null;
            } else if (isLeft) {
                parent.left = null;
            } else parent.right = null;
        } else if (node.right == null) {
            if (node == root) root = node.left;
            else if (isLeft) parent.left = node.left;
            else parent.right = node.left;
        } else if (node.left == null) {
            if (node == root) root = node.right;
            else if (isLeft) parent.left = node.right;
            else parent.right = null;
        } else {
            Node replacement = getReplacementNode(node);
            if (node == root) root = replacement;
            else if (isLeft) parent.left = replacement;
            else parent.right = replacement;
            replacement.left = node.left;
        }

        return true;
    }
    public Node getReplacementNode(Node replacedNode) {
        Node replacementParent = replacedNode;
        Node replacement = replacedNode;
        Node node = replacedNode.right;
        while (node != null) {
            replacementParent = replacement;
            replacement = node;
            node = node.left;
        }
        if (replacement != replacedNode.right) {
            replacementParent.left = replacement.right;
            replacement.right = replacedNode.right;
        }
        return replacement;
    }

    //обход дерева по схеме LRN (обратный обход)
    public void postOrderTraverseTree() {
        Node node = root;
        Stack<Node> stack = new Stack<>();
        while (true) {
            while (node != null) {
                stack.push(node);
                stack.push(node);
                node = node.left;
            }
            if (stack.empty()) return;
            node = stack.pop();
            if (!stack.empty() && stack.peek() == node) node = node.right;
            else {
                System.out.print(" " + node.key);
                node = null;
            }
        }
    }

    //вывод структуры дерева на экран


}

