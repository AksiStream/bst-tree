package ib73z.Mitrofanov;

class Node<T> {
    int key;
    T data;
    Node left, right;

    Node(int key, T data) {
        this.key = key;
        this.data = data;
    }

    public String toString() { return "В ключе " + key + " записано " + data + ".";
    }
}
