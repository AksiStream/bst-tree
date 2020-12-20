package ib73z.Mitrofanov;

public class Iterator<T extends Comparable<T>> {

    private final Node<T> root;
    private Node<T> current;

    public Iterator(BinarySearchTree<T> bst) {
        this.root = bst.root;
    }

    public void findMinimal() {
        current = root;
        while (current.left != null) current = current.left;
    }

    public T get() {
        return current.key;
    }

    public void root() {
        current = root;
    }

    public Node<T> next() {
        Node<T> start = current;
        if (current.right != null) {
            current = current.right;
            while (current.left != null) current = current.left;
            return current;
        }

        while (true) {
            if (current.parent == null) {
                current = start;
                return null;
            }
            if (current.parent.left == current) {
                current = current.parent;
                return current;
            }
            current = current.parent;
        }
    }

    public Node<T> prev() {
        Node<T> start = current;
        if (current.left != null) {
            current = current.left;
            while (current.right != null) current = current.right;
            return current;
        }

        while (true) {
            if (current.parent == null) {
                current = start;
                return null;
            }
            if (current.parent.right == current) {
                current = current.parent;
                return current;
            }
            current = current.parent;
        }
    }

    public boolean hasNext() {
        Node<T> start = current;
        if (next() != null) {
            current = start;
            return true;
        } else {
            current = start;
            return false;
        }
    }

    public boolean hasPrev() {
        Node<T> start = current;
        if (prev() != null) {
            current = start;
            return true;
        } else {
            current = start;
            return false;
        }
    }

}
