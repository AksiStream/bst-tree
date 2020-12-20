package ib73z.Mitrofanov;

class Node<T extends Comparable<T>> {
    T key;
    Node<T> left, right, parent;

    Node(T key) {
        this.key = key;
    }

    public String toString() {
        return key.toString();
    }

}
