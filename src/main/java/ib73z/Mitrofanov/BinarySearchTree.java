package ib73z.Mitrofanov;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable> {

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
    public Node findNode(T key) {
        Node node = root;
        while (node.key != key) {
            if (key.compareTo(node.key) == -1) {
                node = node.left;
            } else {
                node = node.right;
            }
            if (node == null) return null;
        }
        return node;
    }

    //включение нового элемента с заданным ключом
    public void addNode(T key) {
        Node newNode = new Node(key);
        if (root == null) root = newNode;
        else {
            Node node = root;
            Node parent;
            while (true) {
                newNode.height++;
                parent = node;
                if (key.compareTo(node.key) == -1) {
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
    public boolean removeNode(T key) {
        Node node = root;
        Node parent = root;

        boolean isLeft = true;

        while (!node.key.equals(key)) {
            parent = node;
            if (key.compareTo(node.key) == -1) {
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
    public Stack postOrderTraverseTree() {
        Stack<Node> postOrder = new Stack<>();
        Node node = root;
        Stack<Node> stack = new Stack<>();
        while (true) {
            while (node != null) {
                stack.push(node);
                stack.push(node);
                node = node.left;
            }
            if (stack.empty()) return postOrder;
            node = stack.pop();
            if (!stack.empty() && stack.peek() == node) node = node.right;
            else {
                postOrder.push(node);
                node = null;
            }

        }
    }

    //вывод структуры дерева на экран
    public String print() {
        if (isEmpty()) {
            return "Дерево пустое.";
        }
        StringBuilder sb = new StringBuilder();
        Stack<Node> postOrder = postOrderTraverseTree();

        sb.append(postOrder.pop());
        sb.append("\n");

        while (!postOrder.isEmpty()) {
            for (int i = 0; i < postOrder.peek().height-1; i++)
                sb.append("   ");
            sb.append("└──");
            sb.append(postOrder.pop());
            sb.append("\n");
        }

        return sb.toString();
    }

    //объединение двух поддеревьев (рекурсивная форма)
    public Node push(Node root, Node head) {
        // insert the given node at the front of the DDL
        root.right = head;

        // update the left pointer of the existing head node of the DDL
        // to point to the BST node
        if (head != null) {
            head.left = root;
        }

        // update the head pointer of DDL
        head = root;
        return head;
    }

    public Node convertBSTtoDLL(Node root, Node head) {
        // Base case
        if (root == null) {
            return head;
        }

        // recursively convert the right subtree a
        head = convertBSTtoDLL(root.right, head);

        // push current node at the front of the doubly linked list
        head = push(root, head);

        // recursively convert the left subtree
        head = convertBSTtoDLL(root.left, head);

        return head;
    }

    public Node mergeDDLs(Node a, Node b) {
        // if the first list is empty, return the second list
        if (a == null) {
            return b;
        }

        // if the second list is empty, return the first list
        if (b == null) {
            return a;
        }

        // if head node of the first list is smaller
        if (a.key.compareTo(b.key) == -1) {
            a.right = mergeDDLs(a.right, b);
            a.right.left = a;
            return a;
        }

        // if head node of the second list is smaller
        else {
            b.right = mergeDDLs(a, b.right);
            b.right.left = b;
            return b;
        }
    }

    public Node merge(Node a, Node b) {
        Node first = null;
        first = convertBSTtoDLL(a, first);

        Node second = null;
        second = convertBSTtoDLL(b, second);

        return mergeDDLs(first, second);
    }

}

