public class AVL_Tree implements MySet {
    private class Node {
        int key;
        int height;
        Node left;
        Node right;
        Node parent;
        public Node(int key, Node parent) {
            this.key = key;
            this.changeParent(parent);
            height = 1;
        }
        void changeParent(Node parent) {
            if (parent == null) {
                root = this;
            }
            this.parent = parent;
        }
    }

    Node root;
    int size;

    public AVL_Tree() {
        size = 0;
        lvl = 0;
    }

    int getHeight(Node v) {
        if (v == null) {
            return 0;
        }
        return (v.left != null ? v.left.height : 0) + (v.right != null ? v.right.height : 0) + 1;
    }

    void rotateLeft(Node p, Node v) {
        Node pr = p.parent;
        Node b = v.left;
        p.right = b;
        if (b != null) {
            b.changeParent(p);
        }
        v.left = p;
        p.changeParent(v);
        v.changeParent(pr);
        p.height = getHeight(p);
        v.height = getHeight(v);
        if (pr != null) {
            if (pr.left == p) {
                pr.left = v;
            } else {
                pr.right = v;
            }
            pr.height = getHeight(pr);
        }
    }

    void rotateRight(Node p, Node v) {
        Node pr = v.parent;
        Node b = p.right;
        v.left = b;
        if (b != null) {
            b.changeParent(v);
        }
        p.right = v;
        v.changeParent(p);
        p.changeParent(pr);
        p.height = getHeight(p);
        v.height = getHeight(v);
        if (pr != null) {
            if (pr.left == v) {
                pr.left = p;
            } else {
                pr.right = p;
            }
            pr.height = getHeight(pr);
        }
    }

    void balance(Node p) {
        p.height = getHeight(p);
        if (getHeight(p.right) - getHeight(p.left) == 2) {
            if (getHeight(p.right.left) > getHeight(p.right.right)) {
                rotateRight(p.right.left, p.right);
            }
            rotateLeft(p, p.right);
        } else if (getHeight(p.left) - getHeight(p.right) == 2) {
            if (getHeight(p.left.right) > getHeight(p.left.left)) {
                rotateLeft(p.left.right, p.left);
            }
            rotateRight(p, p.left);
        }
    }

    void add(Node v, int x) {
        if (v.key > x) {
            if (v.left == null) {
                v.left = new Node(x, v);
                size++;
            } else {
                add(v.left, x);
            }
        } else if (v.key < x) {
            if (v.right == null) {
                v.right = new Node(x, v);
                size++;
            } else {
                add(v.right, x);
            }
        }
        balance(v);
    }

    void remove(Node v, int x) {
        if (v.key > x && v.left != null) {
            remove(v.left, x);
        } else if (v.key < x && v.right != null) {
            remove(v.right, x);
        }
        if (v.key != x) {
            balance(v);
            return;
        }

        if (v == root && size() == 1) {
            root = null;
            size--;
            return;
        }
        if (v.left == null) {
            if (v.parent != null && v.parent.left == v) {
                v.parent.left = v.right;
            } else if (v.parent != null && v.parent.right == v) {
                v.parent.right = v.right;
            }
            if (v.right != null) {
                v.right.changeParent(v.parent);
            }
        } else {
            Node m = v.left;
            int mx = m.key;
            while (m.right != null) {
                mx = m.right.key;
                m = m.right;
            }
            v.key = mx;

            if (m != v.left) {
                m.parent.right = m.left;
            } else {
                v.left = m.left;
            }
            if (m.left != null) {
                m.left.changeParent(m.parent);
            }
        }
        size--;
        balance(v);
    }

    boolean find(Node v, int x) {
        if (v == null) {
            return false;
        }
        if (v.key < x) {
            return find(v.right, x);
        } else if (v.key > x) {
            return find(v.left, x);
        }
        return true;
    }

    int lvl;
    void printTree(Node v) {
        if (v == null) {
            return;
        }

        lvl++;
        System.out.println(v.key + " lvl=" + lvl);
        int tempLevel = lvl;
        printTree(v.left);
        lvl = tempLevel;
        printTree(v.right);
        lvl = tempLevel;
    }

    @Override
    public void printTree() {
        printTree(root);
        lvl = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(int x) {
        return find(root, x);
    }

    @Override
    public void add(int x) {
        if (root == null) {
            root = new Node(x, null);
            size++;
        } else {
            add(root, x);
        }
    }

    @Override
    public void remove(int x) {
        if (root != null) {
            remove(root, x);
        }
    }
}
