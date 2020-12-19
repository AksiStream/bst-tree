package ib73z.Mitrofanov;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>> {

    Node<T> root;

    //опрос размера дерева
    public int getSize() {
        Node<T> node = root;
        int count = 0;
        if (node == null) return count;
        Stack<Node<T>> sizeStack = new Stack<>();
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
        Queue<Node<T>> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node<T> node = q.poll();
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
    public Node<T> findNode(T key) {
        if (isEmpty()) return null;
        Node<T> node = root;
        while (node.key != key) {
            if (key.compareTo(node.key) < 0) {
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
        Node<T> newNode = new Node<>(key);
        if (root == null) root = newNode;
        else {
            Node<T> node = root;
            Node<T> parent;
            while (true) {
                parent = node;
                if (key.compareTo(node.key) < 0) {
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
    public boolean removeNode(T value) {
        Node<T> current = root;
        Node<T> parent = root;
        boolean isLeftChild = false;
        while (current.key != value) {
            parent = current;
            if (value.compareTo(current.key) < 0) {
                current = current.left;
                isLeftChild = true;
            } else {
                current = current.right;
                isLeftChild = false;
            }
            if (current == null) {
                return false;
            }
        }

        //случай удаления листа
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        //удаление ноды-родителя
        //правый потомок
        else if (current.left == null) {
            // if root node is to be deleted
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        }
        //левый потомок
        else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        }
        //оба потомка
        else {
            Node<T> successor = findSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
        return true;
    }

    //метод для поиска следующего по порядку элемента
    private Node<T> findSuccessor(Node<T> node) {
        Node<T> successor = node;
        Node<T> successorParent = node;
        Node<T> current = node.right;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        if (successor != node.right) {
            successorParent.left = successor.right;
            successor.right = node.right;
        }
        return successor;
    }

    //обход дерева по схеме LRN (обратный обход)
    public Stack<Node<T>> postOrderTraverseTree() {
        Stack<Node<T>> postOrder = new Stack<>();
        Node<T> node = root;
        Stack<Node<T>> stack = new Stack<>();
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
    public void print() {
        TreePrinter<T> printer = new TreePrinter<>();
        printer.print(root);
    }

    //объединение двух поддеревьев (рекурсивная форма)



}

