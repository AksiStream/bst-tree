package ib73z.Mitrofanov;

class Node<T extends Comparable> {
    T key;
    int height = 0;
    Node left, right;

    Node(T key) {
        this.key = key;
    }

    public String toString() {
        return key.toString();
    }
}
