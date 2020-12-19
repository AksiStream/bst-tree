package ib73z.Mitrofanov;

class Node<T> {
    T key;
    Node<T> left, right;

    Node(T key) {
        this.key = key;
    }

    public String toString() {
        return key.toString();
    }
}
